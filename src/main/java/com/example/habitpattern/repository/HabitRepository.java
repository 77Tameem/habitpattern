package com.example.habitpattern.repository;

import com.example.habitpattern.model.Habit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HabitRepository extends JpaRepository<Habit, Long> {}