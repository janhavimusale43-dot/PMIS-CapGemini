package com.clinic.emergency.exception;

public class BedUnavailableException extends RuntimeException {
    public BedUnavailableException(String message) {
        super(message);
    }
}
