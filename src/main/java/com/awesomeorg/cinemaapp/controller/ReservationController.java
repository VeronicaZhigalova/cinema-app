package com.awesomeorg.cinemaapp.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    // Create a new reservation
    // Create a reservation and return the created reservation or an error message
    @PostMapping("/create")
    public ResponseEntity<?> createReservation(
            @Valid @RequestBody final CreateReservationRequest request,
            @RequestHeader(HeaderConstants.CLIENT_ID_HEADER) Long passengerId) {
        try {
            final Reservation reservation = reservationService.createReservation(request, passengerId);
            return ResponseEntity.status(HttpStatus.CREATED).body(reservation);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity not found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

    // Update reservation information by ID
    // Update reservation information and return the updated reservation
    @PutMapping("/{reservationId}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable Long reservationId,
                                                         @Valid @RequestBody UpdateReservationRequest request,
                                                         @RequestHeader(HeaderConstants.CLIENT_ID_HEADER) Long clientId) {
        Reservation updatedReservation = reservationService.updateReservation(reservationId, request, clientId);
        return ResponseEntity.ok(updatedReservation);
    }

    // Cancel a reservation by ID
    // Cancel a reservation by ID and return a successful result or an error message
    @DeleteMapping("/cancel/{reservationId}")
    public ResponseEntity<Void> cancelReservation(@PathVariable Long reservationId) {
        try {
            reservationService.cancelReservation(reservationId);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Get the cinema schedule
    // Get and return the cinema schedule
    @GetMapping("/cinema-schedule")
    public ResponseEntity<List<Showtime>> getCinemaSchedule() {
        List<Showtime> cinemaSchedule = reservationService.getCinemaSchedule();
        return ResponseEntity.ok(cinemaSchedule);
    }

    // Get movie recommendations based on the history of a client
    // Get and return movie recommendations based on the history of a client
    @GetMapping("/recommendations/{clientId}")
    public ResponseEntity<List<Movie>> getRecommendationsBasedOnHistory(@PathVariable Long clientId) {
        List<Movie> recommendations = reservationService.getRecommendationsBasedOnHistory(clientId);
        return ResponseEntity.ok(recommendations);
    }
}

