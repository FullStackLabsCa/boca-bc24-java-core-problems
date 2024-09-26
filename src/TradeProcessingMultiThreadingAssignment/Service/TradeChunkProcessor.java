package TradeProcessingMultiThreadingAssignment.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TradeChunkProcessor {
    ExecutorService executorService = Executors.newFixedThreadPool(10);

    public ExecutorService getExecutorService() {
        return executorService;
    }
}
