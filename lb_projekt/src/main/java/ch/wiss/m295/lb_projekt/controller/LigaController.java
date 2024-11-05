package ch.wiss.m295.lb_projekt.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.wiss.m295.lb_projekt.model.Liga;
import ch.wiss.m295.lb_projekt.repository.LigaRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/liga")
public class LigaController {

    // Logger Objekt
    Logger logger = LoggerFactory.getLogger(LigaController.class);

    // Repository Objekt mit Konstruktor nach SonarLint vorgabe
    private final LigaRepository ligaRepository;

    @Autowired
    public LigaController(LigaRepository ligaRepository) {
        this.ligaRepository = ligaRepository;
    }

    // Alle Ligen abfragen
    @GetMapping("/")
    public ResponseEntity<Iterable<Liga>> getLigas() {
        return ResponseEntity.ok().body(ligaRepository.findAll());
    }

    // Eine bestimmte Liga abfragen mit der ID
    @GetMapping("/{id}")
    public ResponseEntity<Liga> getLiga(@PathVariable long id) {
        return ResponseEntity.ok().body(ligaRepository.findById(id).get());
    }

    // Eine neue Liga erstellen
    @PostMapping("/") /* RequestBody muss JSON-Objekt mit Liga-Attributen enthalten */
    public ResponseEntity<Liga> createLiga(@Valid @RequestBody Liga liga) {
        logger.info("Erstelle Liga: {}", liga);
        return ResponseEntity.ok().body(ligaRepository.save(liga));
    }

    // Eine bestehende Liga anpassen
    @PutMapping("/{id}")
    public ResponseEntity<Liga> updateLiga(@PathVariable long id, @Valid @RequestBody Liga updatedLiga) {
        return ligaRepository.findById(id)
                .map(liga -> {
                    liga.setName(updatedLiga.getName());
                    liga.setLand(updatedLiga.getLand());
                    logger.info("Aktualisiere Liga mit ID {}: {}", id, liga);
                    Liga savedLiga = ligaRepository.save(liga);
                    return ResponseEntity.ok(savedLiga);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Eine bestehende Liga löschen
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLiga(@PathVariable long id) {
        ligaRepository.deleteById(id);
        return ResponseEntity.ok().body("Liga " + id + " gelöscht");
    }

}
