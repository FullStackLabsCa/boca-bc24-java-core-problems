package tradeprocessor.exceptions;

public class PositionProcessingException extends RuntimeException {
    public PositionProcessingException(String message,Throwable cause) {
        super(message,cause);
    }
}
