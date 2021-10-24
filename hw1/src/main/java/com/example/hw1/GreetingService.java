package com.example.hw1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GreetingService {

    @Autowired
    private GreetingRepository greetingRepository;

    public void create(Greeting greeting) {
        if (greetingRepository.existsById(greeting.getId())) {
            throw new ElementAlreadyExistsException();
        } else {
            greetingRepository.save(greeting);
        }
    }

    public List<Greeting> getAll() {
        return (List<Greeting>) greetingRepository.findAll();
    }

    public Greeting getById(Long id) {
        return greetingRepository.findById(id).orElseThrow(ElementNotFoundException::new);
    }

    public void update(long id, Greeting greeting) {
        if (greetingRepository.existsById(greeting.getId())) {
            greeting.setId(id);
            greetingRepository.save(greeting);
        } else {
            throw new ElementNotFoundException();
        }
    }

    public void delete(Long id) {
        greetingRepository.deleteById(id);
    }
}
