package tradingexample.exceptiontrading;

public class HitErrorsThresholdException extends RuntimeException {
    public HitErrorsThresholdException(String message) {
        super(message);
    }
}
