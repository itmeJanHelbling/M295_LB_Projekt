package ch.wiss.m295.lb_projekt.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.wiss.m295.lb_projekt.model.Team;
import ch.wiss.m295.lb_projekt.repository.TeamRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/team")
public class TeamController {

    // Logger Objekt
    Logger logger = LoggerFactory.getLogger(TeamController.class);

    // Repository Objekt mit Konstruktor nach SonarLint vorgabe
    private final TeamRepository teamRepository;

    @Autowired
    public TeamController(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    // Alle Teams abfragen
    @GetMapping("/")
    public ResponseEntity<?> getTeams() {
        try {
            Iterable<Team> teams = teamRepository.findAll();
            return ResponseEntity.ok().body(teams);
        } catch (Exception e) {
            logger.error("Error fetching teams: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching teams");
        }
    }

    // Eine bestimmtes Team abfragen mit der ID
    @GetMapping("/{id}")
    public ResponseEntity<Team> getTeam(@PathVariable long id) {
        return ResponseEntity.ok().body(teamRepository.findById(id).get());
    }

    // Eine neues Team erstellen
    @PostMapping("/")
    public ResponseEntity<?> createTeam(@Valid @RequestBody Team team) {
        try {
            logger.info("Erstelle Team: {}", team);
            Team savedTeam = teamRepository.save(team);
            return ResponseEntity.ok().body(savedTeam);
        } catch (Exception e) {
            logger.error("Error creating team: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating team");
        }
    }

    // Eine bestehendes Team anpassen
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTeam(@PathVariable long id, @Valid @RequestBody Team updatedTeam) {
        try {
            // Fetch the existing team
            Team existingTeam = teamRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Team not found with id " + id));

            // Update fields
            existingTeam.setName(updatedTeam.getName());
            existingTeam.setAustragungsort(updatedTeam.getAustragungsort());
            existingTeam.setLiga(updatedTeam.getLiga()); // assuming Liga is correctly set in the updatedTeam

            // Save the updated team
            Team savedTeam = teamRepository.save(existingTeam);
            return ResponseEntity.ok().body(savedTeam);
        } catch (RuntimeException e) {
            logger.error("Error updating team: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            logger.error("Error updating team: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating team");
        }
    }

    // Eine bestehendes Team löschen
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTeam(@PathVariable long id) {
        teamRepository.deleteById(id);
        return ResponseEntity.ok().body("Team " + id + " gelöscht");
    }
}
