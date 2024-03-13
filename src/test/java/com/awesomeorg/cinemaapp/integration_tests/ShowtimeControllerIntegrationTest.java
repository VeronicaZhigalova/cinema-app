package com.awesomeorg.cinemaapp.integration_tests;

import com.awesomeorg.cinemaapp.protocol.TicketQuery;
import com.awesomeorg.cinemaapp.util.AbstractIntegrationTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ShowtimeControllerIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @SneakyThrows
    @Transactional
    public void testFindTicket() {
        TicketQuery query = new TicketQuery();
        mockMvc.perform(get("/showtimes/find")
                        .param("pageNumber", "0")
                        .param("pageSize", "25")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(query)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }


    @Test
    @SneakyThrows
    @Transactional
    public void testDeleteTicket() {
        Long ticketId = 1L;
        mockMvc.perform(delete("/showtimes/delete/{ticketId}", ticketId))
                .andExpect(status().isNoContent());
    }

    @Test
    @SneakyThrows
    @Transactional
    public void testGetAllMovies() {
        mockMvc.perform(get("/showtimes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @SneakyThrows
    @Transactional
    public void testGetMovieById() {
        Long movieId = 1L;
        mockMvc.perform(get("/showtimes/{id}", movieId))
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    @Transactional
    public void testGetSeatsRecommendation() {
        Long showtimeId = 1L;
        int numOfTickets = 2;
        mockMvc.perform(get("/showtimes/{showtimeId}/seats-recommendation/{numOfTickets}", showtimeId, numOfTickets))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
