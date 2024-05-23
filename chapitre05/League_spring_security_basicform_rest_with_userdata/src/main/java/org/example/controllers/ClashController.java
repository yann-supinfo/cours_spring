package org.example.controllers;

import org.example.dto.Clash;
import org.example.dto.ClashFormDto;
import org.example.services.ClashService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/clashs")
public class ClashController {
    private final ClashService clashService;

    public ClashController(ClashService clashService) {
        this.clashService = clashService;
    }

    @GetMapping("/day/{dayId}")
    public List<Clash> getMatchsByDayId(@PathVariable Long dayId) {
        return clashService.getMatchsByDayId(dayId);
    }

    @PostMapping
    public ResponseEntity<String> createMatchs(@RequestBody ClashFormDto clashFormDto) {
        try {
            clashService.createClash(clashFormDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Match created");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{matchsId}")
    public ResponseEntity<String> updateMatchDate(@PathVariable Long matchsId, @RequestParam ClashFormDto clashFormDto) {
        try {
            clashService.updateMatchDate(matchsId, clashFormDto);
            return ResponseEntity.status(HttpStatus.OK).body("Match date updated");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
