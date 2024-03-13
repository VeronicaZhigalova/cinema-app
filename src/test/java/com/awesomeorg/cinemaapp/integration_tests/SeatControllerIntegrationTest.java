package com.awesomeorg.cinemaapp.integration_tests;

import com.awesomeorg.cinemaapp.util.AbstractIntegrationTest;
import jakarta.transaction.Transactional;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class SeatControllerIntegrationTest extends AbstractIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @SneakyThrows
    @Transactional
    public void testGetSeatRecommendations() {
        Long movieId = 1L;
        Integer numberOfTickets = 2;

        mockMvc.perform(MockMvcRequestBuilders.get("/seats/recommend")
                        .param("movieId", String.valueOf(movieId))
                        .param("numberOfTickets", String.valueOf(numberOfTickets))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

}
