package JDBC.transactions.oopsCreditCard.exceptions;

public class OptimisticLockingException extends RuntimeException {
    public OptimisticLockingException(String message) {
        super(message);
    }
}
