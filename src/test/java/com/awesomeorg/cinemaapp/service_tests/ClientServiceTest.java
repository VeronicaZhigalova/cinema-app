package com.awesomeorg.cinemaapp.service_tests;

import com.awesomeorg.cinemaapp.entity.BlocklistedClient;
import com.awesomeorg.cinemaapp.entity.Client;
import com.awesomeorg.cinemaapp.entity.Reservation;
import com.awesomeorg.cinemaapp.exceptions.ClientBlockedException;
import com.awesomeorg.cinemaapp.protocol.UpdateClientRequest;
import com.awesomeorg.cinemaapp.repository.BlocklistedClientRepository;
import com.awesomeorg.cinemaapp.repository.ClientRepository;
import com.awesomeorg.cinemaapp.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private BlocklistedClientRepository blocklistRepository;

    @InjectMocks
    private ClientService clientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }



    @Test
    void testDeleteClientNotBlocked() {
        // Arrange
        Long clientId = 1L;
        when(blocklistRepository.findByClientId(clientId)).thenReturn(empty());
        doNothing().when(clientRepository).deleteById(clientId);

        // Act
        assertDoesNotThrow(() -> clientService.deleteClient(clientId));
    }

    @Test
    void testDeleteClientBlocked() {
        // Arrange
        Long clientId = 1L;
        when(blocklistRepository.findByClientId(clientId)).thenReturn(of(new BlocklistedClient()));

        // Act and Assert
        assertThrows(ClientBlockedException.class, () -> clientService.deleteClient(clientId));
    }

    @Test
    void testUpdateClient() {
        Long clientId = 1L;
        UpdateClientRequest request = new UpdateClientRequest();
        request.setClientName("John");

        Optional<Client> optionalClient = of(new Client());

        when(clientRepository.findById(clientId)).thenReturn(optionalClient);
        when(clientRepository.save(ArgumentMatchers.any())).thenAnswer(invocation -> {
            Client savedClient = invocation.getArgument(0);
            return savedClient;
        });


        Client result = clientService.updateClient(clientId, request);

        verify(clientRepository, times(1)).findById(clientId);
        verify(clientRepository, times(1)).save(ArgumentMatchers.any());

        assertNotNull(result);
        assertEquals(request.getClientName(), result.getClientName());
    }
    @Test
    void testGetClientHistory() {
        // Arrange
        Long clientId = 1L;
        Client client = new Client();
        client.setReservations(Collections.singletonList(new Reservation()));
        when(clientRepository.findClientByIdAndFetchReservations(clientId)).thenReturn(of(client));

        // Act
        List<Reservation> result = clientService.getClientHistory(clientId);

        // Assert
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    void testGetClientWithReservations() {
        // Arrange
        Long clientId = 1L;
        Client client = new Client();
        client.setReservations(Collections.singletonList(new Reservation()));
        when(clientRepository.findClientByIdAndFetchReservations(clientId)).thenReturn(of(client));

        // Act
        Optional<Client> result = clientService.getClientWithReservations(clientId);

        // Assert
        assertTrue(result.isPresent());
    }

}

