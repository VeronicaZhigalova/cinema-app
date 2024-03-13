package com.awesomeorg.cinemaapp.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity(name = "clients")
@NoArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Unique identifier for the client.

    private String clientName; // Client's name.

    private String phoneNumber; // Client's phone number.

    private String emailAddress; // Client's email address.

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Ticket> tickets; // List of tickets associated with the client.

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Reservation> reservations; // List of reservations associated with the client.

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "blocklisted_client_id")
    private BlocklistedClient blocklistedClient; // Information about the blocked status of the client.
    public Client(String clientName, String phoneNumber, String emailAddress) {
        this.clientName = clientName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }
}

