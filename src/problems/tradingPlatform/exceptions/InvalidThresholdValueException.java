package problems.tradingPlatform.exceptions;


public class InvalidThresholdValueException extends Exception {

    public InvalidThresholdValueException () {

    }

    public InvalidThresholdValueException (String message) {
        super (message);
    }

    public InvalidThresholdValueException (Throwable cause) {
        super (cause);
    }

    public InvalidThresholdValueException (String message, Throwable cause) {
        super (message, cause);
    }
}