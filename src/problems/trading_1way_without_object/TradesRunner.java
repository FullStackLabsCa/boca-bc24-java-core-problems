package problems.trading_1way_without_object;

import problems.trading_1way_without_object.utility.DateFormatValidator;

import java.io.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static problems.trading_1way_without_object.utility.ValidateFieldLength.validateFieldsInTradeLine;

public class TradesRunner {
    public static void main(String[] args) {

        final double errorThreshold = 0.25; // 25%
        final int batchSize = 25;
        int errorCounter = 0;
        int totalRows = 0;
        int successfulInserts = 0;
        List<String> errorMessages = new ArrayList<>();
        int batchCounter = 0;
        String insertDataQuery = "INSERT INTO Trades (trade_id, ticker_symbol, quantity, price, trade_date) VALUES (?,?,?,?,?)";

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("/Users/Karan.Rana/source/student/boca-bc24-java-core-problems/src/problems/trading_problem/trades_Sample.csv")));
             BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("/Users/Karan.Rana/source/student/boca-bc24-java-core-problems/src/problems/trading_problem/error_log.txt", true));
             Connection connection = DataBaseManager.getConnection();
             PreparedStatement insertDataStatement = connection.prepareStatement(insertDataQuery))
        {
            // Set auto-commit to false for transaction management
            connection.setAutoCommit(false);
            String currentLine;
            while ((currentLine = bufferedReader.readLine()) != null) {
                totalRows++;
                boolean success = false;

                try {
                    // Split the line by commas
                    String[] fields = currentLine.split(",");

                    // Ensure there are exactly 5 fields
                    validateFieldsInTradeLine(fields);

                    // Extract and validate each field
                    String trade_id = fields[0];
                    String ticker_symbol = fields[1];
                    String quantityField = fields[2];
                    String priceField = fields[3];
                    String date = fields[4];

                    if (trade_id.isEmpty()) {
                        throw new IllegalArgumentException("Trade ID is missing");
                    }

                    if (ticker_symbol.isEmpty()) {
                        throw new IllegalArgumentException("Ticker symbol is missing");
                    }

                    if (quantityField.isEmpty()) {
                        throw new IllegalArgumentException("Quantity is missing");
                    }
                    int quantity = Integer.parseInt(quantityField);

                    if (priceField.isEmpty()) {
                        throw new IllegalArgumentException("Price is missing");
                    }
                    double price = Double.parseDouble(priceField);

                    if (date.isEmpty()) {
                        throw new IllegalArgumentException("Date is missing");
                    }
                    //validate the date format
                    if (!DateFormatValidator.isValidDate(date)) {
                        throw new IllegalArgumentException("Date Format is not valid.");
                    }


                    // Prepare the statement with the data
                    insertDataStatement.setString(1, trade_id);
                    insertDataStatement.setString(2, ticker_symbol);
                    insertDataStatement.setInt(3, quantity);
                    insertDataStatement.setDouble(4, price);
                    insertDataStatement.setDate(5, Date.valueOf(date));

                    // Add to batch
                    insertDataStatement.addBatch();
                    batchCounter++;
                    success = true;


                } catch (IllegalArgumentException e) {
                    // Log the error, original line, and exception message
                    String errorMessage = "----> Error Log #" + (errorCounter + 1) + ": Failed to process entry: " + currentLine + "  Exception: " + e.getMessage();
                    bufferedWriter.write(errorMessage);
                    bufferedWriter.newLine();
                    errorMessages.add(errorMessage);
                    errorCounter++;
                }

                if (batchCounter == batchSize) {
                    successfulInserts += executeBatch(insertDataStatement, connection, bufferedWriter, errorMessages);
                    batchCounter = 0;
                }
            }
            // Execute any remaining batch
            if (batchCounter > 0) {
                successfulInserts += executeBatch(insertDataStatement, connection, bufferedWriter, errorMessages);
            }

            // Check error threshold and rollback if exceeded
            if ((double) errorCounter / totalRows > errorThreshold) {
                connection.rollback(); // Rollback the transaction if error threshold is exceeded
                throw new HitErrorsThresholdException("More than 25% of the rows contain errors.");
            } else {
                connection.commit(); // Commit only if threshold is not exceeded
            }

            // Summary report
            printSummary(totalRows, successfulInserts, errorCounter, errorMessages);

        } catch (HitErrorsThresholdException e) {
            System.err.println(e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static int executeBatch(PreparedStatement insertDataStatement, Connection connection, BufferedWriter bufferedWriter, List<String> errorMessages) {
        int successfulInserts = 0;
        try {
            int[] batchResults = insertDataStatement.executeBatch();
            for (int result : batchResults) {
                if (result == PreparedStatement.SUCCESS_NO_INFO || result > 0) {
                    successfulInserts++;
                }
            }
        } catch (SQLException e) {
            try {
                connection.rollback(); // Rollback in case of error
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            String batchErrorMessage = "Batch execution failed: " + e.getMessage();
            try {
                bufferedWriter.write(batchErrorMessage);
                bufferedWriter.newLine();
                bufferedWriter.flush(); // Ensure errors are written immediately
            } catch (IOException ioEx) {
                ioEx.printStackTrace();
            }
            errorMessages.add(batchErrorMessage);
            e.printStackTrace();
        }

        // Flush the BufferedWriter to ensure data is persisted
        try {
            bufferedWriter.flush();
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }

        return successfulInserts;
    }

    // Revised to return actual successful insert count
    private static void printSummary(int totalRows, int successfulInserts, int errorCounter, List<String> errorMessages) {
        System.out.println("Summary:");
        System.out.println("Total Records: " + totalRows);
        System.out.println("Successfully Inserted: " + successfulInserts);
        System.out.println("Failed Records: " + errorCounter);
        System.out.println("Reasons for Failures:");
        for (String message : errorMessages) {
            System.out.println(message);
        }
    }
}
