package org.example.services;

import org.example.dto.Day;
import org.example.repository.DayRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DayService {

    private final DayRepository dayRepository;

    public DayService(DayRepository dayRepository) {
        this.dayRepository = dayRepository;
    }

    public List<Day> getAllDaysBySaisonId(Long saisonId) {
        return dayRepository.findAllBySaisonId(saisonId);
    }

    public Optional<Day> getDayById(Long id) {
        return dayRepository.findById(id);
    }


    public void createDay(Day day) {
        Optional<Day> existingDay = dayRepository.findByNumeroAndSaisonId(day.getNumero(), day.getSaisonId());
        if (existingDay.isPresent()) {
            throw new IllegalArgumentException("Day already exists in this season");
        }
        dayRepository.save(day);
    }


    public void deleteDay(Long id) {
        dayRepository.deleteById(id);
    }
}