package com.awesomeorg.cinemaapp.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "blocklisted_clients")
@NoArgsConstructor
public class BlocklistedClient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //Unique identifier for the blocked client.

    @Column(name = "reason", nullable = false)
    private String reason; // Reason for blocking the client.

    @OneToOne
    @JoinColumn(name = "client_id")
    private Client client; // Reference to the associated client.

    public BlocklistedClient(Long id, String reason, Client client) {
        this.id = id;
        this.reason = reason;
        this.client = client;
    }
}
