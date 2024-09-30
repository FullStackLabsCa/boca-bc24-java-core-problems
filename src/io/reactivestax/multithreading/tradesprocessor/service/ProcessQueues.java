package io.reactivestax.multithreading.tradesprocessor.service;

import com.zaxxer.hikari.HikariDataSource;
import io.reactivestax.multithreading.tradesprocessor.config.DatabaseHelper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProcessQueues {
    DistrubuteQueue distrubuteQueue = new DistrubuteQueue();
    ExecutorService executorService = Executors.newFixedThreadPool(3);
    public void processQueue(HikariDataSource dataSource){
        executorService.submit(new ProcessTrade(DistrubuteQueue.queue1, dataSource));
        executorService.submit(new ProcessTrade(DistrubuteQueue.queue2, dataSource));
        executorService.submit(new ProcessTrade(DistrubuteQueue.queue3, dataSource));
    }
}
