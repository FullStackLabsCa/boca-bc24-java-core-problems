package problems.tradeOperations;


import problems.tradeOperations.exceptionFiles.HitErrorsThresholdException;
import problems.tradeOperations.manager.DatabaseManager;
import problems.tradeOperations.manager.ProcessBatchInsert;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInputCMD {

    public UserInputCMD(DatabaseManager databaseManager, double effectiveThreshold) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Please give me trade file path");
        System.out.println("/Users/Jay.Shah/Downloads/tradeFile.csv");
        System.out.println("/Users/Jay.Shah/Downloads/trades_sample_1000.csv");
        String filePath = scanner.nextLine();
        File file = new File(filePath);

        readFile(filePath, databaseManager, effectiveThreshold);

    }

    public static void readFile(String filePath, DatabaseManager databaseManager, Double effectiveThreshold) {

        List<String[]> validTrades = new ArrayList<>();
        int totalRows = 0;
        int errorCount = 0;
        int validRowCount = 0;

        FileWriter errorLogWriter = null;

        try {
            errorLogWriter = new FileWriter("src/problems/tradeOperations/extraUsedFiles/error_log.txt", true);

            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = br.readLine()) != null) {
                    totalRows++;

                    // Skip header line
                    if (line.startsWith("trade_id")) {
                        continue;
                    }

                    String[] fields = line.split(",");
                    if (fields.length != 6) {
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
                        quantity = Integer.parseInt(fields[3]);
                    } catch (NumberFormatException e) {
                        String errorMessage = "Invalid quantity (not an integer): " + fields[3] + " in row: " + line;
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
                        price = Double.parseDouble(fields[4]);
                    } catch (NumberFormatException e) {
                        String errorMessage = "Invalid price (not a decimal): " + fields[4] + " in row: " + line;
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
                        dateFormat.parse(fields[5]);
                    } catch (ParseException e) {
                        String errorMessage = "Invalid trade date (not in yyyy-MM-dd format): " + fields[5] + " in row: " + line;
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
//                if (errorCount > totalRows * 0.25) {
                System.out.println("********* effectiveThreshold: " + ((effectiveThreshold/100)));
                System.out.println("********* effectiveThreshold-line: " + (totalRows * (effectiveThreshold/100)));
                if (errorCount > totalRows * (effectiveThreshold/100)) {
                    throw new HitErrorsThresholdException("Error threshold exceeded: " + errorCount + " out of " + totalRows + " rows failed.");
                }
                // Pass valid trades for batch insertion
                ProcessBatchInsert pbi = new ProcessBatchInsert();
                int[] result = pbi.processBatchInsert(validTrades, databaseManager);
//                int[] result = processBatchInsert(validTrades, databaseManager);
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
}
