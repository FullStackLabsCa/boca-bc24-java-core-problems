package problems.jdbc.trade.exception;

// Custom exception for optimistic locking failure
public class HitErrorsReadingThresholdException extends RuntimeException {
    public HitErrorsReadingThresholdException(String message) {
        super(message);
    }
}