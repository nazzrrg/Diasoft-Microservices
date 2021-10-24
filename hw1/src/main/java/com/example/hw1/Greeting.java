package com.example.hw1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "greetings")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Greeting {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "Id")
    private long id;

    @Column(name = "content")
    private String content;

    public Greeting(String content) {
        this.content = content;
    }
}