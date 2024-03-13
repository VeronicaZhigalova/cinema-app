package com.awesomeorg.cinemaapp.repository;

import com.awesomeorg.cinemaapp.entity.BlocklistedClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlocklistedClientRepository extends JpaRepository<BlocklistedClient, Long> {

    // Custom query to find a blocklisted client by client ID
    @Query("SELECT bc FROM blocklisted_clients bc WHERE bc.client.id = :clientId")
    Optional<BlocklistedClient> findByClientId(@Param("clientId") Long clientId);
}
