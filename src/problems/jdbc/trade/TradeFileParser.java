package problems.jdbc.trade;


import java.io.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TradeFileParser {

    static int counter = 0;
    static int errorCounter = 0;
    static double errorThreshold = 0;
    static TradeTransaction tradeTransaction;

    public static void processFile(String filePath) throws IOException, InvalidInputException {
        List<TradeTransaction> tradeTransactions = new ArrayList<>();
        boolean invalidInput = true;
        String logFilePath = "error_log.txt"; // Path to the error log file

        getThresholdLimit(invalidInput);

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath));
             PrintWriter errorLog = new PrintWriter(new FileWriter(logFilePath))) {
            String line;
            reader.readLine(); // For reading the header of the file;
            while ((line = reader.readLine()) != null) {
                counter++;
                String[] data = line.split(",");
                try {
                    tradeTransaction = new TradeTransaction(data[0],
                            data[1],
                            Integer.parseInt(data[2]),
                            Double.parseDouble(data[3]),
                            new SimpleDateFormat("yyyy-MM-dd").parse(data[4]));
                } catch (NumberFormatException | ParseException e) {
                    if (e instanceof NumberFormatException) {
                        System.out.println("Number format exception happened while trying to parse trade with id" + data[0]);
                    } else {
                        System.out.println("Date Format exception happened while trying to parse trade with id" + data[0]);
                    }
                    writeTologFile(errorLog, line);
                    continue;
                }
                boolean isValid = validateCSV(data); //checking here the empty null value as number format exception is already handled in above try catch block
                if (!isValid) {
                    System.out.println("Escaping the line with error row data " + data[0]);
                    writeTologFile(errorLog, line);
                } else {
                    tradeTransactions.add(tradeTransaction);
//                    System.out.println("adding Trade transaction #" + counter + "in the arraylist >> " + tradeTransaction);
                }
            }

            if (errorCounter > (tradeTransactions.size() * errorThreshold) / 100) {
                throw new HitErrorsThresholdException("The threshold limit exceeds please try with another file");
            }
            TradeRepository.insertTrade(tradeTransactions, errorCounter);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static void writeTologFile(PrintWriter errorLog, String line) {
        errorLog.println("Error in row " + counter + ": " + line);
        errorCounter++;
    }

    private static void getThresholdLimit(boolean invalidInput) {
        while (invalidInput) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter the threshold from 1 to 100");
                String userInputThreshold = scanner.nextLine();
                errorThreshold = Double.parseDouble(userInputThreshold);
                if (errorThreshold < 1 || errorThreshold > 100) {
                    throw new InvalidInputException("Value must be between 1 and 100");
                }
                invalidInput = false;
            } catch (NumberFormatException e ) {
                System.out.println("Invalid input type.Please enter the valid double value");
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }
    }


    public static boolean validateCSV(String[] data) {
        for (String datum : data) {
            if (datum == null || datum.trim().isEmpty()) {
                System.out.println("Missing value in row " + datum);
                return false;
            }
        }
        return true;
    }
}
