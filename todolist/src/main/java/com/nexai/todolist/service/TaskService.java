package com.nexai.todolist.service;

import com.nexai.todolist.entity.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    Task createTask(Task task);

    List<Task> getAllTasks();

    List<Task> getTasksByStatus(String status);

    Optional<Task> getTaskById(Long id);

    Task updateTask(Long id, Task taskDetails) throws Exception;

    void deleteTask(Long id) throws Exception;
}
