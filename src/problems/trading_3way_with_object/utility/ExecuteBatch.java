package problems.trading_3way_with_object.utility;

import java.io.BufferedWriter;
import java.io.IOException;
import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ExecuteBatch {

    public static int executeBatch(PreparedStatement psInsert, Connection conn, BufferedWriter bufferedWriter, List<String> errorMessages) {
        int successfulInserts = 0;
        int duplicateKeyCount = 0; // Counter for duplicate key errors

        try {
            // Execute batch
            int[] batchResults = psInsert.executeBatch();

            // Process the results of the batch execution
            for (int i = 0; i < batchResults.length; i++) {
                if (batchResults[i] == PreparedStatement.SUCCESS_NO_INFO || batchResults[i] > 0) {
                    successfulInserts++;  // Success, count it
                } else if (batchResults[i] == PreparedStatement.EXECUTE_FAILED) {
                    String errorMessage = "Statement " + (i + 1) + " failed.";
                    logError(bufferedWriter, errorMessages, errorMessage);
                }
            }

        } catch (BatchUpdateException bue) {
            // BatchUpdateException caught for any failures during execution
            logError(bufferedWriter, errorMessages, "Batch execution failed: " + bue.getMessage());

            // Get the update counts to check each statement result
            int[] updateCounts = bue.getUpdateCounts();
            SQLException nextException = bue.getNextException();

            for (int i = 0; i < updateCounts.length; i++) {
                if (updateCounts[i] == PreparedStatement.EXECUTE_FAILED) {
                    // Check if the failure is due to duplicate key or other issues
                    String errorDetail = extractDuplicateKeyDetail(nextException); // Extract detailed error info

                    if (errorDetail != null) {
                        duplicateKeyCount++; // Increment duplicate key counter
                        String errorMessage = "Batch item " + (i + 1) + " failed due to duplicate key: " + errorDetail;
                        logError(bufferedWriter, errorMessages, errorMessage);
                    } else {
                        String errorMessage = "Batch item " + (i + 1) + " failed due to an unknown issue.";
                        logError(bufferedWriter, errorMessages, errorMessage);
                    }

                    if (nextException != null) {
                        nextException = nextException.getNextException(); // Move to the next chained exception
                    }
                } else if (updateCounts[i] == PreparedStatement.SUCCESS_NO_INFO || updateCounts[i] > 0) {
                    successfulInserts++;  // Count successful inserts
                }
            }

        } catch (SQLException e) {
            // Handle other SQLExceptions
            String errorMessage = "SQLException occurred: " + e.getMessage();
            logError(bufferedWriter, errorMessages, errorMessage);
        }

        // Ensure the BufferedWriter flushes the output properly
        flushBufferedWriter(bufferedWriter);

        // Log the total number of duplicate key errors found
        logError(bufferedWriter, errorMessages, "Total duplicate key errors: " + duplicateKeyCount);

        return successfulInserts;
    }

    // Helper method to extract duplicate key details from SQLException
    private static String extractDuplicateKeyDetail(SQLException sqlException) {
        // This logic can vary depending on the database and the exception message format.
        // You can refine this based on the exact format of your database’s error message.
        while (sqlException != null) {
            String sqlState = sqlException.getSQLState();
            String message = sqlException.getMessage();
            // Assuming SQLState "23000" is the state for duplicate key errors in most databases (like MySQL)
            if (sqlState != null && sqlState.equals("23000")) {
                return message; // Returning the full message; you can extract key details from it as necessary
            }
            sqlException = sqlException.getNextException(); // Move to next exception if available
        }
        return null; // Return null if no duplicate key error found
    }

    // Helper method to log errors and write to BufferedWriter
    private static void logError(BufferedWriter bufferedWriter, List<String> errorMessages, String errorMessage) {
        try {
            bufferedWriter.write(errorMessage);
            bufferedWriter.newLine();
            bufferedWriter.flush();  // Ensure immediate write
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
        errorMessages.add(errorMessage);
    }

    // Helper method to flush the BufferedWriter
    private static void flushBufferedWriter(BufferedWriter bufferedWriter) {
        try {
            bufferedWriter.flush();
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
    }
}
