package com.hospital.management.system.exceptions;

public class InvalidRoomTypeException extends RuntimeException {
    public InvalidRoomTypeException(String message) {
        super(message);
    }
}
