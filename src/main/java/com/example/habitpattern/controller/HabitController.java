package com.example.habitpattern.controller;
import com.example.habitpattern.model.CheckIn;
import com.example.habitpattern.model.Habit;
import com.example.habitpattern.service.HabitService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/habits")
public class HabitController {

    private final HabitService habitService;

    public HabitController(HabitService habitService) {
        this.habitService = habitService;
    }

    @GetMapping
    public List<Habit> getAll() {
        return habitService.getAll(); // lists all habits 
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Habit create(@Valid @RequestBody Habit habit) {
        return habitService.create(habit); // saves habits after recieving the go ahead from @valid
    }

    @PostMapping("/{id}/checkins")
    @ResponseStatus(HttpStatus.CREATED)
    public CheckIn checkIn(@PathVariable Long id) { // logs check in for the period of time, and returns check in state
        return habitService.checkIn(id);
    }

    @GetMapping("/{id}/streak")
    public Map<String, Integer> streak(@PathVariable Long id) { // calculates and determines the streak, returns state of streak
        return Map.of("streak", habitService.getCurrentStreak(id));
    }
}