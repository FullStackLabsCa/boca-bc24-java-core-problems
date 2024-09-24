package problems.trading_2way.business_logic;

import problems.trading_2way.exceptions.HitErrorsThresholdException;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import static problems.trading_2way.business_logic.ProcessTradeLine.processTradeLine;
import static problems.trading_2way.utility.PrintSummary.printSummary;

public class TradeProcessor {
    private static final double errorThreshold = 1;
    private static final int batchSize = 4;

    public void processTrades(String inputFile, String errorReaderLogFile, Connection connection) throws HitErrorsThresholdException, IOException {
        int errorCounter = 0;
        int totalRows = 0;
        int successfulInserts = 0;
        int batchCounter = 0;

        List<String> errorReaderMessages = new ArrayList<>();
        List<String> errorWriterMessages = new ArrayList<>();

        String insertDataQuery = "INSERT INTO Trades (trade_id, trade_identifier, ticker_symbol, quantity, price, trade_date) VALUES (?,?,?,?,?,?)";

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(errorReaderLogFile, true));
             PreparedStatement insertDataStatement = connection.prepareStatement(insertDataQuery)) {

            connection.setAutoCommit(false);
            String headerLine = bufferedReader.readLine(); // Skip the header line
            String currentLine;

            while ((currentLine = bufferedReader.readLine()) != null) {
                totalRows++;
                try {
                    processTradeLine(currentLine, insertDataStatement);
                    insertDataStatement.addBatch();  // Collect statements into a batch
                    batchCounter++;
                } catch (SQLIntegrityConstraintViolationException e) {
                    // Handle duplicate key errors (e.g., trade_id constraint violation)
                    errorWriterMessages.add(logError(totalRows, bufferedWriter, errorCounter, currentLine, "Duplicate trade_id"));
                    errorCounter++;
                } catch (IllegalArgumentException e) {
                    // Handle validation errors
                    errorReaderMessages.add(logError(totalRows,bufferedWriter, errorCounter, currentLine, e.getMessage()));
                    errorCounter++;
                } catch (SQLException e) {
                    // Handle all other SQL exceptions
                    throw e;  // Rethrow any non-constraint SQL exceptions
                }

                if (batchCounter % batchSize == 0) {
                    insertDataStatement.executeBatch();  // Execute the batch
                    connection.commit();
                }
            }

            // If there are any remaining entries that were not executed in a full batch
            if (batchCounter % batchSize != 0) {
                insertDataStatement.executeBatch();
                connection.commit();
            }

            // If more than threshold of the records had errors, roll back the transaction and throw an exception
            if ((double) errorCounter / totalRows > errorThreshold) {
                connection.rollback();
                throw new HitErrorsThresholdException("More than 25% of the rows contain errors.");
            }

            // Print summary of the process
            printSummary(totalRows, successfulInserts, errorCounter, errorReaderMessages, errorWriterMessages);

        } catch (Exception e) {
            e.printStackTrace();  // Log and handle any other exception
        }
    }

    private String logError(int totalRow, BufferedWriter writer, int errorCounter, String line, String errorMessage) throws IOException {
        String logMessage = errorMessage + " Error in Line # " + totalRow + " || Log# " + (errorCounter + 1) + ": " + line ;
        writer.write(logMessage);
        writer.newLine();
        return logMessage;
    }


}
