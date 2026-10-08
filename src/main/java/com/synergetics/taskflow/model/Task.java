package com.synergetics.taskflow.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Task {

    private Long id;

    @NotBlank(message = "Title is required")
    @Size(max = 80, message = "Title must be 80 characters or fewer")
    private String title;

    @Size(max = 250, message = "Description must be 250 characters or fewer")
    private String description;

    @NotNull(message = "Priority is required")
    private Priority priority = Priority.MEDIUM;

    private boolean completed;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Priority getPriority() { return priority; }
    public void setPriority(Priority priority) { this.priority = priority; }

    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }
}
