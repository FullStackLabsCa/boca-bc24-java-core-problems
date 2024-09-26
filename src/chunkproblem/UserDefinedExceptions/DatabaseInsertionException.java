package chunkproblem.UserDefinedExceptions;

public class DatabaseInsertionException extends RuntimeException {
    public DatabaseInsertionException(String message){
        super(message);
    }
}
