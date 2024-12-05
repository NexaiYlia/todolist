package com.nexai.todolist.controller;

import com.nexai.todolist.entity.Task;
import com.nexai.todolist.service.impl.TaskServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskServiceImpl taskService;


    @PostMapping
    public Task createTask(@Valid @RequestBody Task task) {

        validateDueDate(task.getDueDate());
        return taskService.createTask(task);
    }

    @GetMapping
    public List<Task> getAllTasks(@RequestParam(required = false) String status) {
        if (status != null) {
            return taskService.getTasksByStatus(status);
        }
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) throws Exception {
        return taskService.getTaskById(id)
                .orElseThrow(() -> new Exception("The task is not found!"));
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @Valid @RequestBody Task taskDetails) throws Exception {
        validateDueDate(taskDetails.getDueDate());
        return taskService.updateTask(id, taskDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) throws Exception {
        taskService.deleteTask(id);
    }


    private void validateDueDate(LocalDate dueDate) {
        if (isHolidayOrWeekend(dueDate)) {
            throw new IllegalArgumentException("Choose another day " + getNextValidDate(dueDate));
        }
    }

    private boolean isHolidayOrWeekend(LocalDate date) {
        return date.getDayOfWeek().getValue() >= 6;
    }

    private LocalDate getNextValidDate(LocalDate date) {
        LocalDate nextDay = date.plusDays(1);
        while (nextDay.getDayOfWeek().getValue() >= 6) {
            nextDay = nextDay.plusDays(1);
        }
        return nextDay;
    }
}

