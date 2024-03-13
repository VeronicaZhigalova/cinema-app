package com.awesomeorg.cinemaapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    // Custom query to find free tickets by date of purchase
    @Query(value = "SELECT * FROM tickets t WHERE t.date_of_purchase = :dateOfPurchase", nativeQuery = true)
    Page<Ticket> findFreeTicket(@Param("dateOfPurchase") LocalDate dateOfPurchase, Pageable pageRequest);

    // Custom query to find tickets by showtime ID
    @Query(value = "SELECT * FROM tickets t WHERE t.showtime_id = :showtimeId", nativeQuery = true)
    List<Ticket> findByShowtimeId(@Param("showtimeId") Long showtimeId);
}
