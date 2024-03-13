package com.awesomeorg.cinemaapp.repository;

import com.awesomeorg.cinemaapp.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InternalReservationRepository extends JpaRepository<Reservation, Long>, JpaSpecificationExecutor<Reservation> {

    // Custom query to find reservations by client ID
    List<Reservation> findReservationsByClientId(Long clientId);
}
