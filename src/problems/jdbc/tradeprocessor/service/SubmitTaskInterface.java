package problems.jdbc.tradeprocessor.service;

public interface SubmitTaskInterface<T> {
    void submitTask(T processor);
}
