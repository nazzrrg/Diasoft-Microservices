package com.example.hw3.controllers;

import com.example.hw3.models.TaskModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("tasks")
public class TaskController {
    private final List<TaskModel> tasks = new LinkedList<>() {{
        add(new TaskModel("Task 1"));
        add(new TaskModel("Task 2"));
        add(new TaskModel("Task 3"));
    }};

    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);

    @GetMapping
    public String getTasks() {
        StringBuilder sb = new StringBuilder("Current tasks:\n");

        for (TaskModel task : tasks) {
            sb.append(task);
        }

        return sb.toString();
    }

    @GetMapping("{id}")
    public String getTask(@PathVariable long id) {
        return tasks.stream()
                    .filter(t -> t.getId() == id)
                    .findFirst()
                    .orElseThrow()
                    .toString();
    }

    @PostMapping
    public String createTask(@RequestBody Map<String, String> body) {
        TaskModel t = new TaskModel(body.get("description"));
        tasks.add(t);
        return t.toString();
    }

    @PutMapping
    public String updateTask(@RequestBody Map<String, String> body) {
        TaskModel task = tasks.stream()
                .filter(t -> t.getId() == Long.parseLong(body.get("id")))
                .findFirst()
                .orElseThrow();

        logger.info(task.toString());

        task.setDescription(body.get("description"));
        return task.toString();
    }

    @DeleteMapping
    public void deleteTask(@RequestBody Map<String, String> body) {
        tasks.removeIf(t -> t.getId() == Long.parseLong(body.get("id")));
    }
}
