package problems.jdbc.trading.model;

public class ErrorChecking {
    static double threshold = 0;
    static int records = 0;
    static int errorCount = 0;
    static int insertions = 0;

    private ErrorChecking() {
    }

    public static double getThreshold() {
        return threshold;
    }

    public static void setThreshold(double thresholdValue) {
        threshold = thresholdValue;
    }

    public static void setRecords(int recordsCount) {
        records = recordsCount;
    }

    public static int getRecords() {
        return records;
    }

    public static void incrementRecordCount() {
        records++;
    }

    public static void setErrorCount(int error) {
        errorCount = error;
    }

    public static int getErrorCount() {
        return errorCount;
    }

    public static void incrementErrorCount() {
        errorCount++;
    }

    public static void incrementInsertions(int batch) {
        insertions += batch;
    }

    public static void setInsertions(int value) {
        insertions = value;
    }

    public static int getInsertions() {
        return insertions;
    }
}
