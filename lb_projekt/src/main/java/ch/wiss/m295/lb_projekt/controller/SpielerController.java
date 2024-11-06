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

import ch.wiss.m295.lb_projekt.exceptions.SpielerCouldNotBeFoundException;
import ch.wiss.m295.lb_projekt.exceptions.SpielerLoadException;
import ch.wiss.m295.lb_projekt.model.Spieler;
import ch.wiss.m295.lb_projekt.model.Team;
import ch.wiss.m295.lb_projekt.repository.SpielerRepository;
import ch.wiss.m295.lb_projekt.repository.TeamRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/spieler")
public class SpielerController {

    // Logger Objekt
    Logger logger = LoggerFactory.getLogger(SpielerController.class);

    // Repository Objekt mit Konstruktor nach SonarLint vorgabe
    private final SpielerRepository spielerRepository;
    private final TeamRepository teamRepository;

    @Autowired
    public SpielerController(SpielerRepository spielerRepository, TeamRepository teamRepository) {
        this.spielerRepository = spielerRepository;
        this.teamRepository = teamRepository;
    }

    // Alle Spieler abfragen
    @GetMapping("/")
    public ResponseEntity<Iterable<Spieler>> getSpielers() {
        Iterable<Spieler> spieler = null;
        try {
            spieler = spielerRepository.findAll();
        } catch (Exception ex) {
            throw new SpielerLoadException();
        }
        return ResponseEntity.ok(spieler);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Spieler> getSpieler(@PathVariable long id) {
        Spieler spieler = spielerRepository.findById(id)
                .orElseThrow(() -> new SpielerCouldNotBeFoundException(id));
        return ResponseEntity.ok(spieler);
    }

    // Eine neuer Spieler erstellen
    @PostMapping("/")
    public ResponseEntity<Object> createSpieler(@Valid @RequestBody Spieler spieler) {
        try {
            // Verify that the associated Team exists
            Team team = teamRepository.findById(spieler.getTeam().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid team ID"));

            spieler.setTeam(team); // Associate the team with the player
            Spieler savedSpieler = spielerRepository.save(spieler);

            return ResponseEntity.ok().body(savedSpieler);
        } catch (Exception e) {
            logger.error("Error creating player: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating player: " + e.getMessage());
        }
    }

    // Ein bestehenden Spieler anpassen
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateSpieler(@PathVariable long id, @Valid @RequestBody Spieler updatedSpieler) {
        try {
            // Fetch the existing team
            Spieler existingSpieler = spielerRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Spieler not found with id " + id));

            // Update fields
            existingSpieler.setName(updatedSpieler.getName());
            existingSpieler.setAge(updatedSpieler.getAge());
            existingSpieler.setPosition(updatedSpieler.getPosition());
            existingSpieler.setTeam(updatedSpieler.getTeam());

            // Save the updated team
            Spieler savedSpieler = spielerRepository.save(existingSpieler);
            return ResponseEntity.ok().body(savedSpieler);
        } catch (RuntimeException e) {
            logger.error("Error updating spieler: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            logger.error("Error updating spieler: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating spieler");
        }
    }

    // Ein bestehenden Spieler löschen
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSpieler(@PathVariable long id) {
        spielerRepository.deleteById(id);
        return ResponseEntity.ok().body("Spieler " + id + " gelöscht");
    }

}
