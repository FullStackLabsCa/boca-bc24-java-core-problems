package jdbc.trades.exceptions;

public class InvalidThresholdValueException extends RuntimeException {
    public InvalidThresholdValueException(String message) {
        super(message);
    }
}
