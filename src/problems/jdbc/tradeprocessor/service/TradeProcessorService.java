package problems.jdbc.tradeprocessor.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TradeProcessorService implements SubmitTaskInterface<TradeProcessor> {

    ExecutorService tradeProcessorExecutorService = Executors.newFixedThreadPool(3);

    public void tradeProcessor() {
        submitTask(new TradeProcessor(QueueDistributor.transactionQueueOne));
        submitTask(new TradeProcessor(QueueDistributor.transactionQueueTwo));
        submitTask(new TradeProcessor(QueueDistributor.transactionQueueThree));
    }

    @Override
    public void submitTask(TradeProcessor tradeProcessor) {
        tradeProcessorExecutorService.submit(tradeProcessor);
    }
}
