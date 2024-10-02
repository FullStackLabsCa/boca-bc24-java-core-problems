package tradeprocessor.exceptions;

public class TradeProcessingException extends RuntimeException {
    public TradeProcessingException(String message,Throwable cause) {
        super(message,cause);
    }
}
