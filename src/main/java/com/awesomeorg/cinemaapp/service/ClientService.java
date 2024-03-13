package com.awesomeorg.cinemaapp.service;

import com.awesomeorg.cinemaapp.entity.Client;
import com.awesomeorg.cinemaapp.entity.Reservation;
import com.awesomeorg.cinemaapp.entity.Ticket;
import com.awesomeorg.cinemaapp.exceptions.ClientBlockedException;
import com.awesomeorg.cinemaapp.protocol.CreateClientRequest;
import com.awesomeorg.cinemaapp.protocol.UpdateClientRequest;
import com.awesomeorg.cinemaapp.repository.BlocklistedClientRepository;
import com.awesomeorg.cinemaapp.repository.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final BlocklistedClientRepository blocklistRepository;

    public List<Ticket> findTicketsByClientEmail(String emailAddress) {
        return clientRepository.findTicketsByClientEmail(emailAddress);
    }

    public Optional<Client> findClientByIdAndFetchReservations(Long clientId) {
        return clientRepository.findClientByIdAndFetchReservations(clientId);
    }

    public List<Reservation> findReservationsByClientId(Long clientId) {
        return clientRepository.findReservationsByClientId(clientId);
    }

    public List<Ticket> findTicketsByClientId(Long clientId) {
        return clientRepository.findTicketsByClientId(clientId);
    }

    // Creating a new client
    public Client createClient(CreateClientRequest request) {
        return clientRepository.save(new Client(request.getClientName(), request.getPhoneNumber(), request.getEmailAddress()));
    }

    // Deleting a client with a check for blockage
    public void deleteClient(Long clientId) {
        checkClientNotBlocked(clientId);
        clientRepository.deleteById(clientId);
    }

    // Updating client information
    public Client updateClient(Long clientId, UpdateClientRequest request) {
        Client existingClient = clientRepository.findById(clientId)
                .orElseThrow(() -> new EntityNotFoundException("Client not found with id: " + clientId));

        existingClient.setClientName(request.getClientName());
        existingClient.setPhoneNumber(request.getPhoneNumber());
        existingClient.setEmailAddress(request.getEmailAddress());

        return clientRepository.save(existingClient);
    }

    // Getting the reservation history of a client
    public List<Reservation> getClientHistory(Long clientId) {
        Optional<Client> clientOptional = clientRepository.findClientByIdAndFetchReservations(clientId);

        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();
            return client.getReservations();
        } else {
            return Collections.emptyList();
        }
    }


    // Getting a client with their reservations
    public Optional<Client> getClientWithReservations(Long clientId) {
        return clientRepository.findClientByIdAndFetchReservations(clientId);
    }

    // Converting a ticket to a reservation
    private Reservation convertTicketToReservation(Ticket ticket) {
        Reservation reservation = new Reservation();
        reservation.setClient(ticket.getClient());
        reservation.setShowtime(ticket.getShowtime());
        reservation.setSeat(ticket.getSeat());
        reservation.setBookingStatus(Reservation.BookingStatus.FINISHED);
        reservation.setReservationDate(ticket.getDateOfMovie());

        return reservation;
    }

    // Checking if the client is not blocked
    private void checkClientNotBlocked(Long clientId) {
        if (blocklistRepository.findByClientId(clientId).isPresent()) {
            throw new ClientBlockedException("Client is blocklisted and cannot perform the operation");
        }
    }
}
