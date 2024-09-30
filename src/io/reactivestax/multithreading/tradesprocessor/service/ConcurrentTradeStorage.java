package io.reactivestax.multithreading.tradesprocessor.service;

import io.reactivestax.multithreading.tradesprocessor.interfaces.TradeStorage;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentTradeStorage implements TradeStorage {
    private final ConcurrentHashMap<String, Integer> tradeMap = new ConcurrentHashMap<>();

    @Override
    public void addAccountNumberWithQueue(String accountNumber, int queueNumber) {
        tradeMap.put(accountNumber, queueNumber);
    }

    @Override
    public Integer getQueueNumber(String accountNumber) {
        return tradeMap.get(accountNumber);
    }




}
