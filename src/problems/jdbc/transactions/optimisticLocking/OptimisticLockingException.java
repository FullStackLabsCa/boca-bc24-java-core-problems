package problems.jdbc.transactions.optimisticLocking;

public class OptimisticLockingException extends RuntimeException {
    public OptimisticLockingException(String message) {
        super(message);
    }
}
