package fileIoTradeAssignment.service;

import com.zaxxer.hikari.HikariDataSource;
import fileIoTradeAssignment.customExceptionClasses.HitReadFileErrorsThresholdException;
import fileIoTradeAssignment.model.TradePOJO;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

import static fileIoTradeAssignment.DatabaseHelper.getConnection;

public class TradeService implements TradeServiceInterface {

    private String filePath;
    public ArrayList<TradePOJO> validLines;
    public static HikariDataSource dataSource = getConnection();

    public int errorCounter = 0;
    private int readFileErrorThreshold;
    public int DBInsertErrorThreshold;


    // Method to set error thresholds
    @Override
    public void setErrorThreshold(double threshold) {
        this.readFileErrorThreshold = (int) threshold; // Assuming we cast to int
        this.DBInsertErrorThreshold = (int) threshold; // Assuming we cast to int
    }

    @Override
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void readTradeFile() throws FileNotFoundException, HitReadFileErrorsThresholdException {
        validLines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String newLine;
            boolean isFirstLine = true;

            while ((newLine = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] tradeDetails = newLine.split(",");

                try {
                    // Parse details into TradePOJO
                    int trade_id = Integer.parseInt(tradeDetails[0]);
                    String trade_identifier = tradeDetails[1];
                    String ticker_symbol = tradeDetails[2];
                    int quantity = Integer.parseInt(tradeDetails[3]);
                    double price = Double.parseDouble(tradeDetails[4]);
                    Date trade_date = Date.valueOf(tradeDetails[5]);

                    // Create TradePOJO object
                    TradePOJO tradePOJO = new TradePOJO(trade_id, trade_identifier, ticker_symbol, quantity, price, trade_date);

                    // Validate the tradePOJO
                    if (validateTradePOJO(tradePOJO)) {
                        validLines.add(tradePOJO); // Store valid TradePOJO objects
                    }

                } catch (Exception e) {
                    errorCounter++;
                    String errorMessage = "Error parsing line: " + newLine + " - " + e.getMessage();
                    System.err.println(errorMessage);
                    logError(errorMessage, "readFile_error_log.txt");
                }

                if (errorCounter >= readFileErrorThreshold) {
                    String errorMessage = "Error count: " + errorCounter + " exceeds threshold while reading file.";
                    System.out.println(errorMessage);
                    logError(errorMessage, "/Users/akshitabajaj/Documents/reactiveStax/boca-bc24-java-core-problems/src/fileIoTradeAssignment/errorLogs/readFile_error_log.txt");
                    throw new HitReadFileErrorsThresholdException("Threshold limit exceeded while reading file");
                }
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File doesn't exist");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean validateTradePOJO(TradePOJO trade) {
        // Add custom validation logic
        return trade.getTrade_id() > 0 && (trade.getQuantity() > 0 && trade.getQuantity() <= 500) && (trade.getPrice() > 0 && trade.getPrice() <= 4000.00);
    }


@Override
    public void logError(String message, String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, true))) {
            writer.write(new java.util.Date() + ": " + message); // Add timestamp
            writer.println();
            writer.flush(); // Ensure the log message is written immediately
        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
            e.printStackTrace(); // Print stack trace for better debugging
        }

    }
}