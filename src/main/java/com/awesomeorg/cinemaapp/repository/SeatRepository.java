package com.awesomeorg.cinemaapp.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {

    // Custom query to find free seats by movie ID
    @Query(value = "SELECT * FROM seats s WHERE s.movie_id = :movieId AND s.occupied = false", nativeQuery = true)
    List<Seat> findFreeSeatsByMovieId(@Param("movieId") Long movieId, Pageable pageRequest);
}
