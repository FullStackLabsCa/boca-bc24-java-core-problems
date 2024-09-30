package io.reactivestax.multithreading.tradesprocessor.interfaces;

public interface TradeStorage {
    void addAccountNumberWithQueue(String accountNumber, int queueNumber);
    Integer getQueueNumber(String accountNumber);
}
