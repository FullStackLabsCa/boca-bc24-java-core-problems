package trading_parser.service;

import trading_parser.model.Trade;
import trading_parser.utility.InvalidThresholdValueException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static trading_parser.repo.TradeParserRepo.*;
import static trading_parser.utility.TradeParseUtility.dataSource;
import static trading_parser.utility.TradeParseUtility.logFileValidityForErrors;

public class TradeParserEngine {

    public static int successfullInsertsCount = 0, failedInsertsCount = 0;
    public static double threshold;

    public static String getFileNameFromCommandLine(Scanner scanner){
        boolean validFileName = false;
        do {
            System.out.println("Please enter the file path: ");
            String filePath = scanner.next();
            if (Files.exists(Paths.get(filePath))) {
                return filePath;
            }
            System.out.println("Invalid File Path!!!");
        } while (!validFileName);

        return null;
    }

    //Service
    public static String getThresholdAndFilePathFromCommandLine() {
        try (Scanner scanner = new Scanner(System.in)) {

            boolean thresholdValid = false;
            do {
                try {
                    System.out.println("Enter a Threshold Value: ");
                    String thresholdInputFromUser = scanner.next();
                    threshold = validateThresholdInput(thresholdInputFromUser);
                    thresholdValid = true;
                } catch (InvalidThresholdValueException e) {
                    System.out.println("User Input Required Again");
                }
            } while (!thresholdValid);

            return getFileNameFromCommandLine(scanner);
        }
    }

    //Service
    public static double validateThresholdInput(String threshold) throws InvalidThresholdValueException {
        double thresholdValue;
        try {
            thresholdValue = Double.valueOf(threshold);
        } catch (Exception e) {
            throw new InvalidThresholdValueException();
        }

        if ((thresholdValue >= 1) && (thresholdValue <= 100)) {
            System.out.println("Threshold Input Valid");
            return thresholdValue;
        } else {
            throw new InvalidThresholdValueException();
        }
    }

    //Service
    public static void readTradesFileAndWriteToDatabase(String filePath, Logger logger) throws SQLException {

        //Read transactions from file
        try (Scanner fileReader = new Scanner(new BufferedReader(new FileReader(filePath)));
             Connection conn = dataSource.getConnection()) {
            //Propagation
            conn.setAutoCommit(false);

            int errorCount = 0, success = 0, tried = 0;
            List<Trade> tradeslist = new ArrayList<>();
            fileReader.useDelimiter("[,\n]");

            if (fileReader.nextLine() != null) {
                System.out.println("Skipping the first intro line.");
            } else {
                return;
            }

            while (fileReader.hasNext()) {
                String trade_id = null, ticker_symbol, trade_date_read, trade_identifier;
                int quantity;
                double price;
                try {
                    tried++;
                    //Process the buffer and create prepared statements from each read
                    trade_id = fileReader.next();
                    trade_identifier = fileReader.next();
                    ticker_symbol = fileReader.next();
                    quantity = fileReader.nextInt();
                    price = fileReader.nextDouble();
                    trade_date_read = fileReader.next();
                    LocalDate trade_date = LocalDate.parse(trade_date_read, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                    Trade trade = new Trade(trade_id, trade_identifier, ticker_symbol, quantity, price, trade_date);

                    if (tradesBusinessValidation(conn, trade)) {
                        success++;
                        tradeslist.add(trade);
                    } else {
                        errorCount++;
                        fileReader.nextLine();
                        logger.log(Level.WARNING, "Invalid security Symbol: " + trade.getTicker_symbol() + ", Trade_ID: " + trade.getTrade_id());
                    }
                    //Execute insertion
                    if ((tradeslist.size() >= batchSize)) {
                        insertTrades(conn, logger, tradeslist);
                        tradeslist.clear();
                    }

                } catch (InputMismatchException | DateTimeParseException e) {
                    errorCount++;
                    fileReader.nextLine();
                    if (trade_id != null) {
                        logger.log(Level.WARNING, "Parser Error: Failed to process Transaction with ID: " + trade_id);
                    } else {
                        logger.log(Level.WARNING, "Parser Error: Failed to process TradeID");
                    }
                }
            }

            //Execute the Last batch
            if (!tradeslist.isEmpty()) {
                insertTrades(conn, logger, tradeslist);
                tradeslist.clear();
            }

            logFileValidityForErrors(success, tried, errorCount, threshold);

            conn.commit();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    //Service
    public static void udpateInsertionLogs(Connection connection, List<Trade> tradesList, int originalSizeOfTable) {
        int tableSizeAfterBatchExecution = getTradeTableSize(connection);
        failedInsertsCount += tradesList.size() - (tableSizeAfterBatchExecution - originalSizeOfTable);
        successfullInsertsCount += tableSizeAfterBatchExecution - originalSizeOfTable;
    }

}
