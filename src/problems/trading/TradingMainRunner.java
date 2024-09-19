package problems.trading;

import java.util.Scanner;
import java.util.concurrent.LinkedBlockingDeque;

public class TradingMainRunner {
    public static LinkedBlockingDeque<TradeTransaction> tradingTransactionDeQueue = new LinkedBlockingDeque<>(5000);

    public static void main(String[] args) throws Exception {
        boolean isFileNameValid = false;
        Scanner scanner = new Scanner(System.in);
        String fileName = "";

        //checking file name from the user input
        fileName = CheckUserInputForFile.checkFileName(isFileNameValid, fileName, scanner);

        // read file and load data into LinkedBlockingDeque

        String filePath = "/Users/Gaurav.Manchanda/src/boca-bc24-java-core-problems/" + fileName + ".csv";
        TradeFileReader.checkThresholdValue();
        tradingTransactionDeQueue = TradeFileReader.readTransactionFileAndWriteToQueue(filePath, tradingTransactionDeQueue);

        //writing to the DB
        TradeFileWriter.insertQuery(tradingTransactionDeQueue);
    }
}
