package com.awesomeorg.cinemaapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity(name = "seats")
@NoArgsConstructor
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Unique identifier for the seat.

    private Integer row; // Row number of the seat.

    private Integer number; // Seat number.

    @OneToMany(mappedBy = "seat", cascade = CascadeType.ALL)
    private List<Ticket> tickets; // List of tickets associated with the seat.

    @OneToMany(mappedBy = "seat", cascade = CascadeType.ALL)
    private List<Reservation> reservations; // List of reservations associated with the seat.

    @ManyToOne
    @JoinColumn(name = "movie_id")
    @JsonIgnoreProperties("seats") // Ignore seats property during serialization to avoid infinite loop
    private Movie movie; // Movie associated with the seat.

    private boolean occupied; // Flag indicating whether the seat is occupied.

    public Seat(Integer row, Integer number) {
        this.row = row;
        this.number = number;
        this.occupied = false;
    }

//    public Seat(int i, int i1, boolean b) {
//    }
}

