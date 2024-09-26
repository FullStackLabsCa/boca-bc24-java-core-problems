package multithread_trade_processing.service;

import multithread_trade_processing.interfaces.ChunkProcessing;
import multithread_trade_processing.interfaces.tradeIdAndAccNum;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ChunkProcessor implements ChunkProcessing {

    //ThreadPool
    public void startChunkProcessorPool(String folderPath, int numberOfFiles){
        //Will have a pool of 10 threads
        //Input to the thread pool will be the folder of the files and the naming convention
        String filePath = null;

        //Each thread will do:
            String payload = readPayloadFromChunk(filePath);
            String tradeValidity = checkPayloadValidity(payload);
            tradeIdAndAccNum tradeIdentifiers = getIdentifierFromPayload(payload);
            writePayloadToRawDatabase(tradeIdentifiers.tradeID(), tradeValidity, payload);

            int queueMapping = getQueueMapping(tradeIdentifiers.accountNumber());
            writeToQueue(tradeIdentifiers.tradeID(), queueMapping);


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
        return new tradeIdAndAccNum(fieldsOfTrade[0], fieldsOfTrade[2]);
    }

    @Override
    public void writePayloadToRawDatabase(String tradeID, String tradeStatus, String payload) {

    }

    @Override
    public int getQueueMapping(String accountNumber) {

        return 0;
    }

    @Override
    public void writeToQueue(String tradeID, int queueID) {

    }
}
