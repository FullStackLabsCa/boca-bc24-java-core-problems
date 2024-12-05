package problems.trading.customexceptions;

public class ReadThresholdHitException extends RuntimeException {
    public ReadThresholdHitException(String message) {
        super(message);
    }
}
