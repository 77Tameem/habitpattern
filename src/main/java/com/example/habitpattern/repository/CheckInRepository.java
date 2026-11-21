package com.example.habitpattern.repository;
import com.example.habitpattern.model.CheckIn;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

// The interface between spring and the check in class.
public interface CheckInRepository extends JpaRepository<CheckIn, Long> { //selecting the CheckIn entities that are long

    List<CheckIn> findByHabitIdOrderByDateDesc(Long habitId);
    //SQL Query that searches through habit id (ordered by descending)
    boolean existsByHabitIdAndDate(Long habitId, LocalDate date);
    //Checks whether a row exists matching a specific habitID and a date.
}