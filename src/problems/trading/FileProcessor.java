package problems.trading;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingDeque;

public class FileProcessor {
    private static int counter = 0;
    private static int errorLengthInQueue = 0;
    private static double errorThreshold = 0;
    private static boolean isValid = false;

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

                try {
                    tradeTransaction = new TradeTransaction(row[0], row[1], Integer.parseInt(row[2]), Double.parseDouble(row[3]), Date.valueOf(row[4]));
                } catch (NumberFormatException e) {
                    System.out.println(e.getMessage());
                    errorLengthInQueue++;
//                    e.printStackTrace();
                    continue;
                }

                isValid = validateCSVFile(row);

                if (isValid) {
                    System.out.println("adding transaction #" + counter + " in the queue >> " + tradeTransaction);
                    tradingTransactionDeQueue.put(tradeTransaction);
                }
            }
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
        for (int i = 0; i < data.length; i++) {
            if (data[i] == null || data[i].trim().isEmpty() || data[i].contains("INVALID")) {
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
