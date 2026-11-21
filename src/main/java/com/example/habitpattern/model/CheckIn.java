package com.example.habitpattern.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class CheckIn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "habit_id")
    private Habit habit;

    private LocalDate date;

    public CheckIn() {}

    public CheckIn(Habit habit, LocalDate date) {
        this.habit = habit;
        this.date = date;
    }

    public Long getId() { return id; }
    public Habit getHabit() { return habit; }
    public LocalDate getDate() { return date; }
}