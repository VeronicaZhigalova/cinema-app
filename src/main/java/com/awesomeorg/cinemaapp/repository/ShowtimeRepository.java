package com.awesomeorg.cinemaapp.repository;

import com.awesomeorg.cinemaapp.entity.Movie;
import com.awesomeorg.cinemaapp.entity.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;

@Repository
public interface ShowtimeRepository extends JpaRepository<Showtime, Long> {

    // Find movies that match specified criteria (genre, age limit, showtime, language).
    @Query("SELECT DISTINCT s.movie FROM showtimes s WHERE s.movie.genre = :genre AND s.movie.limitAge = :ageLimit AND s.showtimeDateTime = :showtimeDateTime AND s.language = :language")
    List<Showtime> findFilteredMovies(String genre, Integer ageLimit, LocalTime showtimeDateTime, String language);

    // Find all unique movies presented in the showtimes schedule.
    @Query("SELECT DISTINCT s.movie FROM showtimes s")
    List<Movie> findAllMovies();

    // Find user preferences based on showtimes schedule (movies for which the user has purchased tickets).
    @Query("SELECT DISTINCT s FROM showtimes s WHERE s.client.id = :clientId")
    int findUserPreferences(@Param("clientId") Long clientId);


    // Find the count of unique movies viewed by the user through purchased tickets.
    @Query("SELECT COUNT(DISTINCT s.movie.id) FROM showtimes s JOIN s.reservations r WHERE r.client.id = :clientId")
    int findUserViewHistory(@Param("clientId") Long clientId);
}
