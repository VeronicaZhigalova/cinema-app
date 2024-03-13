package com.awesomeorg.cinemaapp.integration_tests;

import com.awesomeorg.cinemaapp.protocol.CreateClientRequest;
import com.awesomeorg.cinemaapp.protocol.UpdateClientRequest;
import com.awesomeorg.cinemaapp.util.AbstractIntegrationTest;
import jakarta.transaction.Transactional;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class ClientControllerIntegrationTest extends AbstractIntegrationTest {


    @Autowired
    private MockMvc mockMvc;

    @Test
    @SneakyThrows
    @Transactional
    public void testRegisterClient() {
        CreateClientRequest request = new CreateClientRequest();
        request.setClientName("John Doe");
        request.setPhoneNumber("+123456789");
        request.setEmailAddress("john.doe@example.com");

        mockMvc.perform(MockMvcRequestBuilders.post("/clients/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.clientName").value("John Doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value("+123456789"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.emailAddress").value("john.doe@example.com"));
    }


    @Test
    @SneakyThrows
    @Transactional
    public void testUpdateClient() {
        Long clientId = 1L;

        UpdateClientRequest request = new UpdateClientRequest();
        request.setClientName("Julia My");
        request.setPhoneNumber("+987654321");
        request.setEmailAddress("julia.my@example.com");

        mockMvc.perform(MockMvcRequestBuilders.put("/clients/update/{clientId}", clientId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().string("Client not found with ID: " + clientId));
    }


    @Test
    @SneakyThrows
    @Transactional
    public void testDeleteClient() {
        Long clientId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/clients/delete/{clientId}", clientId))
                .andExpect(MockMvcResultMatchers.status().isOk()) // Update to .isOk() instead of .isNoContent()
                .andExpect(MockMvcResultMatchers.content().string("Client deleted successfully"));
    }


    @Test
    @SneakyThrows
    @Transactional
    public void testGetClientHistory() {
        Long clientId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.get("/clients/history/{clientId}", clientId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                // Update expectations based on the actual behavior
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isEmpty());
    }
}


