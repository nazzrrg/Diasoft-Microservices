package com.example.hw1;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private static GreetingService greetingService;

    public GreetingController(GreetingService service) {
        greetingService = service;
    }

    @GetMapping("/greetings")
    public List<Greeting> getAll() {
        return greetingService.getAll();
    }

    @GetMapping("/greetings/{id}")
    public Greeting getById(@PathVariable long id) {
        return greetingService.getById(id);
    }

    @PostMapping("/greetings")
    public void create(@RequestBody Greeting greeting) {
        greetingService.create(greeting);
    }

    @PutMapping("/greetings")
    public void update(@RequestBody Greeting greeting) {
        greetingService.update(greeting.getId(), greeting);
    }

    @DeleteMapping("/greetings/{id}")
    public void delete(@PathVariable long id) {
        greetingService.delete(id);
    }
}
