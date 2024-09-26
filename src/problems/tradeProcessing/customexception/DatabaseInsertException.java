package problems.tradeProcessing.customexception;

public class DatabaseInsertException extends Exception{
    public DatabaseInsertException(String message) {
        super(message);
    }
}
