package trade.processing.multithreading.exceptions;

public class FileNotExistException extends RuntimeException{
    public FileNotExistException(String message) {
        super(message);
    }
}
