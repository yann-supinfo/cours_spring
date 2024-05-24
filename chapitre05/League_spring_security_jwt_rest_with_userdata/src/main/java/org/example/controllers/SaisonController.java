package org.example.controllers;

import org.example.dto.Saison;
import org.example.exception.SaisonAlreadyExistException;
import org.example.exception.SaisonNotFoundException;
import org.example.services.SaisonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/saisons")
public class SaisonController {

    private final SaisonService saisonService;

    public SaisonController(SaisonService saisonService) {
        this.saisonService = saisonService;
    }

    @GetMapping
    public List<Saison> getAllSaisons() {
        return saisonService.getAllSaisons();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Saison> getSaisonById(@PathVariable Long id) {
        Optional<Saison> saison = saisonService.getSaisonById(id);
        return saison.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }


    @PostMapping
    public ResponseEntity<String> createSaison(@RequestBody Saison createSaisonRequest) {
        try {
            saisonService.createSaison(createSaisonRequest.getYear());
            return ResponseEntity.status(HttpStatus.CREATED).body("Saison created");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/register/saison/{saisonId}/team/{teamId}")
    public ResponseEntity<String> register(@PathVariable int saisonId, @PathVariable int teamId) {
        try {
            saisonService.registerTeam(teamId , saisonId);
            return ResponseEntity.status(HttpStatus.CREATED).body("Team registered");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateSaison(@PathVariable int id, @RequestBody Saison createSaisonRequest) {
        try {
            saisonService.updateSaison(id, createSaisonRequest.getYear());
            return ResponseEntity.status(HttpStatus.CREATED).body("Saison Updated");
        } catch (SaisonAlreadyExistException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSaison(@PathVariable Long id) {
        try {
            saisonService.deleteSaison(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Saison deleted");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}