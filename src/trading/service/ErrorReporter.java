package trading.service;

public interface ErrorReporter {
    boolean reportError(String trade,String errorMessage,int lineNumber);
}
