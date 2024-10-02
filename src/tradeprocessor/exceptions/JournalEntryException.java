package tradeprocessor.exceptions;

public class JournalEntryException extends RuntimeException {
    public JournalEntryException(String message,Throwable cause) {
        super(message,cause);
    }
}
