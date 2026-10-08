package com.synergetics.taskflow;

import com.synergetics.taskflow.model.Priority;
import com.synergetics.taskflow.model.Task;
import com.synergetics.taskflow.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TaskServiceTest {

    private TaskService service;

    @BeforeEach
    void setUp() {
        service = new TaskService();
    }

    @Test
    void startsWithSeedTasks() {
        assertThat(service.findAll()).hasSize(3);
    }

    @Test
    void createAssignsIdAndStoresTask() {
        Task t = new Task();
        t.setTitle("Write tests");
        t.setPriority(Priority.LOW);

        Task saved = service.create(t);

        assertThat(saved.getId()).isNotNull();
        assertThat(service.findAll()).hasSize(4);
    }

    @Test
    void toggleFlipsCompletedFlag() {
        Long id = service.findAll().get(0).getId();

        service.toggle(id);
        assertThat(service.countCompleted()).isEqualTo(1);

        service.toggle(id);
        assertThat(service.countCompleted()).isZero();
    }

    @Test
    void deleteRemovesTask() {
        Long id = service.findAll().get(0).getId();
        service.delete(id);
        assertThat(service.count()).isEqualTo(2);
    }
}
