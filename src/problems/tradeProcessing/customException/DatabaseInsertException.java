package problems.tradeProcessing.customException;

public class DatabaseInsertException extends Exception{
    public DatabaseInsertException(String message) {
        super(message);
    }
}
