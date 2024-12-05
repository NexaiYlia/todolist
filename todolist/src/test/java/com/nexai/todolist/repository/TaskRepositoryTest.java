package com.nexai.todolist.repository;


import com.nexai.todolist.entity.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class TaskRepositoryTest {
    @Autowired
    private TaskRepository taskRepository; // Репозиторий для работы с задачами

    @Test
    public void testSaveAndFindTask() {

        Task task = new Task();
        task.setTitle("Test Task");
        task.setDescription("This is a test task.");
        task.setStatus("Not Completed");

        Task savedTask = taskRepository.save(task);

        assertThat(savedTask).isNotNull();
        assertThat(savedTask.getId()).isNotNull();

        Task foundTask = taskRepository.findById(savedTask.getId()).orElse(null);

        assertThat(foundTask).isNotNull();
        assertThat(foundTask.getTitle()).isEqualTo("Test Task");
        assertThat(foundTask.getDescription()).isEqualTo("This is a test task.");
    }
}
