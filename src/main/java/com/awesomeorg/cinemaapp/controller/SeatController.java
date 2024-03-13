package com.awesomeorg.cinemaapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seats")
@RequiredArgsConstructor
public class SeatController {

    private final SeatService seatService;

    // Get seat recommendations
    // Get and return seat recommendations
    @GetMapping("/recommend")
    public ResponseEntity<List<Seat>> getSeatRecommendations(@RequestParam Long movieId, @RequestParam Integer numberOfTickets) {
        List<Seat> recommendedSeats = seatService.getRecommendedSeats(movieId, numberOfTickets);
        return ResponseEntity.ok(recommendedSeats);
    }

    // Generate occupied seats
    // Generate and return occupied seats
    @PostMapping(value = "/generate-occupied-seats", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Seat>> generateOccupiedSeats(@RequestBody SeatOccupancyRequest request) {
        List<Seat> occupiedSeats = seatService.generateAndSaveOccupiedSeats(request);
        return ResponseEntity.ok(occupiedSeats);
    }
}
