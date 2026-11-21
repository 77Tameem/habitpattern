package com.example.habitpattern.repository;
import com.example.habitpattern.model.Habit;
import org.springframework.data.jpa.repository.JpaRepository;

// used to manage entities through JpaRespository, this includes save/findby/delete/findall
public interface HabitRepository extends JpaRepository<Habit, Long> {}