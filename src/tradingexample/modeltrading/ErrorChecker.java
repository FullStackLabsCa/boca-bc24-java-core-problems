package tradingexample.modeltrading;

public class ErrorChecker {
    double threshold;
    int records;
    int errorCount;
    int insertion;

    public ErrorChecker() {
        threshold = 0;
        records = 0;
        errorCount = 0;
        insertion = 0;
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


    public void setError(int errors) {
        errorCount = errors;
    }

    public int getErrorCount() {
        return errorCount;
    }

    public void incrementErrorCount() {
        errorCount++;
    }

    public void setInsertion(int inserts) {
        insertion = inserts;
    }

    public  void incrementInsertCount(int batchSize){
        insertion += batchSize;
    }

    public int getInsertion() {
        return insertion;
    }
}
