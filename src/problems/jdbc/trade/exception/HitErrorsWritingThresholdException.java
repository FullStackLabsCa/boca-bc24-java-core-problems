package problems.jdbc.trade.exception;

// Custom exception for optimistic locking failure
public class HitErrorsWritingThresholdException extends RuntimeException {
    public HitErrorsWritingThresholdException(String message) {
        super(message);
    }
}