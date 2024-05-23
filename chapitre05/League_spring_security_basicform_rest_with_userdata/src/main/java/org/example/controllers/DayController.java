package org.example.controllers;

import org.example.dto.Day;
import org.example.services.DayService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/days")
public class DayController {

    private final DayService dayService;

    public DayController(DayService dayService) {
        this.dayService = dayService;
    }

    @GetMapping("/saison/{saisonId}")
    public List<Day> getAllDaysBySaisonId(@PathVariable Long saisonId) {
        return dayService.getAllDaysBySaisonId(saisonId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Day> getDayById(@PathVariable Long id) {
        Optional<Day> day = dayService.getDayById(id);
        return day.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @PostMapping
    public ResponseEntity<String> createDay(@RequestBody Day day) {
        try {
            dayService.createDay(day);
            return ResponseEntity.status(HttpStatus.CREATED).body("Day created");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDay(@PathVariable Long id) {
        dayService.deleteDay(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Day deleted");
    }
}