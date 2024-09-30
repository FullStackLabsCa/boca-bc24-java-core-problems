package problems.trademultithreading.customexception;

public class FileSplitterException extends RuntimeException {
    public FileSplitterException(String message) {
        super(message);
    }

    public FileSplitterException(String message, Throwable cause) {
        super(message, cause);
    }}
