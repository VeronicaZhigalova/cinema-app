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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@AutoConfigureMockMvc
public class ReservationControllerIntegrationTest extends AbstractIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @SneakyThrows
    @Transactional
    public void cancelReservation_ValidRequest_ReturnsNoContentStatus(){
        // Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.delete("/reservations/cancel/{reservationId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

}
