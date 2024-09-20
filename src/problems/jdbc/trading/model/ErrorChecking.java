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

    public double getThreshold() {
        return threshold;
    }

    public void setThreshold(double thresholdValue) {
        threshold = thresholdValue;
    }

    public int getRecords() {
        return records;
    }

    public void incrementRecordCount() {
        records++;
    }

    public void setRecordCount(int count) {
        records = count;
    }

    public void setErrors(int errors) {
        errorCount = errors;
    }

    public int getErrorCount() {
        return errorCount;
    }

    public void incrementErrorCount() {
        errorCount++;
    }

    public int getInsertions() {
        return records - errorCount;
    }
}
