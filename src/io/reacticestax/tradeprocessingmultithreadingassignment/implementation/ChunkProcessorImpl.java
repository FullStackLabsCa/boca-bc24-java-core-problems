package io.reacticestax.tradeprocessingmultithreadingassignment.implementation;

import io.reacticestax.tradeprocessingmultithreadingassignment.projectinterfaces.ChunkProcessor;

import java.io.*;
import java.util.Map;


public class ChunkProcessorImpl implements ChunkProcessor, Runnable {

    public static String chunkFilePath;

    public ChunkProcessorImpl(String chunkFilePath) {
        this.chunkFilePath = chunkFilePath;
    }

    TradeDistributor tradeDistributor = new TradeDistributor();


    @Override
    public void processChunk(String chunkFilePath) throws IOException {
    TradeProcessingServiceImpl tradeProcessingService = new TradeProcessingServiceImpl();
    String line;


        try (BufferedReader reader = new BufferedReader(new FileReader(chunkFilePath))) {
        while ((line = reader.readLine()) != null) {
            String[] columns = line.split(",");


            String trade_id = columns[0];
            String account_number = columns[2];
            String payload = line;
            String status = columns.length != 7 ? "invalid" : "valid";


            tradeProcessingService.insertToTradePayloadTableInDB(trade_id, status, payload);

            tradeDistributor.consultAccountToQueueMap(account_number);

            for (Map.Entry<String, Integer> entry : tradeDistributor.accountToQueueMap.entrySet()) {
                String key = entry.getKey().toString();
                Integer value = entry.getValue();
                System.out.println("key, " + key + " value " + value);
            }


            // Insert account number into the distributor and assign it a queue number
            tradeDistributor.insertToTradeQueue(account_number, trade_id);

        }
    } catch (Exception e) {
        e.printStackTrace();
        throw new RuntimeException(e);
    }
        System.out.println("end of method");
}

    @Override
    public void run() {

        try {

            processChunk(this.chunkFilePath);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}







// System.out.println("queue size: "   + tradeDistributor.tradeQueue1.size() + tradeDistributor.tradeQueue2.size() + tradeDistributor.tradeQueue3.size());
//    @Override
//    public void processChunk(String chunkFilePath) throws IOException {
//        TradeProcessingServiceImpl tradeProcessingService = new TradeProcessingServiceImpl();
//        TradeDistributor tradeDistributor = new TradeDistributor();
//        String line;
//        try (BufferedReader reader = new BufferedReader(new FileReader(chunkFilePath))) {
//            while ((line = reader.readLine()) != null) {
//                String[] columns = line.split(",");
//                if (columns.length != 7) {
//                    tradeProcessingService.insertToTradePayloadTableInDB(columns[0], "invalid", line);
//                } else {
//                    tradeProcessingService.insertToTradePayloadTableInDB(columns[0], "valid", line);
//                }
//                tradeDistributor.consultAccountToQueueMap(columns[2]);
//                for (Map.Entry<String, Integer> entry : tradeDistributor.accountToQueueMap.entrySet()) {
//                    String key = entry.getKey().toString();
//                    Integer value = entry.getValue();
//                    System.out.println("key, " + key + " value " + value);
//                }
//
//                tradeDistributor.insertToTradeQueue(columns[2], columns[0]);
//
//
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//
//        }

//    }




