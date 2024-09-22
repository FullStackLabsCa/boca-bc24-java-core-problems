package jdbc.trade.exceptions;

public class HitInsertErrorsThresholdException extends RuntimeException {
    public HitInsertErrorsThresholdException(String message) {
        super(message);
    }
}