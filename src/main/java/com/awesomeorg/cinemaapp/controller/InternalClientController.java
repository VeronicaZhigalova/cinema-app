package com.awesomeorg.cinemaapp.controller;

import com.awesomeorg.cinemaapp.entity.Client;
import com.awesomeorg.cinemaapp.entity.Reservation;
import com.awesomeorg.cinemaapp.protocol.CreateClientRequest;
import com.awesomeorg.cinemaapp.protocol.UpdateClientRequest;
import com.awesomeorg.cinemaapp.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/internal/clients")
@RequiredArgsConstructor
public class InternalClientController {

    private final ClientService clientService;

    // Create a new client internally
    // Create a client and return the created client
    @PostMapping("/create")
    public ResponseEntity<Client> createClient(@RequestBody CreateClientRequest request) {
        Client client = clientService.createClient(request);
        return new ResponseEntity<>(client, HttpStatus.CREATED);
    }

    // Delete a client by ID internally
    // Delete the client internally by ID and return a message of successful deletion
    @DeleteMapping("/delete/{clientId}")
    public ResponseEntity<String> deleteClient(@PathVariable Long clientId) {
        clientService.deleteClient(clientId);
        return ResponseEntity.ok("Client deleted successfully");
    }

    // Update client information by ID internally
    // Update client information internally and return the updated client
    @PutMapping("/update/{clientId}")
    public ResponseEntity<Client> updateClient(@PathVariable Long clientId,
                                               @RequestBody UpdateClientRequest request) {
        Client updatedClient = clientService.updateClient(clientId, request);
        return ResponseEntity.ok(updatedClient);
    }

    // Get the reservation history of a client by ID internally
    // Get and return the reservation history of the client by ID internally
    @GetMapping("/history/{clientId}")
    public ResponseEntity<List<Reservation>> getClientHistory(@PathVariable Long clientId) {
        List<Reservation> history = clientService.getClientHistory(clientId);
        return ResponseEntity.ok(history);
    }
}

