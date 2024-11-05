package ch.wiss.m295.lb_projekt.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Optional;

import ch.wiss.m295.lb_projekt.model.Liga;
import ch.wiss.m295.lb_projekt.model.Team;
import ch.wiss.m295.lb_projekt.repository.LigaRepository;
import ch.wiss.m295.lb_projekt.repository.SpielerRepository;
import ch.wiss.m295.lb_projekt.repository.TeamRepository;

@ExtendWith(SpringExtension.class)
@WebMvcTest
@AutoConfigureMockMvc
class TeamControllerTest {

    @MockBean // simuliert DOC
    LigaRepository ligaRepository;

    @MockBean
    TeamRepository teamRepository;

    @MockBean
    SpielerRepository spielerRepository;

    @Autowired
    TeamController teamController;

    @Autowired
    MockMvc mockMvc;

    // Test 1
    @Test
    void testGetTeamById_ReturnsTeam() throws Exception {

        // Arrange
        // testLiga anlegen
        Liga testLiga = new Liga();
        testLiga.setId(1L);
        testLiga.setName("Test Liga");

        // testTeam anlegen
        Team testTeam = new Team();
        testTeam.setId(1L);
        testTeam.setName("Test Team");
        testTeam.setAustragungsort("Test Stadion");
        testTeam.setLiga(testLiga);

        // Mock the service behavior
        Mockito.when(teamRepository.findById(1L)).thenReturn(Optional.of(testTeam));

        // Act & Assert
        // Überprüfen ob die Values übereinstimmen
        mockMvc.perform(get("/team/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Test Team")))
                .andExpect(jsonPath("$.austragungsort", is("Test Stadion")))
                .andExpect(jsonPath("$.liga.id", is(1)))
                .andExpect(jsonPath("$.liga.name", is("Test Liga")));
    }

    // Test 2
    // Testet den Post Request von einem gültigen Team Objekt und gibt uns ein 2xx
    // Status Successful zurück
    @Test
    void testCorrectPostRequest_ReturnsOk() throws Exception {
        // Erstelle ein Liga-Objekt
        Liga testLiga = new Liga();
        testLiga.setId(1L);
        testLiga.setName("Test Liga");
        testLiga.setLand("Switzerland");

        // Erstelle ein Team-Objekt
        Team testTeam = new Team();
        testTeam.setId(1L);
        testTeam.setName("Testname");
        testTeam.setAustragungsort("Test Stadion");
        testTeam.setLiga(testLiga); // Setze die Liga

        // Konvertiere das Team-Objekt in JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(testTeam);

        // Führe den POST-Request aus und überprüfe den Status
        mockMvc.perform(post("/team/")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }
}