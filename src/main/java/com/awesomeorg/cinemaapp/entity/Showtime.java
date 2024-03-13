package com.awesomeorg.cinemaapp.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity(name = "showtimes")
@NoArgsConstructor
public class Showtime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Unique identifier for the showtime.

    @Column(name = "language")
    private String language; // Language.

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie; // Movie associated with the showtime.

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client; // Client associated with the showtime.

    @OneToMany(mappedBy = "showtime", cascade = CascadeType.ALL)
    private List<Ticket> tickets; // List of tickets associated with the showtime.

    @OneToMany(mappedBy = "showtime", cascade = CascadeType.ALL)
    private List<Reservation> reservations; // List of reservations associated with the showtime.

    @Column(name = "showtimeDateTime")
    private String showtimeDateTime; // Showtime date.

    public Showtime(Movie movie) {
        this.movie = movie;
    }
}
