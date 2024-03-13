package com.awesomeorg.cinemaapp.controller;

import com.awesomeorg.cinemaapp.entity.Client;
import com.awesomeorg.cinemaapp.entity.Reservation;
import com.awesomeorg.cinemaapp.protocol.CreateClientRequest;
import com.awesomeorg.cinemaapp.protocol.UpdateClientRequest;
import com.awesomeorg.cinemaapp.service.ClientService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    // Create a new client
    // Register the client and return the created client
    @PostMapping("/register")
    public ResponseEntity<Client> registerClient(@Valid @RequestBody final CreateClientRequest request) {
        final Client created = clientService.createClient(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // Delete a client by ID
    // Delete the client by ID and return a message of successful deletion or an error if the client is not found
    @DeleteMapping("/delete/{clientId}")
    public ResponseEntity<String> deleteClient(@PathVariable Long clientId) {
        try {
            clientService.deleteClient(clientId);
            return ResponseEntity.ok("Client deleted successfully");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found with ID: " + clientId);
        }
    }

    // Update client information by ID
    // Update client information and return the updated client or an error message if the client is not found
    @PutMapping("/update/{clientId}")
    public ResponseEntity<?> updateClient(@PathVariable Long clientId,
                                          @Valid @RequestBody UpdateClientRequest request) {
        try {
            Client client = clientService.updateClient(clientId, request);
            return ResponseEntity.ok(client);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found with ID: " + clientId);
        }
    }



    // Get the reservation history of a client by ID
    // Get and return the reservation history of the client by ID
    @GetMapping("/history/{clientId}")
    public ResponseEntity<List<Reservation>> getClientHistory(@PathVariable Long clientId) {
        List<Reservation> clientHistory = clientService.getClientHistory(clientId);
        return ResponseEntity.ok(clientHistory);
    }
}

