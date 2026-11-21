package com.example.habitpattern.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class CheckIn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false) // check ins must be met with a habit
    @JoinColumn(name = "habit_id") // foreign key
    private Habit habit;

    private LocalDate date; // date of check in 

    public CheckIn() {}

    public CheckIn(Habit habit, LocalDate date) {
        this.habit = habit;
        this.date = date;
    }

    //getters
    public Long getId() { return id; }
    public Habit getHabit() { return habit; }
    public LocalDate getDate() { return date; }
}