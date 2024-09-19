package problems.tradingPlatform.exceptions;

public class HitErrorsThresholdException extends Exception {

    public HitErrorsThresholdException () {

    }

    public HitErrorsThresholdException (String message) {
        super (message);
    }

    public HitErrorsThresholdException (Throwable cause) {
        super (cause);
    }

    public HitErrorsThresholdException (String message, Throwable cause) {
        super (message, cause);
    }
}
