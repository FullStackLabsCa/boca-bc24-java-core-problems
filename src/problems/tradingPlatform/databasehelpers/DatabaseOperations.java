package problems.tradingPlatform.databasehelpers;

import problems.tradingPlatform.helpers.CommonFunctions;
import problems.tradingPlatform.helpers.ErrorManager;
import problems.tradingPlatform.services.TradeReader;
import problems.tradingPlatform.services.TradeWriter;
import problems.tradingPlatform.models.Trade;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import static problems.tradingPlatform.helpers.ErrorManager.logReadingError;
import static problems.tradingPlatform.helpers.ErrorManager.logWritingError;

public class DatabaseOperations  {
    public static double errorThresholdCount = 25;
    public static double totalRowsOfFile = 0;
    String filePath = "";
    File file;

    public void StartTradeDataExecution() {
        boolean isValidFile = false;
        while (!isValidFile) {
            Scanner scanner = new Scanner(System.in);
            isValidFile = checkForValidFile(scanner);
            if (isValidFile) {
                try  {
                    errorThresholdCount = getErrorThresholdCount(scanner);
                    TradeReader tradeReader = new TradeReader();
                    List<Trade> tradeList = tradeReader.readTradeData(filePath);
                    if (!tradeList.isEmpty()) {
                        System.out.println("Trade Read successfully for : "+tradeList.size() + " Rows");
                        TradeWriter tradeWriter = new TradeWriter();
                        Connection conn = DatabaseConnection.getConnection(false);
                        tradeWriter.writeTradeData(tradeList,conn,false);
                    }
                } catch (SQLException e) {
                    ErrorManager.checkForErrorThreshold("\n Error Creating a Connection : SQLException",false);
                } finally {
                    scanner.close();
                }
            } else {
                ErrorManager.checkForErrorThreshold("\n File Does not Exits. Please Enter the valid file path",true);
            }
        }
    }

    public double getErrorThresholdCount(Scanner scanner) {
        double percentage = CommonFunctions.askUserForInput(scanner);
        try {
            totalRowsOfFile = CommonFunctions.getTotalRowsCount(file);
            errorThresholdCount = (double) (totalRowsOfFile * percentage) / 100;
            System.out.println("Error Threshold Count : " + errorThresholdCount);
        }
        catch ( InputMismatchException e) {
            ErrorManager.checkForErrorThreshold("\n An error occurred during reading the file : InputMismatchException."+e.getMessage(),true);
        }
        return  errorThresholdCount;
    }

    public boolean checkForValidFile(Scanner scanner)
    {
        System.out.println("Please Enter the file path");
        filePath = scanner.nextLine();
        file = new File(filePath);
        logReadingError("\n",false);
        logWritingError("\n" ,false);
        return file.exists();
    }


}

