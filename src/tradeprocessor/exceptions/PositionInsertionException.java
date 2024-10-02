package tradeprocessor.exceptions;

public class PositionInsertionException extends RuntimeException {
    public PositionInsertionException(String message) {
        super(message);
    }
  public PositionInsertionException(String message,Throwable cause) {
    super(message,cause);
  }
}
