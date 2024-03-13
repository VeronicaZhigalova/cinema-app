package com.awesomeorg.cinemaapp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/internal/reservations")
@RequiredArgsConstructor
public class InternalReservationController {

    private final ReservationService reservationService;

    // Create a new reservation internally
    // Create a reservation and return the created reservation
    @PostMapping("/create")
    public ResponseEntity<Reservation> createReservation(@RequestBody CreateReservationRequest request,
                                                         @RequestParam Long clientId) {
        Reservation reservation = reservationService.createReservation(request, clientId);
        return new ResponseEntity<>(reservation, HttpStatus.CREATED);
    }

    // Cancel a reservation by ID internally
    // Cancel a reservation by ID internally and return a message of successful cancellation
    @DeleteMapping("/cancel/{reservationId}")
    public ResponseEntity<String> cancelReservation(@PathVariable Long reservationId) {
        reservationService.cancelReservation(reservationId);
        return ResponseEntity.ok("Reservation canceled successfully");
    }

    // Update reservation information by ID internally
    // Update reservation information internally and return the updated reservation
    @PutMapping("/{reservationId}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable Long reservationId,
                                                         @Valid @RequestBody UpdateReservationRequest request,
                                                         @RequestParam Long clientId) {
        Reservation updatedReservation = reservationService.updateReservation(reservationId, request, clientId);
        return ResponseEntity.ok(updatedReservation);
    }


    // Get the cinema schedule internally
    // Get and return the cinema schedule internally
    @GetMapping("/schedule")
    public ResponseEntity<List<Showtime>> getCinemaSchedule() {
        List<Showtime> schedule = reservationService.getCinemaSchedule();
        return ResponseEntity.ok(schedule);
    }

    // Get movie recommendations based on the history of a client internally
    // Get and return movie recommendations based on the history of a client internally
    @GetMapping("/recommendations/{clientId}")
    public ResponseEntity<List<Movie>> getRecommendationsBasedOnHistory(@PathVariable Long clientId) {
        List<Movie> recommendations = reservationService.getRecommendationsBasedOnHistory(clientId);
        return ResponseEntity.ok(recommendations);
    }
}

