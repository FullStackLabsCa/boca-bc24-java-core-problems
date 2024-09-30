package io.reacticestax.tradeprocessingmultithreadingassignment.implementation;

import io.reacticestax.tradeprocessingmultithreadingassignment.projectinterfaces.TaskQueueDistributor;
import io.reacticestax.tradeprocessingmultithreadingassignment.projectinterfaces.TradeProcessingService;

import java.io.File;

public class FileTask implements Runnable{
    File file ;
    TaskQueueDistributor taskQueueDistributor;
    private TradeProcessingService tradeService;

    public FileTask(File file) {

        this.file = file;
    }

    @Override
    public void run() {
        //iterate the file and start executing the step 4, 5, 6 for each line
        String trade="";
        String tradeId="";
        tradeService.saveToRawTableInDB(trade);
        taskQueueDistributor.consultTheQueueDistributorMap(trade);
        taskQueueDistributor.submitToTaskDistributionQueue(tradeId);
    }



    private void saveToRawTableInDB(String trade) {

    }
}
