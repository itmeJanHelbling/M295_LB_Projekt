package ch.wiss.m295.lb_projekt.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.wiss.m295.lb_projekt.model.Team;
import ch.wiss.m295.lb_projekt.repository.TeamRepository;

@RestController
@RequestMapping("/spielplan")
public class SpielplanController {

    private final TeamRepository teamRepository;

    @Autowired
    public SpielplanController(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @GetMapping("/{ligaId}")
    public ResponseEntity<List<Map<String, Object>>> getSpielplan(@PathVariable int ligaId) {
        List<Team> teams = teamRepository.findAllByLigaId(ligaId);
        List<Map<String, Object>> spiele = new ArrayList<>();

        // Erstellen des Spielplans
        for (int i = 0; i < teams.size(); i++) {
            for (int j = i + 1; j < teams.size(); j++) {
                Map<String, Object> spiel = new HashMap<>();
                spiel.put("team1", teams.get(i).getName());
                spiel.put("team2", teams.get(j).getName());
                spiel.put("austragungsort", getRandomAustragungsort(teams.get(i), teams.get(j)));
                spiel.put("datum", getRandomDatum());

                spiele.add(spiel);
            }
        }

        return ResponseEntity.ok(spiele);
    }

    private String getRandomAustragungsort(Team team1, Team team2) {
        List<String> austragungsorte = new ArrayList<>();
        austragungsorte.add(team1.getAustragungsort());
        austragungsorte.add(team2.getAustragungsort());

        // Shuffle the list and return the first element
        Collections.shuffle(austragungsorte);
        return austragungsorte.get(0); // return a random team stadium
    }

    private LocalDateTime getRandomDatum() {
        LocalDateTime now = LocalDateTime.now();
        return now.plusDays((long) (Math.random() * 30));
    }
}
