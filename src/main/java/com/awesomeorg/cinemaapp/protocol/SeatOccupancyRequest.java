package com.awesomeorg.cinemaapp.protocol;

import com.awesomeorg.cinemaapp.entity.Movie;
import com.awesomeorg.cinemaapp.entity.Reservation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.List;

@Data
public class SeatOccupancyRequest {

    private Integer row;

    private Integer number;

    private Integer tickets;

    private List<Reservation> reservations;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    @JsonIgnore
    private Movie movie;


    public SeatOccupancyRequest(Integer row, Integer number, Integer tickets, List<Reservation> reservations) {
        this.row = row;
        this.number = number;
        this.tickets = tickets;
        this.reservations = reservations;
    }

    public SeatOccupancyRequest() {

    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getTickets() {
        return tickets;
    }

    public void setTickets(Integer tickets) {
        this.tickets = tickets;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
//Purpose: Used for querying seat occupancy information.
//Fields:
//row: Row number of the seat.
//number: Seat number.
//tickets: Number of tickets.
//reservations: List of reservations.

