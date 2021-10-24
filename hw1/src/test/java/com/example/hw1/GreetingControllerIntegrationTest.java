package com.example.hw1;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
class GreetingControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @SneakyThrows
    @Test
    void getAll() {
        List<Greeting> expected = new LinkedList<>() {{
            add(new Greeting(1, "test1"));
            add(new Greeting(2, "test2"));
        }};

        mvc.perform(MockMvcRequestBuilders.get("/greetings").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].content", is(expected.get(0).getContent())))
                .andExpect(jsonPath("$[1].content", is(expected.get(1).getContent())));
    }

    @SneakyThrows
    @Test
    void getById() {
        Greeting expected = new Greeting(1, "test1");

        mvc.perform(MockMvcRequestBuilders.get("/greetings/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content", is(expected.getContent())))
                .andExpect(jsonPath("$.id", is((int)expected.getId())));
    }

    @Transactional
    @SneakyThrows
    @Test
    void create() {
        Greeting newGreeting = new Greeting("test3");

        mvc.perform(MockMvcRequestBuilders.post("/greetings").contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(newGreeting)))
                .andExpect(status().isOk());

        mvc.perform(MockMvcRequestBuilders.get("/greetings/3").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content", is(newGreeting.getContent())))
                .andExpect(jsonPath("$.id").exists());
    }

    @Transactional
    @SneakyThrows
    @Test
    void update() {
        Greeting updatedGreeting = new Greeting(1, "test0");

        mvc.perform(MockMvcRequestBuilders.put("/greetings").contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(updatedGreeting)))
                .andExpect(status().isOk());

        mvc.perform(MockMvcRequestBuilders.get("/greetings/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content", is(updatedGreeting.getContent())))
                .andExpect(jsonPath("$.id", is((int)updatedGreeting.getId())));
    }

    @Transactional
    @SneakyThrows
    @Test
    void delete() {
        mvc.perform(MockMvcRequestBuilders.delete("/greetings/1")).andExpect(status().isOk());
        mvc.perform(MockMvcRequestBuilders.get("/greetings/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}