package com.awesomeorg.cinemaapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    // Custom query to find movies by client ID
    @Query(value = "SELECT DISTINCT m.* FROM movies m JOIN reservations r ON m.id = r.movie_id WHERE r.client_id = :clientId", nativeQuery = true)
    List<Movie> findMoviesByClientId(@Param("clientId") Long clientId);
}
