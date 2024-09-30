package io.reactivestax.jdbc.trades.exceptions;

public class InvalidThresholdValueException extends RuntimeException {
    public InvalidThresholdValueException(String message) {
        super(message);
    }
}
