package com.awesomeorg.cinemaapp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity(name = "movies")
@NoArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //  Unique identifier for the movie.

    private String title; // Movie title.

    @Enumerated(EnumType.STRING)
    private Genre genre; // Movie genre.

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Seat> seats; // Represents a list of seats in the cinema associated with the specific movie.


    @Enumerated(EnumType.STRING)
    private Language language; // Movie language.

    private int limitAge; // Age limit for the movie.

    private String author; //  Movie author.

    private LocalDate date; // Release date of the movie.

    private int popularity; // Popularity rating of the movie.

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("movie") // Ignore movie property during serialization to avoid infinite loop
    private List<Showtime> showtimes; // List of showtimes associated with the movie.

    public Movie(String title, Genre genre, Language language, int limitAge, String author, LocalDate date) {
        this.title = title;
        this.genre = genre;
        this.language = language;
        this.limitAge = limitAge;
        this.author = author;
        this.date = date;
    }

    public Movie(Long movieId) {
    }

    // Nested enums for language and genre
    public enum Language {
        ENGLISH,
        RUSSIAN,
        ESTONIAN
    }

    public enum Genre {
        ACTION,
        COMEDY,
        DRAMA,
        SCIFI,
        HORROR,
        ROMANCE,
        THRILLER
    }
}
