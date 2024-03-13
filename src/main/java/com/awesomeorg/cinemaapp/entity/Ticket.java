package com.awesomeorg.cinemaapp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Entity(name = "tickets")
@NoArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Unique identifier for the ticket.

    @ManyToOne
    @JoinColumn(name = "showtime_id")
    private Showtime showtime; // Showtime associated with the ticket.
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client; //  Client associated with the ticket.

    @ManyToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation; // Reservation associated with the ticket.

    @ManyToOne
    @JoinColumn(name = "seat_id")
    private Seat seat; // Seat associated with the ticket.

    private Integer price; // Price of the ticket.

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateOfPurchase; // Date of ticket purchase.

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateOfMovie; // Date of the movie for which the ticket is purchased.

    @Enumerated(EnumType.STRING)
    private TicketType ticketType; // Type of the ticket.
    public Ticket(Showtime showtime, Client client, Seat seat, Integer price,
                  LocalDateTime dateOfPurchase, LocalDateTime dateOfMovie, TicketType ticketType) {
        this.showtime = showtime;
        this.client = client;
        this.seat = seat;
        this.price = price;
        this.dateOfPurchase = dateOfPurchase;
        this.dateOfMovie = dateOfMovie;
        this.ticketType = ticketType;
    }

    public void setSeatId(Long seatId) {
    }

    public void setClientId(Long clientId) {
    }

    public void setShowtimeId(Long showtimeId) {
    }


    // Nested enum for ticket type
    public enum TicketType {
        KID,
        TEENAGER,
        ADULT18
    }
}

