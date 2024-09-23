package fileIoTradeAssignment.customExceptionClasses;

public class HitReadFileErrorsThresholdException extends Exception {
    public HitReadFileErrorsThresholdException(String str) {
        super(str);
    }
}