package tradingExample.exceptionTrading;

public class HitErrorsThresholdException extends RuntimeException {
    public HitErrorsThresholdException(String message) {
        super(message);
    }
}
