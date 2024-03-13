package com.awesomeorg.cinemaapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShowtimeService {

    private final TicketRepository ticketRepository;
    private final ShowtimeRepository showtimeRepository;
    private final SeatService seatService;

    // Getting all movies
    public List<Movie> getAllMovies() {
        return showtimeRepository.findAll().stream()
                .map(Showtime::getMovie)
                .distinct()
                .collect(Collectors.toList());
    }

    // Getting a movie by ID
    public Movie getMovieById(Long id) {
        return showtimeRepository.findById(id)
                .map(Showtime::getMovie)
                .orElse(null);
    }


    // Getting recommended movies for a client
    public List<Movie> getRecommendedMovies(Long clientId) {
        List<Movie> allMovies = showtimeRepository.findAllMovies();
        Set<String> userPreferences = new HashSet<>(showtimeRepository.findUserPreferences(clientId));
        Set<Movie> userViewHistory = new HashSet<>(showtimeRepository.findUserViewHistory(clientId));

        return allMovies.stream()
                .filter(movie -> !userViewHistory.contains(movie) && userPreferences.contains(movie.getGenre().toString()))
                .sorted(Comparator.comparingInt(Movie::getPopularity).reversed())
                .limit(5)
                .collect(Collectors.toList());
    }

    // Getting filtered movies
    public List<Showtime> getFilteredMovies(String genre, Integer ageLimit, LocalTime showDateTime, String language) {
        return showtimeRepository.findFilteredMovies(genre, ageLimit, showDateTime, language);
    }

    // Getting seat recommendations for a showtime
    public List<Seat> getSeatsRecommendation(Long showtimeId, int numOfTickets) {
        return seatService.getRecommendedSeats(showtimeId, numOfTickets);
    }

//    // Finding a showtime by ID
//    public Showtime findShowtimeById(Long id) {
//        return showtimeRepository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Showtime not found with ID: " + id));
//    }

}

