package com.awesomeorg.cinemaapp.protocol;

import com.awesomeorg.cinemaapp.entity.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class UpdateReservationRequest {

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
//Purpose: Used for updating reservation information.
//Fields:
//showtime: Updated showtime associated with the reservation.
//client: Updated client associated with the reservation.
//seat: Updated seat associated with the reservation.
//ticket: Updated ticket associated with the reservation.
//tickets: Updated list of tickets associated with the reservation.
//bookingStatus: Updated status of the reservation.
//reservationDate: Updated date and time of the reservation.
