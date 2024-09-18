package problems.jdbc.transactions.locking.optimistic;

// Custom exception for optimistic locking failure
class OptimisticLockingException extends RuntimeException {
    public OptimisticLockingException(String message) {
        super(message);
    }
}
