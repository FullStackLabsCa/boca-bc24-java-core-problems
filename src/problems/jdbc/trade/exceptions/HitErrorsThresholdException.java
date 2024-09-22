package jdbc.trade.exceptions;

public class HitErrorsThresholdException extends RuntimeException {
    public HitErrorsThresholdException(String message) {
        super(message);
    }
}