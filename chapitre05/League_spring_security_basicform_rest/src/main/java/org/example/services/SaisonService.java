package org.example.services;

import org.example.dto.Saison;
import org.example.exception.SaisonAlreadyExistException;
import org.example.exception.SaisonNotFoundException;
import org.example.exception.TeamNotFoundException;
import org.example.repository.SaisonRepository;
import org.example.repository.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;
import java.util.Optional;

@Service
public class SaisonService {

    public  int getDaysInYear(int year) {
        Year y = Year.of(year);
        LocalDate firstDay = y.atDay(1);
        LocalDate lastDay = y.atDay(y.length());
        return (int) (lastDay.toEpochDay() - firstDay.toEpochDay() + 1);
    }

    private final SaisonRepository saisonRepository;
    private final TeamRepository teamRepository;

    public SaisonService(SaisonRepository saisonRepository, TeamRepository teamRepository) {
        this.saisonRepository = saisonRepository;
        this.teamRepository = teamRepository;
    }

    public List<Saison> getAllSaisons() {
        return saisonRepository.findAll();
    }

    public Optional<Saison> getSaisonById(Long id) {
        return saisonRepository.findById(id);
    }


    public void createSaison(int year) {
        if (saisonRepository.findByYear(year).isPresent()) {
            throw new IllegalArgumentException("Saison for this year already exists");
        }
        String libelle = "Saison " + year + "-" + (year + 1);
        Saison saison = new Saison(null, libelle, year);
        saisonRepository.save(saison);
    }

    public void updateSaison(int saisonId, int year) throws SaisonAlreadyExistException {


        if (saisonRepository.findByYearAndNotById(year, saisonId).isPresent()) {
            throw new SaisonAlreadyExistException("Saison for this year already exists");
        }
        String libelle = "Saison " + year + "-" + (year + 1);
        Saison saison = new Saison(null, libelle, year);
        saisonRepository.update(saisonId, saison);
    }


    public void deleteSaison(Long id) {
        saisonRepository.deleteById(id);
    }

    public void registerTeam(int teamId, int saisonId) throws SaisonAlreadyExistException, TeamNotFoundException, SaisonNotFoundException {

        if (!teamRepository.getTeamById(teamId).isPresent()) {
            throw new TeamNotFoundException("This team not exist");
        }

        if(!saisonRepository.findById(Long.valueOf(saisonId)).isPresent()) {
            throw  new SaisonNotFoundException("This saison not exist");
        }

        if (saisonRepository.findByTeamIdAndSaisonId(teamId, saisonId)) {
            throw new SaisonAlreadyExistException("Team for this saison already exists");
        }
        saisonRepository.addTeamToSaison(teamId, saisonId);
    }


}
