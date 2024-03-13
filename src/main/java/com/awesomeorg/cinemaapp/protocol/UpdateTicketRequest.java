package com.awesomeorg.cinemaapp.protocol;

import com.awesomeorg.cinemaapp.entity.Ticket;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdateTicketRequest {

    @NotNull
    private Long showtimeId;

    @NotNull
    private Long clientId;

    @NotNull
    private Long seatId;

    private Integer price;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateOfPurchase;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateOfMovie;

    @Enumerated(EnumType.STRING)
    private Ticket.TicketType ticketType;
}
//Purpose: Used for updating ticket information.
//Fields:
//showtimeId: Updated identifier for the showtime.
//clientId: Updated identifier for the client.
//seatId: Updated identifier for the seat.
//price: Updated price of the ticket.
//dateOfPurchase: Updated date and time of ticket purchase.
//dateOfMovie: Updated date and time of the movie.
//ticketType: Updated type of the ticket.


