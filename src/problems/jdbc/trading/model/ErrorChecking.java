package problems.jdbc.trading.model;

public class ErrorChecking {
    static double threshold;
    static int records;
    static int errorCount;
    static int insertions;

    public ErrorChecking() {
        threshold = 0;
        records = 0;
        errorCount = 0;
        insertions = 0;
    }

    public static double getThreshold() {
        return threshold;
    }

    public static void setThreshold(double thresholdValue) {
        threshold = thresholdValue;
    }

    public static int getRecords() {
        return records;
    }

    public static void incrementRecordCount() {
        records++;
    }

    public static void setRecordCount(int count) {
        records = count;
    }

    public static void setRecords(int recordsCount) {
        records = recordsCount;
    }

    public static int getErrorCount() {
        return errorCount;
    }

    public static void incrementErrorCount() {
        errorCount++;
    }

    public static int getInsertions() {
        return records - errorCount;
    }
}
