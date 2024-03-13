package com.awesomeorg.cinemaapp.service;

import com.awesomeorg.cinemaapp.entity.*;
import com.awesomeorg.cinemaapp.protocol.CreateReservationRequest;
import com.awesomeorg.cinemaapp.protocol.UpdateReservationRequest;
import com.awesomeorg.cinemaapp.repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ClientRepository clientRepository;
    private final TicketRepository ticketRepository;
    private final MovieRepository movieRepository;
    private final ShowtimeRepository showtimeRepository;

    // Creating a reservation
    public Reservation createReservation(CreateReservationRequest request, Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new EntityNotFoundException("Client not found with ID: " + clientId));

        // Instead of findShowtime and findTicket methods, directly retrieve them from the repositories
        Showtime showtime = showtimeRepository.findById(request.getShowtimeId())
                .orElseThrow(() -> new EntityNotFoundException("Showtime not found with ID: " + request.getShowtimeId()));
        Ticket ticket = ticketRepository.findById(request.getTicketId())
                .orElseThrow(() -> new EntityNotFoundException("Ticket not found with ID: " + request.getTicketId()));

        Reservation reservation = new Reservation();
        reservation.setBookingStatus(request.getBookingStatus());
        reservation.setClient(client);
        reservation.setShowtime(showtime);
        reservation.setTicket(ticket);

        return reservationRepository.save(reservation);
    }


    // Adding a method to find a reservation by ID
    private Optional<Reservation> findReservation(Long reservationId) {
        return reservationRepository.findById(reservationId);
    }

    // Canceling a reservation
    public void cancelReservation(Long reservationId) {
        // Using the findReservation method to check if the reservation exists before attempting deletion
        findReservation(reservationId).ifPresent(reservation -> {
            // Delete the reservation if it exists
            reservationRepository.delete(reservation);
        });
    }


    // Getting the active cinema schedule
    public List<Showtime> getCinemaSchedule() {
        return reservationRepository.findActiveShowtimes();
    }

    // Getting movie recommendations based on viewing history
    public List<Movie> getRecommendationsBasedOnHistory(Long clientId) {
        List<Movie> userViewHistory = reservationRepository.findMoviesByClientId(clientId);

        return movieRepository.findAll().stream()
                .filter(movie -> !userViewHistory.contains(movie))
                .collect(Collectors.toList());
    }


    // Updating reservation information
    public Reservation updateReservation(Long reservationId, UpdateReservationRequest request, Long clientId) {
        // Checking if the reservation exists
        Reservation existingReservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new EntityNotFoundException("Reservation not found with ID: " + reservationId));

        // Checking if the client exists
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new EntityNotFoundException("Client not found with ID: " + clientId));

        // Updating reservation details
        existingReservation.setShowtime(request.getShowtime());
        existingReservation.setClient(client);
        existingReservation.setSeat(request.getSeat());
        existingReservation.setBookingStatus(request.getBookingStatus());
        existingReservation.setReservationDate(LocalDateTime.now());

        // Saving and returning the updated reservation
        Reservation updatedReservation = reservationRepository.save(existingReservation);

        if (updatedReservation == null) {
            throw new RuntimeException("Failed to update reservation");
        }

        return updatedReservation;
    }
}

