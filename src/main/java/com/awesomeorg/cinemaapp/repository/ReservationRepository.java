package com.awesomeorg.cinemaapp.repository;

import com.awesomeorg.cinemaapp.entity.Movie;
import com.awesomeorg.cinemaapp.entity.Reservation;
import com.awesomeorg.cinemaapp.entity.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long>, JpaSpecificationExecutor<Reservation> {

    // Custom query to find movies by client ID
    @Query(value = "SELECT m.* FROM movies m LEFT JOIN reservations r ON m.id = r.showtime_movie_id WHERE r.client_id = :clientId", nativeQuery = true)
    List<Movie> findMoviesByClientId(@Param("clientId") Long clientId);

    // Custom query to find active showtimes
    @Query(value = "SELECT s.* FROM showtimes s LEFT JOIN reservations r ON s.id = r.showtime_id WHERE r.booking_status = 'CONFIRMED'", nativeQuery = true)
    List<Showtime> findActiveShowtimes();
}
