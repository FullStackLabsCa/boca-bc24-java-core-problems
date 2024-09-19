package problems.trading;

import java.io.*;
import java.sql.Date;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingDeque;

public class TradeFileReader {
    private static int counter = 0;
    private static int errorLengthInQueue = 0;
    private static double errorThreshold = 0;
    private static boolean isValid = false;
    private static String logFilePath = "/Users/Gaurav.Manchanda/Sources/Student-mode/error_log.txt";

    public static void checkThresholdValue() {
        boolean inputValid = false;
        Scanner scanner = new Scanner(System.in);
        while (!inputValid) {
            try {
                System.out.print("Enter a threshold value: ");
                String s = scanner.nextLine();
                errorThreshold = Double.parseDouble(s);
                if (errorThreshold < 0 || errorThreshold > 100) {
                    throw new InvalidInputException("Not valid input. Please enter a valid threshold limit in between 1 to 100");
                }
                inputValid = true;
            } catch (NumberFormatException e) {
                inputValid = false;
                System.out.println("Please enter a valid threshold limit in between 1 to 100");
            } catch (InvalidInputException e) {
                inputValid = false;
                System.out.println("Please enter a valid threshold limit in between 1 to 100");
            }
        }
    }

    public static LinkedBlockingDeque<TradeTransaction> readTransactionFileAndWriteToQueue(String filePath, LinkedBlockingDeque<TradeTransaction> tradingTransactionDeQueue) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            reader.readLine();
            TradeTransaction tradeTransaction;
            while ((line = reader.readLine()) != null) {
                counter++;
                String[] row = line.strip().split(",");

                //throwing the number format Exception
                try {
                    tradeTransaction = new TradeTransaction(row[0], row[1], row[2], Integer.parseInt(row[3]), Double.parseDouble(row[4]), Date.valueOf(row[5]));
                } catch (NumberFormatException e) {
                    System.out.println(e.getMessage());
                    //writing to file
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFilePath, true))) {
                        int errorAtLine = counter;
                        writer.write("Error in the row " + (errorAtLine + 1) + ">>" + line);
                        writer.newLine();
                    } catch (IOException ex) {
                        ex.getMessage();
                    }
                    errorLengthInQueue++;
                    continue;
                }

                // checking validation by null and empty values
                isValid = validateCSVFile(row);

                //if its valid then adding to the DeQueue
                if (isValid) {
                    System.out.println("adding transaction #" + counter + " in the queue >> " + tradeTransaction);
                    tradingTransactionDeQueue.put(tradeTransaction);
                }
            }

            //if threshold limit increases then throwing an exception
            if (errorLengthInQueue > (errorThreshold * tradingTransactionDeQueue.size()) / 100) {
                throw new HitErrorsThresholdException("Errors exceeded threshold limit");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return tradingTransactionDeQueue;
    }

    private static boolean validateCSVFile(String[] data) {
        isValid = true;
        for (String trade : data) {
            if (trade == null || trade.trim().isEmpty() || trade.contains("INVALID")) {
                errorLengthInQueue++;
                isValid = false;
                System.out.println("error in the line >>" + counter);
                return isValid;
            }
            break;
        }

        return isValid;
    }
}
