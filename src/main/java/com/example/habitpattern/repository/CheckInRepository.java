package com.example.habitpattern.repository;

import com.example.habitpattern.model.CheckIn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface CheckInRepository extends JpaRepository<CheckIn, Long> {

    List<CheckIn> findByHabitIdOrderByDateDesc(Long habitId);

    boolean existsByHabitIdAndDate(Long habitId, LocalDate date);
}