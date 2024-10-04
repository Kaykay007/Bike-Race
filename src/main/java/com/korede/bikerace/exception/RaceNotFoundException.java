package com.korede.bikerace.exception;

public class RaceNotFoundException extends RuntimeException {
    public RaceNotFoundException(String message) {
        super(message);
    }
}
