package problems.trading;

import problems.trading.exceptions.InvalidInputException;

import java.io.*;
import java.sql.Date;
import java.util.ArrayList;

public class TradeFileReader {
    private static int counter = 0;
    private static boolean isValid = false;

    public static void checkThresholdValue(String str) {
        TradeService.isThresholdValid = false;
        TradeService.errorThreshold = Double.parseDouble(str);
        if (TradeService.errorThreshold < 0 || TradeService.errorThreshold > 100) {
            throw new InvalidInputException("Not valid input. Please enter a valid threshold limit in between 1 to 100");
        }
        TradeService.isThresholdValid = true;
    }

    public static ArrayList<TradeTransaction> readTransactionFileAndWriteToList(String filePath) {
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

                //if its valid then adding to the list
                if (isValid) {
                    System.out.println("adding transaction #" + counter + " in the queue >> " + tradeTransaction);
                    TradeService.tradingTransactionArrayList.add(tradeTransaction);
                }
            }

            //if threshold limit increases then throwing an exception
            TradeService.checkingThreshold(TradeService.tradingTransactionArrayList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return TradeService.tradingTransactionArrayList;
    }

    public static String checkFileName(String fileName) throws FileNotFoundException {
        TradeService.filePath = "";
        // Check if the read file exists and delete it at the start of execution
        TradeService.checkReadLogFileExistOrNot();

        // Check if the write file exists and delete it at the start of execution
        TradeService.checkWriteLogFileExistOrNot();
        //checking file name from the user input
        TradeService.isFileExist = false;
        while (!TradeService.isFileExist) {
            if (fileName.equals("trade_data")) {
                TradeService.filePath = "/Users/Gaurav.Manchanda/src/boca-bc24-java-core-problems/" + fileName + ".csv";
                TradeService.isFileExist = true;
                System.out.println("File found in the system");
            } else {
                throw new FileNotFoundException("Please enter a valid file name");
            }
        }
        return TradeService.filePath;
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
