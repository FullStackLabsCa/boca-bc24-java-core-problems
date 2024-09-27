package problems.jdbc.tradeprocessor.service;

import problems.jdbc.tradeprocessor.repository.TradeRepository;

import java.util.concurrent.LinkedBlockingQueue;

public class TradeProcessor implements Runnable {

    LinkedBlockingQueue<String> tradeQueue;

    public TradeProcessor(LinkedBlockingQueue<String> tradeQueue) {
        this.tradeQueue = tradeQueue;
    }

    @Override
    public void run() {
        TradeRepository tradeRepository = new TradeRepository();
        try {
            tradeRepository.insertTradeRawPayload(tradeQueue.take(), "");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Thread was interrupted");
        }
    }
}
