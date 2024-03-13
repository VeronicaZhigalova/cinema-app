package com.awesomeorg.cinemaapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;

@Repository
public interface ShowtimeRepository extends JpaRepository<Showtime, Long> {
    @Query("SELECT DISTINCT s.movie FROM showtimes s WHERE s.movie.genre = :genre AND s.movie.limitAge = :ageLimit AND s.showtimeDateTime = :showtimeDateTime AND s.language = :language")
    List<Showtime> findFilteredMovies(String genre, Integer ageLimit, LocalTime showtimeDateTime, String language);

    @Query("SELECT DISTINCT s.movie FROM showtimes s")
    List<Movie> findAllMovies();

    @Query("SELECT DISTINCT s FROM showtimes s WHERE s.client.id = :clientId")
    int findUserPreferences(@Param("clientId") Long clientId);



    @Query("SELECT COUNT(DISTINCT s.movie.id) FROM showtimes s JOIN s.reservations r WHERE r.client.id = :clientId")
    int findUserViewHistory(@Param("clientId") Long clientId);
}
