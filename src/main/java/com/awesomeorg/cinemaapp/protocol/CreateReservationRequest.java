package com.awesomeorg.cinemaapp.protocol;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateReservationRequest {
    @NotNull
    private Long showtimeId;

    @NotNull
    private Long clientId;

    @NotNull
    private Long seatId;

    private Long ticketId;

    private Reservation.BookingStatus bookingStatus;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime reservationDate;
}

//Purpose: Used for creating a new reservation.
//Fields:
//showtimeId: Identifier for the showtime.
//clientId: Identifier for the client.
//seatId: Identifier for the seat.
//ticketId: Identifier for the ticket (optional).
//bookingStatus: Status of the reservation (optional).
//reservationDate: Date and time of the reservation.




