package problems.tradeOperations;


import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInputCMD {

    public UserInputCMD(DatabaseManager databaseManager) {

        Scanner scanner = new Scanner(System.in);



        System.out.println("Please give me trade file path");
        System.out.println("/Users/Jay.Shah/Downloads/tradeFile.csv");
        String filePath = scanner.nextLine();
        File file = new File(filePath);

        readFile(filePath, databaseManager);

    }

    public static void readFile(String filePath, DatabaseManager databaseManager) {

        List<String[]> validTrades = new ArrayList<>();
        int totalRows = 0;
        int errorCount = 0;
        int validRowCount = 0;

        FileWriter errorLogWriter = null;

        try {
            errorLogWriter = new FileWriter("src/problems/tradeOperations/error_log.txt", true);

            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = br.readLine()) != null) {
                    totalRows++;

                    // Skip header line
                    if (line.startsWith("trade_id")) {
                        continue;
                    }

                    String[] fields = line.split(",");
                    if (fields.length != 5) {
                        String errorMessage = "Invalid row (Incorrect number of fields): " + line;
                        System.out.println(errorMessage);
                        if (errorLogWriter != null) {
                            errorLogWriter.write(errorMessage + System.lineSeparator());
                            errorLogWriter.flush(); // Ensure immediate write
                        }
                        errorCount++;
                        continue;
                    }

                    // validation - quantity (integer)
                    int quantity;
                    try {
                        quantity = Integer.parseInt(fields[2]);
                    } catch (NumberFormatException e) {
                        String errorMessage = "Invalid quantity (not an integer): " + fields[2] + " in row: " + line;
                        System.out.println(errorMessage);
                        if (errorLogWriter != null) {
                            errorLogWriter.write(errorMessage + System.lineSeparator());
                            errorLogWriter.flush(); // Ensure immediate write
                        }
                        errorCount++;
                        continue;
                    }

                    // validation - price (decimal)
                    double price;
                    try {
                        price = Double.parseDouble(fields[3]);
                    } catch (NumberFormatException e) {
                        String errorMessage = "Invalid price (not a decimal): " + fields[3] + " in row: " + line;
                        System.out.println(errorMessage);
                        if (errorLogWriter != null) {
                            errorLogWriter.write(errorMessage + System.lineSeparator());
                            errorLogWriter.flush(); // Ensure immediate write
                        }
                        errorCount++;
                        continue;
                    }

                    // validation - trade date (yyyy-MM-dd)
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    dateFormat.setLenient(false);
                    try {
                        dateFormat.parse(fields[4]);
                    } catch (ParseException e) {
                        String errorMessage = "Invalid trade date (not in yyyy-MM-dd format): " + fields[4] + " in row: " + line;
                        System.out.println(errorMessage);
                        if (errorLogWriter != null) {
                            errorLogWriter.write(errorMessage + System.lineSeparator());
                            errorLogWriter.flush(); // Ensure immediate write
                        }
                        errorCount++;
                        continue;
                    }

                    System.out.println("Valid row: " + line);
                    validTrades.add(fields);
                    validRowCount++;
                }
                if (errorCount > totalRows * 0.25) {
                    throw new HitErrorsThresholdException("Error threshold exceeded: " + errorCount + " out of " + totalRows + " rows failed.");
                }
                // Pass valid trades for batch insertion
                int[] result = processBatchInsert(validTrades, databaseManager);
                int insertedCount = result[0];
                int failedCount = result[1];

                // Summary
                System.out.println("Summary:");
                System.out.println("Total rows processed: " + totalRows);
                System.out.println("Valid rows inserted: " + insertedCount);
                System.out.println("Failed rows due to errors: " + errorCount);
                System.out.println("Failed rows during insertion: " + failedCount);

            } catch (HitErrorsThresholdException e) {
                System.out.println("Summary:");
                System.out.println("Total rows processed: " + totalRows);
                System.out.println("Valid rows inserted: " + 0); // No successful inserts due to threshold
                System.out.println("Failed rows due to validation errors: " + errorCount);
                System.out.println("Failed rows during batch insertion: " + validTrades.size()); // All valid rows are failed due to threshold

                throw new RuntimeException(e);
            } catch (FileNotFoundException e) {
//                throw new RuntimeException(e);
                System.out.println("File not found: " + e.getMessage());
            } catch (IOException e) {
//                throw new RuntimeException(e);
                System.out.println("IO Error: " + e.getMessage());
            } finally {
                try {
                    if (errorLogWriter != null) {
                        errorLogWriter.close();
                    }
                } catch (IOException e) {
                    System.out.println("Failed to close error log file: " + e.getMessage());
                }

            }
        } catch (IOException e) {
            System.out.println("Error initializing error log file: " + e.getMessage());
        }
    }

    public static int[] processBatchInsert(List<String[]> validTrades, DatabaseManager databaseManager) {
        String insertQuery = "INSERT INTO Trades (trade_id, ticker_symbol, quantity, price, trade_date) VALUES (?, ?, ?, ?, ?)";

        Connection connection = null;
        PreparedStatement statement = null;
        int successfulInserts = 0;
        int failedInserts = 0;

        try {
            connection = databaseManager.getConnection();
            statement = connection.prepareStatement(insertQuery);

            connection.setAutoCommit(false); // Start transaction

            for (String[] fields : validTrades) {
                statement.setString(1, fields[0]);
                statement.setString(2, fields[1]);
                statement.setInt(3, Integer.parseInt(fields[2]));
                statement.setDouble(4, Double.parseDouble(fields[3]));
                statement.setString(5, fields[4]);

                statement.addBatch();
            }
            try {
                int[] result = statement.executeBatch();
                connection.commit(); // Commit transaction if all statements succeed

                for (int i : result) {
                    if (i >= 0) { // Check for successful insertions
                        successfulInserts++;
                    } else {
                        failedInserts++;
                    }
                }
                System.out.println("Inserted valid trades into the database.");
            } catch (SQLException batchException) {
                // Rollback if there is an error during batch execution
                connection.rollback();
                System.out.println("Failed to insert trade into the database: " + batchException.getMessage());
                failedInserts += validTrades.size();
            }

        } catch (SQLException e) {

            System.out.println("Error with database connection: " + e.getMessage());
        } finally {
            // Ensure resources are closed
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
        return new int[]{successfulInserts, failedInserts};
    }

}
