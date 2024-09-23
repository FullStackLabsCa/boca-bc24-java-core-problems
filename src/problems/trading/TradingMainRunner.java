package problems.trading;

import problems.trading.database.DatabaseConnectionPool;

import java.util.ArrayList;
import java.util.Scanner;

public class TradingMainRunner {

    public static void main(String[] args) throws Exception {
        // read file and load data into the list
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter a file name >>>");
        String fileName = scanner.next().trim();
        TradeFileReader.checkFileName(fileName);

        //IF file is valid then setting up the threshold
        do {
            Scanner thresholdScanner = new Scanner(System.in);
            System.out.print("Enter a threshold value: ");
            String str = thresholdScanner.nextLine();
            TradeFileReader.checkThresholdValue(str);
        } while (TradeService.isThresholdValid == false);


        //For readAndWrite to List
        TradeFileReader.readTransactionFileAndWriteToList(TradeService.filePath);

        //writing to the DB
        TradeFileWriter.insertQuery(TradeService.tradingTransactionArrayList, DatabaseConnectionPool.getConnection());
    }
}
