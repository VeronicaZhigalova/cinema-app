package com.awesomeorg.cinemaapp.protocol;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ReservationQuery {
    private Showtime showtime;
    private Client client;
    private Seat seat;
    private Ticket ticket;
    private List<Ticket> tickets;
    private Reservation.BookingStatus bookingStatus;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime reservationDate;

}

//Purpose: Represents a query for retrieving reservation information.
//Fields:
//showtime: Showtime associated with the reservation.
//client: Client associated with the reservation.
//seat: Seat associated with the reservation.
//ticket: Ticket associated with the reservation.
//tickets: List of tickets associated with the reservation.
//bookingStatus: Status of the reservation.
//reservationDate: Date and time of the reservation.
