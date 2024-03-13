package com.awesomeorg.cinemaapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    // Custom query to find tickets by client email address
    @Query(value = "SELECT t.* FROM tickets t JOIN clients c ON t.client_id = c.id WHERE c.email_address = :emailAddress", nativeQuery = true)
    List<Ticket> findTicketsByClientEmail(@Param("emailAddress") String emailAddress);

    // Custom query to find client by ID and fetch reservations eagerly
    @Query("SELECT c FROM clients c LEFT JOIN FETCH c.reservations r WHERE c.id = :clientId")
    Optional<Client> findClientByIdAndFetchReservations(@Param("clientId") Long clientId);

    // Custom query to find reservations by client ID
    @Query(value = "SELECT r.* FROM reservations r WHERE r.reservations_client_id = :clientId", nativeQuery = true)
    List<Reservation> findReservationsByClientId(@Param("clientId") Long clientId);

    // Custom query to find tickets by client ID
    @Query(value = "SELECT t.* FROM tickets t WHERE t.client_id = :clientId", nativeQuery = true)
    List<Ticket> findTicketsByClientId(@Param("clientId") Long clientId);

}
