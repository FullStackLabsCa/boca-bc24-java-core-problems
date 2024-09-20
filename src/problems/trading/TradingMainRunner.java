package problems.trading;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingDeque;

public class TradingMainRunner {
    private static ArrayList<TradeTransaction> tradingTransactionArrayList = new ArrayList<>(5000);

    public static void main(String[] args) throws Exception {
        // read file and load data into the list
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter a file name >>>");
        String fileName = scanner.next().trim();
        TradeFileReader.checkFileName(fileName);

        //IF file is valid then setting up the threshold
        TradeFileReader.checkThresholdValue();

        //For readAndWrite to List
        TradeFileReader.readTransactionFileAndWriteToList(tradingTransactionArrayList);

        //writing to the DB
        TradeFileWriter.insertQuery(tradingTransactionArrayList);
    }
}
