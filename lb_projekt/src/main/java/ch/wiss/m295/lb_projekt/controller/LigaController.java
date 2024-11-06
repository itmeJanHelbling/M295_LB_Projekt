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

import ch.wiss.m295.lb_projekt.exceptions.LigaCouldNotBeFoundException;
import ch.wiss.m295.lb_projekt.exceptions.LigaCouldNotBeSavedException;
import ch.wiss.m295.lb_projekt.exceptions.LigenLoadException;
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
    public ResponseEntity<Iterable<Liga>> getLigen() {
        Iterable<Liga> ligen = null;
        try {
            ligen = ligaRepository.findAll();
        } catch (Exception ex) {
            throw new LigenLoadException();
        }
        return ResponseEntity.ok(ligen);
    }

    // Eine bestimmte Liga abfragen mit der ID
    @GetMapping("/{id}")
    public ResponseEntity<Liga> getLiga(@PathVariable long id) {
        Liga liga = ligaRepository.findById(id)
                .orElseThrow(() -> new LigaCouldNotBeFoundException(id));
        return ResponseEntity.ok(liga);
    }

    // Eine neue Liga erstellen
    @PostMapping("/")
    public ResponseEntity<Liga> createLiga(@Valid @RequestBody Liga liga) {
        try {
            // Save the Liga object to the repository
            Liga savedLiga = ligaRepository.save(liga);

            // Return a response with status 201 (Created) and the saved Liga in the body
            return ResponseEntity.status(HttpStatus.CREATED).body(savedLiga);
        } catch (Exception ex) {
            // Handle any exceptions and throw a custom exception
            throw new LigaCouldNotBeSavedException(liga.getName());
        }
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
