package com.nexai.todolist.entity;

import java.time.LocalDateTime;

public class Entity {

    private String title;
    private String description;
    private LocalDateTime createdDate = LocalDateTime.now();
    private LocalDateTime dueDate;

    private TaskStatus status = TaskStatus.PENDING;

    public enum TaskStatus {
        PENDING, COMPLETED
    }

}

