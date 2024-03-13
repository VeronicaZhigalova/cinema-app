package com.awesomeorg.cinemaapp.service;

import com.awesomeorg.cinemaapp.entity.Client;
import com.awesomeorg.cinemaapp.entity.Seat;
import com.awesomeorg.cinemaapp.entity.Ticket;
import com.awesomeorg.cinemaapp.exceptions.TicketAvailabilityExceededException;
import com.awesomeorg.cinemaapp.exceptions.TicketNotFoundException;
import com.awesomeorg.cinemaapp.protocol.CreateClientRequest;
import com.awesomeorg.cinemaapp.protocol.SeatOccupancyRequest;
import com.awesomeorg.cinemaapp.protocol.TicketQuery;
import com.awesomeorg.cinemaapp.protocol.UpdateTicketRequest;
import com.awesomeorg.cinemaapp.repository.ClientRepository;
import com.awesomeorg.cinemaapp.repository.SeatRepository;
import com.awesomeorg.cinemaapp.repository.ShowtimeRepository;
import com.awesomeorg.cinemaapp.repository.TicketRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final ClientRepository clientRepository;
    private final SeatRepository seatRepository;


    // Finding free tickets
    public Page<Ticket> findFreeTicket(TicketQuery query, PageRequest pageRequest) {
        LocalDate dateOfMovie = query.getDateOfMovie() != null ? LocalDate.from(query.getDateOfMovie()) : null;

        if (dateOfMovie == null) {
            // Handle the case when dateOfMovie is null, e.g., throw an exception or return an empty page
            return new PageImpl<>(Collections.emptyList(), pageRequest, 0);
        }

        List<Ticket> ticketList = ticketRepository.findFreeTicket(dateOfMovie, pageRequest)
                .filter(ticket -> ticket.getDateOfMovie().equals(dateOfMovie))
                .toList();

        return new PageImpl<>(ticketList, pageRequest, ticketList.size());
    }


    // Creating a ticket
    public List<Ticket> createTicket(TicketQuery ticketQuery) {
        LocalDate dateOfPurchase = LocalDateTime.now().toLocalDate();

        // Fetch existing tickets for the given showtime and date
        final Page<Ticket> existingTickets = ticketRepository.findFreeTicket(LocalDate.from(ticketQuery.getDateOfPurchase()), PageRequest.of(0, Integer.MAX_VALUE));

        int availableSeats = calculateAvailableSeats(existingTickets.getContent().size(), 100);

        if (availableSeats < ticketQuery.getSeatId()) {
            throw new TicketAvailabilityExceededException("Not enough available seats for purchase");
        }

        List<Ticket> newTickets = new ArrayList<>();

        for (int i = 0; i < ticketQuery.getSeatId(); i++) {
            if (i >= availableSeats) {
                break;
            }

            Ticket ticket = new Ticket();
            ticket.setShowtimeId(ticketQuery.getShowtimeId());
            ticket.setClientId(ticketQuery.getClientId());
            ticket.setSeatId((long) (existingTickets.getContent().size() + i + 1));
            ticket.setPrice(ticketQuery.getPrice());
            ticket.setDateOfPurchase(dateOfPurchase.atStartOfDay());
            ticket.setDateOfMovie(ticketQuery.getDateOfMovie());
            ticket.setTicketType(ticketQuery.getTicketType());

            newTickets.add(ticket);
        }

        List<Ticket> savedTickets = ticketRepository.saveAll(newTickets);

        return savedTickets;
    }


    private int calculateAvailableSeats(int existingTicketsCount, int totalSeatsLimit) {
        return Math.max(totalSeatsLimit - existingTicketsCount, 0);
    }

    // Getting a seat from the request
    private Seat getSeatFromRequest(Long seatId) {
        return seatRepository.findById(seatId)
                .orElseThrow(() -> new EntityNotFoundException("Seat not found with ID: " + seatId));
    }

    // Getting a client from the TicketQuery
    private Client getClientFromRequest(TicketQuery ticketQuery) {
        Long clientId = ticketQuery.getClientId();

        if (clientId != null) {
            return clientRepository.findById(clientId)
                    .orElseThrow(() -> new EntityNotFoundException("Client not found with ID: " + clientId));
        } else {
            throw new IllegalArgumentException("ClientId is required to create a ticket");
        }

    }


    public boolean existsById(Long ticketId) {
        return ticketRepository.existsById(ticketId);
    }

    // Updating ticket information
    public Ticket updateTicket(Long ticketId, UpdateTicketRequest request) {
        if (existsById(ticketId)) {
            Optional<Ticket> optionalTicket = ticketRepository.findById(ticketId);
            if (optionalTicket.isPresent()) {
                Ticket ticket = optionalTicket.get();
                // Update ticket properties based on UpdateTicketRequest
                ticket.setShowtimeId(request.getShowtimeId());
                ticket.setClientId(request.getClientId());
                ticket.setSeatId(request.getSeatId());
                ticket.setPrice(request.getPrice());
                ticket.setDateOfPurchase(request.getDateOfPurchase());
                ticket.setDateOfMovie(request.getDateOfMovie());
                ticket.setTicketType(request.getTicketType());

                return ticketRepository.save(ticket);
            } else {
                // Handle the case where the ticket is not found (optionalTicket is empty)
                throw new TicketNotFoundException("Ticket not found with ID: " + ticketId);
            }
        } else {
            // Handle the case where the ticket does not exist
            throw new TicketNotFoundException("Ticket not found with ID: " + ticketId);
        }
    }


    // Deleting a ticket by ID
    public void deleteTicket(Long ticketId) {
        ticketRepository.deleteById(ticketId);
    }

    // Getting a client from the CreateClientRequest
    private Client getClientFromRequest(CreateClientRequest request) {
        String clientName = request.getClientName();
        String phoneNumber = request.getPhoneNumber();
        String emailAddress = request.getEmailAddress();

        // Checking for at least one client parameter
        if (clientName != null || phoneNumber != null || emailAddress != null) {
            // Creating a new client or using an existing one
            return Optional.ofNullable(request.getClientName())
                    .map(clientId -> clientRepository.findById(Long.valueOf(clientId))
                            .orElseGet(() -> new Client(clientName, phoneNumber, emailAddress)))
                    .orElseGet(() -> new Client(clientName, phoneNumber, emailAddress));
        } else {
            // If client data is absent, return null or throw an exception
            return null;
        }
    }

    // Getting a seat from the request
    private Seat getSeatFromRequest(SeatOccupancyRequest request) {
        return Optional.ofNullable(request.getNumber())
                .map(number -> seatRepository.save(new Seat(request.getRow(), number)))
                .orElse(null);
    }
}

