package problems.trading;

import problems.trading.exceptions.InvalidInputException;

import java.io.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;

public class TradeFileReader {
    private static int counter = 0;
    private static boolean isValid = false;
    private static String filePath = "";

    public static void checkThresholdValue() {
        boolean inputValid = false;
        Scanner scanner = new Scanner(System.in);
        while (!inputValid) {
            try {
                System.out.print("Enter a threshold value: ");
                String s = scanner.nextLine();
                TradeService.errorThreshold = Double.parseDouble(s);
                if (TradeService.errorThreshold < 0 || TradeService.errorThreshold > 100) {
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

    public static ArrayList<TradeTransaction> readTransactionFileAndWriteToList(ArrayList<TradeTransaction> tradingTransactionArrayList) {
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
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(TradeService.readFile, true))) {
                        int errorAtLine = counter;
                        writer.write("Error in the row while reading from file>>> line number " + (errorAtLine + 1) + " >> " + line);
                        writer.newLine();
                    } catch (IOException ex) {
                        ex.getMessage();
                    }
                    TradeService.errorCount++;
                    continue;
                }

                // checking validation by null and empty values
                isValid = validateCSVFile(row);

                //if its valid then adding to the DeQueue
                if (isValid) {
                    System.out.println("adding transaction #" + counter + " in the queue >> " + tradeTransaction);
                    tradingTransactionArrayList.add(tradeTransaction);
                }
            }

            //if threshold limit increases then throwing an exception
            TradeService.checkingThreshold(tradingTransactionArrayList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tradingTransactionArrayList;
    }

    public static String checkFileName() throws FileNotFoundException {
        //checking file name from the user input
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter a file name >>>");
        String fileName = scanner.next().trim();
        boolean isFileNameValid = false;
        while (!isFileNameValid) {
            if (fileName.equals("trade_data")) {
                filePath = "/Users/Gaurav.Manchanda/src/boca-bc24-java-core-problems/" + fileName + ".csv";
                isFileNameValid = true;
            } else {
                throw new FileNotFoundException("Please enter a valid file name");
            }
        }
        return filePath;
    }

    private static boolean validateCSVFile(String[] data) {
        isValid = true;
        for (String trade : data) {
            if (trade == null || trade.trim().isEmpty()) {
                TradeService.errorCount++;
                isValid = false;
                System.out.println("error in the line >>" + counter);
                return isValid;
            }
            break;
        }

        return isValid;
    }
}
