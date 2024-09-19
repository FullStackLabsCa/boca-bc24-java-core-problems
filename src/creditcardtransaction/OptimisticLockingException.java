package creditcardtransaction;

public class OptimisticLockingException extends RuntimeException{
    public OptimisticLockingException(String message) {
        super(message);
    }
}
