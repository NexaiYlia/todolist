package com.nexai.todolist.service.impl;

import com.nexai.todolist.entity.Task;
import com.nexai.todolist.exceptions.ServiceException;
import com.nexai.todolist.repository.TaskRepository;

import com.nexai.todolist.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Override
    @Transactional
    public Task createTask(Task task) {
        task.setCreatedDate(LocalDate.now());
        return taskRepository.save(task);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public List<Task> getTasksByStatus(String status) {
        return taskRepository.findByStatus(status);
    }

    @Override
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    @Override
    @Transactional
    public Task updateTask(Long id, Task taskDetails) throws ServiceException {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ServiceException("The task is not found"));

        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setStatus(taskDetails.getStatus());

        return taskRepository.save(task);
    }

    @Override
    @Transactional
    public void deleteTask(Long id) throws ServiceException {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ServiceException("The task is not found"));

        taskRepository.delete(task);
    }
}
