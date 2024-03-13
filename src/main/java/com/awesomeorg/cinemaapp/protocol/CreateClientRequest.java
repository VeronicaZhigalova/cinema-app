package com.awesomeorg.cinemaapp.protocol;

import lombok.Data;

@Data
public class CreateClientRequest {
    private String clientName;
    private String phoneNumber;
    private String emailAddress;

    public CreateClientRequest(String clientName, String phoneNumber, String emailAddress) {
        this.clientName = clientName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    public CreateClientRequest() {

    }

}

//Purpose: Used for creating a new client.
//Fields:
//clientName: Name of the client.
//phoneNumber: Phone number of the client.
//emailAddress: Email address of the client.


