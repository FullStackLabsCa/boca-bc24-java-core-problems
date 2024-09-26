package multithread_trade_processing.service;

import multithread_trade_processing.interfaces.ChunkProcessing;
import multithread_trade_processing.interfaces.tradeIdAndAccNum;

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

        return null;
    }

    @Override
    public String checkPayloadValidity(String payload){

        return null;
    }

    @Override
    public tradeIdAndAccNum getIdentifierFromPayload(String payload){

        return null;
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
