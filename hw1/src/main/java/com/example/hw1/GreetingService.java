package com.example.hw1;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class GreetingService {
    private final GreetingRepository greetingRepository;

    public GreetingService(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    @Transactional
    public void save(Greeting greeting) {
        greetingRepository.save(greeting);
    }

    @Transactional(readOnly = true)
    public List<Greeting> getAll() {
        return (List<Greeting>) greetingRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Greeting getById(Long id) {
        return greetingRepository.findById(id).orElseThrow(ElementNotFoundException::new);
    }

    @Transactional
    public void delete(Long id) {
        greetingRepository.deleteById(id);
    }

    @Transactional
    public void update(Greeting greeting) {
        save(greeting);
    }
}
