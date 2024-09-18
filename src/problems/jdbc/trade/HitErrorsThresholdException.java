package problems.jdbc.trade;

// Custom exception for optimistic locking failure
public class HitErrorsThresholdException extends RuntimeException {
    public HitErrorsThresholdException(String message) {
        super(message);
    }
}