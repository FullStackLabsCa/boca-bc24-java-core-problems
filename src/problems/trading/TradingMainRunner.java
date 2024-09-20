package problems.trading;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingDeque;

public class TradingMainRunner {
    public static ArrayList<TradeTransaction> tradingTransactionArrayList = new ArrayList<>(5000);

    public static void main(String[] args) throws Exception {
        // Check if the read file exists and delete it at the start of execution
        TradeService.checkReadLogFileExistOrNot();

        // Check if the write file exists and delete it at the start of execution
        TradeService.checkWriteLogFileExistOrNot();

        // read file and load data into the list
        TradeFileReader.checkFileName();
        //IF file is valid then setting up the threshold
        TradeFileReader.checkThresholdValue();
        //For readAndWrite to List
        TradeFileReader.readTransactionFileAndWriteToList(tradingTransactionArrayList);

        //writing to the DB
        TradeFileWriter.insertQuery(tradingTransactionArrayList);
    }
}
