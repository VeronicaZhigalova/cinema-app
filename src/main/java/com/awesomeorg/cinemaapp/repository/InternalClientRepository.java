package com.awesomeorg.cinemaapp.repository;

import com.awesomeorg.cinemaapp.entity.Client;
import com.awesomeorg.cinemaapp.entity.Reservation;
import com.awesomeorg.cinemaapp.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InternalClientRepository extends JpaRepository<Client, Long> {

    // Custom query to find tickets by client email address
    @Query(value = "SELECT t.* FROM tickets t " +
            "INNER JOIN clients c ON t.client_id = c.id " +
            "WHERE c.email_address = :emailAddress", nativeQuery = true)
    List<Ticket> findTicketsByClientEmail(@Param("emailAddress") String emailAddress);

    // Custom query to find reservations by client ID
    @Query(value = "SELECT r.* FROM reservations r " +
            "INNER JOIN clients c ON r.client_id = c.id " +
            "WHERE c.id = :clientId", nativeQuery = true)
    List<Reservation> findReservationsByClientId(@Param("clientId") Long clientId);

    // Custom query to find tickets by client ID
    @Query(value = "SELECT t.* FROM tickets t " +
            "WHERE t.client_id = :clientId", nativeQuery = true)
    List<Ticket> findTicketsByClientId(@Param("clientId") Long clientId);
}
