package com.awesomeorg.cinemaapp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity(name = "reservations")
@NoArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Unique identifier for the reservation.

    @ManyToOne
    @JoinColumn(name = "showtime_id")
    private Showtime showtime; // Showtime associated with the reservation.

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client; // Client associated with the reservation.

    @ManyToOne
    @JoinColumn(name = "seat_id")
    private Seat seat; // Seat associated with the reservation.

    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket; // Ticket associated with the reservation.

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL)
    private List<Ticket> tickets; // List of tickets associated with the reservation.

    private BookingStatus bookingStatus; // Booking status of the reservation.

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime reservationDate; // Date of the reservation.


    public Reservation(Showtime showtime, Client client, Seat seat, BookingStatus bookingStatus, LocalDateTime reservationDate) {
        this.showtime = showtime;
        this.client = client;
        this.seat = seat;
        this.bookingStatus = bookingStatus;
        this.reservationDate = reservationDate;
    }

    // Nested enum for booking status
    public enum BookingStatus {
        PENDING,
        UPDATED,
        CONFIRMED,
        FINISHED
    }
}
