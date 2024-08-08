package net.javaguides.todoapp.model;

import java.time.LocalDate;
import java.util.Objects;

public class Todo {
    private Long id;
    private String title;
    private String username;
    private String description;
    private LocalDate targetDate;
    private boolean status;

    protected Todo() {
    }

    public Todo(long id, String title, String username, String description, LocalDate targetDate, boolean isDone) {
        this.id = id;
        this.title = title;
        this.username = username;
        this.description = description;
        this.targetDate = targetDate;
        this.status = isDone;
    }

    public Todo(String title, String username, String description, LocalDate targetDate, boolean isDone) {
        this.title = title;
        this.username = username;
        this.description = description;
        this.targetDate = targetDate;
        this.status = isDone;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getTargetDate() {
        return this.targetDate;
    }

    public void setTargetDate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }

    public boolean getStatus() {
        return this.status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Todo other = (Todo) obj;
        return Objects.equals(id, other.id);
    }
}