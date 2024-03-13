package com.awesomeorg.cinemaapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatService {

    private final SeatRepository seatRepository;
    // Getting recommended seats
    public List<Seat> getRecommendedSeats(Long movieId, Integer numberOfTickets) {
        List<Seat> freeSeats = seatRepository.findFreeSeatsByMovieId(movieId, PageRequest.of(0, numberOfTickets));
        return generateOccupiedSeatsLogic(freeSeats, movieId);
    }

    // Generating logic for occupied seats without saving them immediately
    private List<Seat> generateOccupiedSeatsLogic(List<Seat> freeSeats, Long movieId) {
        List<Seat> occupiedSeats = new ArrayList<>();

        for (Seat freeSeat : freeSeats) {
            Seat occupiedSeat = new Seat(freeSeat.getRow(), freeSeat.getNumber());
            occupiedSeat.setMovie(new Movie(movieId)); // Associate the seat with the movie
            occupiedSeat.setOccupied(true);
            occupiedSeats.add(occupiedSeat);
        }

        return occupiedSeats;
    }


    // Generating and saving occupied seats based on a request
    public List<Seat> generateAndSaveOccupiedSeats(SeatOccupancyRequest request) {
        Seat occupiedSeat = new Seat(request.getRow(), request.getNumber());
        occupiedSeat.setOccupied(true);
        seatRepository.save(occupiedSeat);
        return Collections.singletonList(occupiedSeat);
    }
}
