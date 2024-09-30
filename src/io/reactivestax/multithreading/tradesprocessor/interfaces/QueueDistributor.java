package io.reactivestax.multithreading.tradesprocessor.interfaces;

public interface QueueDistributor {
    void distribute(String tradeId, int queueNumber);
}
