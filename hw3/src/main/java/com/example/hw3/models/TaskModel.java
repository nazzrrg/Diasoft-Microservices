package com.example.hw3.models;

public class TaskModel {
    private static long ID = 0;

    public long getId() {
        return id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private final long id;
    private String description;

    public TaskModel(String description) {
        this.id = ID++;
        this.description = description;
    }

    @Override
    public String toString() {
        return description + "(" + id + ")\n";
    }
}
