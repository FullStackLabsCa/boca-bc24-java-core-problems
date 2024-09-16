package problems.creditcard.exception;

public class SQLIntegrityConstraintViolationException extends RuntimeException {

    public SQLIntegrityConstraintViolationException(String message) {
        super(message);
    }
}

