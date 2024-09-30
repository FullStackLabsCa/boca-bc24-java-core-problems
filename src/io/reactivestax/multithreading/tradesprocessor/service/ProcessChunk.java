package io.reactivestax.multithreading.tradesprocessor.service;
import com.zaxxer.hikari.HikariDataSource;
import io.reactivestax.multithreading.tradesprocessor.interfaces.QueueDistributor;
import io.reactivestax.multithreading.tradesprocessor.interfaces.TradeStorage;
import io.reactivestax.multithreading.tradesprocessor.repo.TradeRepo;
import io.reactivestax.multithreading.tradesprocessor.utils.GetDataFromProperties;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;

public class ProcessChunk implements Runnable {
    GetDataFromProperties getDataFromProperties;
    String destination = "/Users/Ankit.Joshi/Desktop/Reactive/boca-bc24-java-core-problems/src/io/reactivestax/multithreading/tradesprocessor/resources/";
    int chunk;
    HikariDataSource hikariDataSource;
    String portNumber = "3306";
    int queueCount = 0;
    private static final TradeStorage storage  = new ConcurrentTradeStorage();
    private DistrubuteQueue distributor = new DistrubuteQueue();
    public ProcessChunk(int chunk, HikariDataSource hikariDataSource) {
        this.chunk = chunk;
        this.hikariDataSource = hikariDataSource;
    }

    @Override
    public void run() {
        TradeRepo repo = new TradeRepo();
        String line;
        try(BufferedReader reader = new BufferedReader(new FileReader(destination+"chunkFile"+chunk+".csv"))){
            while((line = reader.readLine()) != null){
                String[] values = line.split(",");
//                boolean isValid = validateValues(values[0], Date.valueOf(values[1]), values[2], values[3], values[4], Integer.valueOf(values[5]), Double.valueOf(values[6]));
                String validOrInvalid = "VALID";
//                if(isValid){
//                    validOrInvalid = "VALID";
//                }else{
//                    validOrInvalid = "INVALID";
//                }
               // INSERT INTO trade_payloads (trade_id, status, status_reason, payload) VALUES (?,?,?,?)
                repo.addTradeToTradePayloads(line, values[0], validOrInvalid, validOrInvalid, hikariDataSource);
                queueCount = queueCount == 3 ? 1 : queueCount +1;
                storage.addAccountNumberWithQueue(values[2], queueCount);
                distributor.distribute(values[0], storage.getQueueNumber(values[2]));
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private boolean validateValues(String value, Date value1, String value2, String value3, String status, int value5, Double value6) {
        if (status.equals("BUY") || status.equals("SELL")) {
            return false;
        }
        return true;
    }

}
