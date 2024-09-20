package problems.jdbc.trade;


import java.io.PrintWriter;

public class Error {
    private int errorCounter;
    private PrintWriter errorLog;
    private int counter;
    private String line;
    private double errorThreshold;
    private int recordSize;

    public Error(int errorCounter, PrintWriter errorLog, int counter, String line, double errorThreshold, int recordSize) {
        this.errorCounter = errorCounter;
        this.errorLog = errorLog;
        this.counter = counter;
        this.line = line;
        this.errorThreshold = errorThreshold;
        this.recordSize = recordSize;
    }

    public int getErrorCounter() {
        return errorCounter;
    }

    public void setErrorCounter(int errorCounter) {
        this.errorCounter = errorCounter;
    }

    public PrintWriter getErrorLog() {
        return errorLog;
    }

    public void setErrorLog(PrintWriter errorLog) {
        this.errorLog = errorLog;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
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
