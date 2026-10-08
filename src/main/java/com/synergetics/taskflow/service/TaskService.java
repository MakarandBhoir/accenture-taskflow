package com.synergetics.taskflow.service;

import com.synergetics.taskflow.model.Priority;
import com.synergetics.taskflow.model.Task;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/** In-memory store: keeps the demo focused on the pipeline, not on a database. */
@Service
public class TaskService {

    private final Map<Long, Task> tasks = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong();

    public TaskService() {
        create(seed("Set up repository", "Create the repo and push the starter code", Priority.HIGH));
        create(seed("Write CI workflow", "Build and test with Maven on every push", Priority.HIGH));
        create(seed("Deploy to Azure", "Add a CD workflow for Azure App Service", Priority.MEDIUM));
    }

    public List<Task> findAll() {
        return tasks.values().stream()
                .sorted(Comparator.comparing(Task::getId))
                .toList();
    }

    public Task create(Task task) {
        task.setId(sequence.incrementAndGet());
        tasks.put(task.getId(), task);
        return task;
    }

    public void toggle(Long id) {
        Task task = tasks.get(id);
        if (task != null) {
            task.setCompleted(!task.isCompleted());
        }
    }

    public void delete(Long id) {
        tasks.remove(id);
    }

    public long countCompleted() {
        return tasks.values().stream().filter(Task::isCompleted).count();
    }

    public int count() {
        return tasks.size();
    }

    private Task seed(String title, String description, Priority priority) {
        Task t = new Task();
        t.setTitle(title);
        t.setDescription(description);
        t.setPriority(priority);
        return t;
    }
}
