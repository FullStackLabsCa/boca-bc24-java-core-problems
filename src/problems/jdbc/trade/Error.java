package problems.jdbc.trade;

public class Error {
    private int errorCounter;
    private double errorThreshold;
    private int recordSize;

    public Error(int errorCounter, double errorThreshold, int recordSize) {
        this.errorCounter = errorCounter;
        this.errorThreshold = errorThreshold;
        this.recordSize = recordSize;
    }

    public int getErrorCounter() {
        return errorCounter;
    }

    public void setErrorCounter(int errorCounter) {
        this.errorCounter = errorCounter;
    }

    public double getErrorThreshold() {
        return errorThreshold;
    }

    public void setErrorThreshold(double errorThreshold) {
        this.errorThreshold = errorThreshold;
    }

    public int getRecordSize() {
        return recordSize;
    }

    public void setRecordSize(int recordSize) {
        this.recordSize = recordSize;
    }
}
