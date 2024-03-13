package com.awesomeorg.cinemaapp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/showtimes")
@RequiredArgsConstructor
public class ShowtimeController {

    private final ShowtimeService showtimeService;
    private final TicketService ticketService;

    // Find tickets based on a query
    // Find and return tickets based on the query
    @GetMapping("/find")
    public ResponseEntity<Page<Ticket>> findTicket(@Valid final TicketQuery query,
                                                   @RequestParam(defaultValue = "0", required = false) final int pageNumber,
                                                   @RequestParam(defaultValue = "25", required = false) final int pageSize) {
        final Page<Ticket> tickets = ticketService.findFreeTicket(query, PageRequest.of(pageNumber, pageSize));
        return ResponseEntity.ok(tickets);
    }

    // Create a new ticket
    // Create a ticket and return the created ticket
    @PostMapping("/create")
    public ResponseEntity<Ticket> createTicket(@Valid @RequestBody final TicketQuery request) {
        List<Ticket> createdTickets = ticketService.createTicket(request);

        if (!createdTickets.isEmpty()) {
            Ticket createdTicket = createdTickets.get(0);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdTicket);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
    // Update ticket information by ID
    // Update ticket information and return the updated ticket or an error if the ticket is not found
    @PutMapping("/update/{ticketId}")
    public ResponseEntity<Ticket> updateTicket(@PathVariable Long ticketId, @RequestBody UpdateTicketRequest request) {
        if (ticketId == null) {
            throw new IllegalArgumentException("Ticket ID cannot be null");
        }
        Ticket updatedTicket = ticketService.updateTicket(ticketId, request);
        return ResponseEntity.ok(updatedTicket);
    }


    // Delete a ticket by ID
    // Delete a ticket by ID and return a successful result or an error if the ticket is not found
    @DeleteMapping("/delete/{ticketId}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long ticketId) {
        ticketService.deleteTicket(ticketId);
        return ResponseEntity.noContent().build();
    }


    // Get all movies
    // Get and return all movies
    @GetMapping()
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> allMovies = showtimeService.getAllMovies();
        return ResponseEntity.ok(allMovies);
    }

    // Get a movie by ID
    // Get and return a movie by ID
    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        Movie movie = showtimeService.getMovieById(id);
        return ResponseEntity.ok(movie);
    }

    // Get recommendations for movies based on the client's history
    // Retrieve recommended movies for the specified client
    @GetMapping("/recommendations")
    public ResponseEntity<List<Movie>> getMovieRecommendations(@RequestParam Long clientId) {
        List<Movie> recommendedMovies = showtimeService.getRecommendedMovies(clientId);
        return ResponseEntity.ok(recommendedMovies);
    }

    // Get movies based on filtering criteria
    // Retrieve movies based on the specified filtering criteria
    @GetMapping("/filter")
    public ResponseEntity<List<Showtime>> getFilteredMovies(
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) Integer ageLimit,
            @RequestParam(required = false) LocalTime startTime,
            @RequestParam(required = false) String language
    ) {
        List<Showtime> filteredMovies = showtimeService.getFilteredMovies(genre, ageLimit, startTime, language);
        return ResponseEntity.ok(filteredMovies);
    }

    // Get seat recommendations for a specific showtime and number of tickets
    // Retrieve seat recommendations for the specified showtime and number of tickets
    @GetMapping("/{showtimeId}/seats-recommendation/{numOfTickets}")
    public ResponseEntity<List<Seat>> getSeatsRecommendation(
            @PathVariable Long showtimeId,
            @PathVariable int numOfTickets) {
        List<Seat> seatRecommendations = showtimeService.getSeatsRecommendation(showtimeId, numOfTickets);
        return ResponseEntity.ok(seatRecommendations);
    }
}
