package tradeprocessor.exceptions;

public class ChunkProcessingException extends RuntimeException {
    public ChunkProcessingException(String message,Throwable cause) {
        super(message,cause);
    }
}
