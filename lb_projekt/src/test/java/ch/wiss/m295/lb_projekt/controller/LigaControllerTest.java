package ch.wiss.m295.lb_projekt.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;

import ch.wiss.m295.lb_projekt.model.Liga;
import ch.wiss.m295.lb_projekt.repository.LigaRepository;
import ch.wiss.m295.lb_projekt.repository.SpielerRepository;
import ch.wiss.m295.lb_projekt.repository.TeamRepository;

@ExtendWith(SpringExtension.class)
@WebMvcTest
@AutoConfigureMockMvc
class LigaControllerTest {

    @MockBean // simuliert DOC
    LigaRepository ligaRepository;

    @MockBean
    TeamRepository teamRepository;

    @MockBean
    SpielerRepository spielerRepository;

    @Autowired // System under Test
    LigaController ligaController;

    @Autowired
    MockMvc mockMvc;

    // Default Test
    @Test
    void assertSetupWorks() {
        assertTrue(true);
    }

    // Test 1
    // Testet die PostRequest von einem gültigen Liga Objekt und gibt uns einen 200
    // Status Successful
    @Test
    void testCorrectPostRequest_ReturnsOk() throws Exception {

        JSONObject json = new JSONObject();
        json.put("name", "Testname");
        json.put("land", "Switzerland");

        mockMvc.perform(post("/liga/")
                .content(json.toString())
                .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().is2xxSuccessful());
    }

    // Test 2
    // Testet einen Post Request mit einem nicht vorhandenen Json Objekt und gibt
    // uns eine 4xx Client Error
    @Test
    void testeLeereLigaPostParameter_400erError() throws Exception {

        mockMvc.perform(post("/liga/"))
                .andExpect(status().is4xxClientError());

    }

    // Test 3
    // Testet ein Post Request mit einem Liga Objekt das einen zu kurzen Namen hat
    // und gibt uns einen 4xx Client Error
    @Test
    void testeZuKurzeLigaPostParameter_400erError() throws Exception {

        JSONObject json = new JSONObject();
        json.put("name", "t");
        json.put("land", "Switzerland");
        mockMvc.perform(post("/liga/")
                .content(json.toString())
                .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().is4xxClientError());

    }

    // Test 4
    // Testet ob 2 erstellte Test Ligas richtig erfasst werden mit einem Get Request
    // für alle Ligas
    @Test
    void testGetLigas_ReturnsLigasList() throws Exception {
        // Erstelle einige Demo-Liga-Objekte für den Test
        Liga liga1 = new Liga();
        liga1.setId(1L);
        liga1.setName("Demo Liga 1");
        liga1.setLand("Switzerland");

        Liga liga2 = new Liga();
        liga2.setId(2L);
        liga2.setName("Demo Liga 2");
        liga2.setLand("Germany");

        List<Liga> ligaList = Arrays.asList(liga1, liga2);

        // Simuliere die Rückgabe dieser Liga-Liste vom Repository
        when(ligaRepository.findAll()).thenReturn(ligaList);

        // Führe den GET-Request aus und überprüfe das Ergebnis
        mockMvc.perform(get("/liga/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2))) // Prüft, ob die Liste 2 Elemente enthält
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Demo Liga 1")))
                .andExpect(jsonPath("$[0].land", is("Switzerland")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("Demo Liga 2")))
                .andExpect(jsonPath("$[1].land", is("Germany")));
    }

}
