package io.reactivestax.multithreading.tradesprocessor.service;

import com.zaxxer.hikari.HikariDataSource;
import io.reactivestax.multithreading.tradesprocessor.repo.TradeRepo;

import java.util.concurrent.LinkedBlockingQueue;

public class ProcessTrade implements Runnable {

    HikariDataSource dataSource;
    LinkedBlockingQueue<String> queue;
    TradeRepo repo = new TradeRepo();
    ProcessTrade(LinkedBlockingQueue<String> queue, HikariDataSource dataSource){
        this.queue = queue;
        this.dataSource = dataSource;
    }

    @Override
    public void run() {
       while (true){
           try {
             String payload =  repo.getTradePayLoad(queue.take(), dataSource);
             String[] values = payload.split(",");
               System.out.println(values[3]);
               boolean cusipPresent = repo.isCusipPresent(values[3], dataSource);
               if(cusipPresent){
                   repo.addTradeToJournalEntry(payload, "not_posted", dataSource);
                   // trade_id,transaction_time,account_number,cusip,activity,quantity,price
                   ////TDB_00000000,2024-09-19 22:16:18,TDB_CUST_5214938,V,SELL,683,638.02
                   repo.addTradeToPosition(values[2], values[3], values[5], values[4], dataSource);
               }

           } catch (InterruptedException e) {
               System.out.println(""+e);
           }
       }
    }

    public void processTrade(String tradeId) {

    }


    public void loadTrade(String tradeId) {

    }

}
