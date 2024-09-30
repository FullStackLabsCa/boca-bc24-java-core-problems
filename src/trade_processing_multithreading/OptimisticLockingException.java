package trade_processing_multithreading;

class OptimisticLockingException extends RuntimeException {
    public OptimisticLockingException(String message) {
        super(message);
    }
}