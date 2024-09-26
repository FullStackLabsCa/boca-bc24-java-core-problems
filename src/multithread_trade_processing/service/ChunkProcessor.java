package multithread_trade_processing.service;

import multithread_trade_processing.interfaces.ChunkProcessing;
import multithread_trade_processing.interfaces.tradeIdAndAccNum;
import multithread_trade_processing.repo.PayloadDatabaseRepo;
import multithread_trade_processing.repo.PayloadDatabaseRepo.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;

public class ChunkProcessor implements ChunkProcessing {

    ConcurrentHashMap<String, Integer> accToQueueMap =  new ConcurrentHashMap<>();

    static final LinkedBlockingDeque<String> tradesIdQueue1 = new LinkedBlockingDeque<>();
    static final LinkedBlockingDeque<String> tradesIdQueue2 = new LinkedBlockingDeque<>();
    static final LinkedBlockingDeque<String> tradesIdQueue3 = new LinkedBlockingDeque<>();

    //ThreadPool
    public void startChunkProcessorPool(String folderPath, int numberOfFiles){
        //Will have a pool of 10 threads
        //Input to the thread pool will be the folder of the files and the naming convention
        String filePath = null;

        //Each thread will do:
            String payload = readPayloadFromChunk(filePath);
            String tradeValidity = checkPayloadValidity(payload);
            tradeIdAndAccNum tradeIdentifiers = getIdentifierFromPayload(payload);

            writePayloadToPayloadDatabase(tradeIdentifiers.tradeID(), tradeValidity, payload);

            if(tradeValidity.equals("Valid")) {
                int queueMapping = getQueueMapping(tradeIdentifiers.accountNumber());
                writeToQueue(tradeIdentifiers.tradeID(), queueMapping);
            }

    }

    @Override
    public String readPayloadFromChunk(String filePath){
        try(Scanner chunkReader = new Scanner(new FileReader(filePath))){
            if(chunkReader.hasNextLine()){
                return chunkReader.nextLine();
            } else {
                System.out.println("End Of Chunk Reached.");
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String checkPayloadValidity(String payload){
        String[] fieldsOfTrade = payload.split(",");
        if(fieldsOfTrade.length != 7){
            return "Invalid";
        } else return "Valid";
    }

    @Override
    public tradeIdAndAccNum getIdentifierFromPayload(String payload){
        String[] fieldsOfTrade = payload.split(",");
        if((fieldsOfTrade[0] != null) && (fieldsOfTrade[1] != null))
            return new tradeIdAndAccNum(fieldsOfTrade[0], fieldsOfTrade[2]);
        else if(fieldsOfTrade[0] == null) return new tradeIdAndAccNum("Invalid", fieldsOfTrade[1]);
        else if(fieldsOfTrade[1] == null) return new tradeIdAndAccNum(fieldsOfTrade[0], "Invalid");
        else return new tradeIdAndAccNum("Invalid","Invalid");
    }

    @Override
    public void writePayloadToPayloadDatabase(String tradeID, String tradeStatus, String payload) {
        PayloadDatabaseRepo payloadRepo = new PayloadDatabaseRepo();
        payloadRepo.writeToDatabase(tradeID, tradeStatus, payload);
    }

    @Override
    public int getQueueMapping(String accountNumber) {
        if(accToQueueMap.containsKey(accountNumber)){
            return accToQueueMap.get(accountNumber);
        } else {
            int randomQueueNum = (int)(Math.random() * 3) + 1;
            accToQueueMap.put(accountNumber, randomQueueNum);
            return randomQueueNum;
        }
    }

    @Override
    public void writeToQueue(String tradeID, int queueID) {
        try {
            switch (queueID) {
                case 1:
                    tradesIdQueue1.putFirst(tradeID);
                    break;
                case 2:
                    tradesIdQueue2.putFirst(tradeID);
                    break;
                case 3:
                    tradesIdQueue3.putFirst(tradeID);
                    break;
                default:
                    System.out.println("Unable to add to Queue. Please Debug the Issue.");
            }
        } catch (InterruptedException e){
            throw new RuntimeException(e);
        }
    }
}
