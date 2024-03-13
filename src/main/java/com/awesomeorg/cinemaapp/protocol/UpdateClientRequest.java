package com.awesomeorg.cinemaapp.protocol;

import lombok.Data;

@Data
public class UpdateClientRequest {

    private String clientName;

    private String phoneNumber;

    private String emailAddress;


}
//Purpose: Used for updating client information.
//Fields:
//clientName: Updated name of the client.
//phoneNumber: Updated phone number of the client.
//emailAddress: Updated email address of the client.
