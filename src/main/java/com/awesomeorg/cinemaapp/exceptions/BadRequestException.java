package com.awesomeorg.cinemaapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BadRequestException extends ResponseStatusException {
    public BadRequestException(final String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
