package jdbc.transactions.oopsCreditCard.exceptions;

public class SQLIntegrityConstraintViolationException extends RuntimeException {
    public SQLIntegrityConstraintViolationException(String message) {
        super(message);
    }
}
