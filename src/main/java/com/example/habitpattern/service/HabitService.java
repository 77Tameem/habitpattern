package com.example.habitpattern.service;

import com.example.habitpattern.model.CheckIn;
import com.example.habitpattern.model.Habit;
import com.example.habitpattern.repository.CheckInRepository;
import com.example.habitpattern.repository.HabitRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class HabitService {

    private final HabitRepository habitRepository;
    private final CheckInRepository checkInRepository;

    public HabitService(HabitRepository habitRepository, CheckInRepository checkInRepository) {
        this.habitRepository = habitRepository;
        this.checkInRepository = checkInRepository;
    }

    public List<Habit> getAll() {
        return habitRepository.findAll();
    }

    public Habit create(Habit habit) {
        return habitRepository.save(habit);
    }

    public CheckIn checkIn(Long habitId) {
        Habit habit = habitRepository.findById(habitId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Habit not found"));

        LocalDate today = LocalDate.now();
        if (checkInRepository.existsByHabitIdAndDate(habitId, today)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Already checked in today");
        }
        return checkInRepository.save(new CheckIn(habit, today));
    }

    public int getCurrentStreak(Long habitId) {
        if (!habitRepository.existsById(habitId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Habit not found");
        }
        List<LocalDate> dates = checkInRepository.findByHabitIdOrderByDateDesc(habitId)
                .stream()
                .map(CheckIn::getDate)
                .toList();
        return calculateStreak(dates, LocalDate.now());
    }

 static int calculateStreak(List<LocalDate> datesNewestFirst, LocalDate today) {
        if (datesNewestFirst.isEmpty()) {
            return 0;
        }

        LocalDate newest = datesNewestFirst.get(0);
        LocalDate expected;

        if (newest.equals(today)) {
            expected = today;
        } else if (newest.equals(today.minusDays(1))) {
            expected = today.minusDays(1);
        } else {
            return 0;
        }

        int streak = 0;
        for (LocalDate date : datesNewestFirst) {
            if (date.equals(expected)) {
                streak++;
                expected = expected.minusDays(1);
            } else {
                break;
            }
        }
        return streak;
    }
}