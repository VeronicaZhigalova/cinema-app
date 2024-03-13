package com.awesomeorg.cinemaapp.protocol;

import com.awesomeorg.cinemaapp.entity.Ticket;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TicketQuery {

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

    public Long getShowtimeId() {
        return showtimeId;
    }

    public void setShowtimeId(Long showtimeId) {
        this.showtimeId = showtimeId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getSeatId() {
        return seatId;
    }

    public void setSeatId(Long seatId) {
        this.seatId = seatId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public LocalDateTime getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(LocalDateTime dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public LocalDateTime getDateOfMovie() {
        return dateOfMovie;
    }

    public void setDateOfMovie(LocalDateTime dateOfMovie) {
        this.dateOfMovie = dateOfMovie;
    }

    public Ticket.TicketType getTicketType() {
        return ticketType;
    }

    public void setTicketType(Ticket.TicketType ticketType) {
        this.ticketType = ticketType;
    }
}
//Purpose: Represents a query for retrieving ticket information.
//Fields:
//showtimeId: Identifier for the showtime.
//clientId: Identifier for the client.
//seatId: Identifier for the seat.
//price: Price of the ticket.
//dateOfPurchase: Date and time of ticket purchase.
//dateOfMovie: Date and time of the movie.
//ticketType: Type of the ticket.

