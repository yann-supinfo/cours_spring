package org.example.services;

import org.example.dto.*;
import org.example.exception.BadDateException;
import org.example.exception.ClashException;
import org.example.exception.DayNotFoundException;
import org.example.exception.SaisonNotFoundException;
import org.example.repository.ClashRepository;
import org.example.repository.DayRepository;
import org.example.repository.SaisonRepository;
import org.example.repository.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class ClashService {
    private final ClashRepository clashRepository;
    private final TeamRepository teamRepository;
    private final DayRepository dayRepository;
    private final SaisonRepository saisonRepository;

    public ClashService(ClashRepository clashRepository, TeamRepository teamRepository, DayRepository dayRepository, SaisonRepository saisonRepository) {
        this.clashRepository = clashRepository;
        this.teamRepository = teamRepository;
        this.dayRepository = dayRepository;
        this.saisonRepository = saisonRepository;
    }

    public List<Clash> getMatchsByDayId(Long dayId) {
        return clashRepository.findAllByDayId(dayId);
    }


    public void createClash(ClashFormDto clashFormDto) throws BadDateException,
            ClashException, DayNotFoundException, SaisonNotFoundException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        LocalDateTime dateTime = LocalDateTime.parse(clashFormDto.getClashDate(), formatter);

        int year = dateTime.getYear();

        int dayOfYear = dateTime.getDayOfYear();

        System.out.println("Year: " + year);
        System.out.println("Day of Year: " + dayOfYear);

        if (dateTime.isBefore(LocalDateTime.now())) {
            throw new BadDateException("Match date must be in the future");
        }

        Optional<Saison> saison = saisonRepository.findByYear(year);

        if (!saison.isPresent()) {
            throw new SaisonNotFoundException("Saison for this year not exists");
        }


        Optional<Day> dayOptional = dayRepository.findByNumeroAndSaisonId(dayOfYear, saison.get().getId());
        if (!dayOptional.isPresent()) {
            throw new DayNotFoundException("Day not found");
        }


        boolean homeTeamInSaison = teamRepository.isTeamExistInSaison(saison.get().getId(), clashFormDto.getAwayTeamId());
        boolean awayTeamInSaison = teamRepository.isTeamExistInSaison(saison.get().getId(), clashFormDto.getHomeTeamId());

        if (!homeTeamInSaison || !awayTeamInSaison) {
            throw new ClashException("Both teams must be registered in the same season");
        }
        Optional<Clash> clashOptional = clashRepository.findByDayAndTeam(dayOptional.get().getId(), clashFormDto.getAwayTeamId(),clashFormDto.getHomeTeamId());

        if (clashOptional.isPresent()) {
            throw new ClashException("Clash already exist at this day");
        }

        clashFormDto.setDayId(dayOptional.get().getId());
        clashRepository.save(clashFormDto);
    }

    @Transactional
    public void updateMatchDate(Long clashId, ClashFormDto clashFormDto) throws BadDateException,
            ClashException, DayNotFoundException, SaisonNotFoundException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        LocalDateTime dateTime = LocalDateTime.parse(clashFormDto.getClashDate(), formatter);

        int year = dateTime.getYear();

        int dayOfYear = dateTime.getDayOfYear();

        System.out.println("Year: " + year);
        System.out.println("Day of Year: " + dayOfYear);

        if (dateTime.isBefore(LocalDateTime.now())) {
            throw new BadDateException("Match date must be in the future");
        }
        Optional<Saison> saison = saisonRepository.findByYear(year);

        if (!saison.isPresent()) {
            throw new SaisonNotFoundException("Saison for this year not exists");
        }


        Optional<Day> dayOptional = dayRepository.findByNumeroAndSaisonId(dayOfYear, saison.get().getId());
        if (!dayOptional.isPresent()) {
            throw new DayNotFoundException("Day not found");
        }


        boolean homeTeamInSaison = teamRepository.isTeamExistInSaison(saison.get().getId(), clashFormDto.getAwayTeamId());
        boolean awayTeamInSaison = teamRepository.isTeamExistInSaison(saison.get().getId(), clashFormDto.getHomeTeamId());

        if (!homeTeamInSaison || !awayTeamInSaison) {
            throw new ClashException("Both teams must be registered in the same season");
        }

        clashRepository.updateClash(clashId, clashFormDto);
    }
}
