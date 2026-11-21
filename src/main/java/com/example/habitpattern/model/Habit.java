package com.example.habitpattern.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Habit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank // input check, cannot be left blank 
    private String name;

    private String description;
    private String frequency;

    public Habit() {}

    public Habit(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // getters and setters
    public String getFrequency() { return frequency; }
    public void setFrequency(String frequency) { this.frequency = frequency; }
    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
}  