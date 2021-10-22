package com.example.hw1;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GreetingService {
    private final GreetingRepository greetingRepository;

    public GreetingService(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    public void save(Greeting greeting) {
        greetingRepository.save(greeting);
    }

    public List<Greeting> getAll() {
        return (List<Greeting>) greetingRepository.findAll();
    }

    public Greeting getById(Long id) {
        return greetingRepository.findById(id).orElseThrow(ElementNotFoundException::new);
    }

    public void delete(Long id) {
        greetingRepository.deleteById(id);
    }

    public void update(Greeting greeting) {
        save(greeting);
    }
}
