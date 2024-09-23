package fileIoTradeAssignment.customExceptionClasses;

public class HitDatabaseInsertErrorsThresholdException extends Exception {
    public HitDatabaseInsertErrorsThresholdException(String str) {
        super(str);
    }
}