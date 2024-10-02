package tradeprocessor.exceptions;

public class AccountVersionException extends RuntimeException {
    public AccountVersionException(String message) {
        super(message);
    }
  public AccountVersionException(String message,Throwable cause) {
    super(message,cause);
  }

}
