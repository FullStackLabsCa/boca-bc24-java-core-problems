package problems.tradingPlatform.databasehelpers;

import problems.tradingPlatform.helpers.CommonFunctions;
import problems.tradingPlatform.helpers.ErrorManager;
import problems.tradingPlatform.services.TradeReader;
import problems.tradingPlatform.services.TradeWriter;
import problems.tradingPlatform.models.Trade;

import java.io.*;
import java.util.*;

public class DatabaseOperations  {
    double errorThresholdCount = 25;
    String filePath = "";
    File file;

    public void readTradeData() {
        boolean isValidFile = false;
        while (!isValidFile) {
            Scanner scanner = new Scanner(System.in);
            isValidFile = checkForValidFile(scanner);

            if (isValidFile) {
                double percentage = askUserForInput(scanner);
                try  {
                    long totalRows = getTotalRowsCount(file);
                    errorThresholdCount = (double) (totalRows *  percentage != -1 ?  percentage : 0.25);
                    TradeReader tradeReader = new TradeReader();
                    List<Trade> tradeList = tradeReader.readTradeData(filePath, errorThresholdCount);
                    if (!tradeList.isEmpty()) {
                        System.out.println("Trade Read successfully for : "+tradeList.size() + " Rows");
                        TradeWriter tradeWriter = new TradeWriter();
                        tradeWriter.writeTradeData(tradeList,errorThresholdCount);
                    }
                } catch ( InputMismatchException e) {
                    ErrorManager.checkForErrorThreshold(errorThresholdCount,"\n An error occurred during reading the file : InputMismatchException."+e.getMessage(),true);
                }
                finally {
                    scanner.close();
                }
            } else {
                ErrorManager.checkForErrorThreshold(errorThresholdCount,"\n File Does not Exits. Please Enter the valid file path",true);
            }
        }
    }

    public double askUserForInput(Scanner scanner) {
        int choice = -1;
        double percentage = -1;

        do {
            System.out.println("Select an option:");
            System.out.println("1. Use Percentage from Application Properties");
            System.out.println("2. You want to add a Percentage Threshold");
            System.out.print("Enter your choice: ");

            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Please enter a valid input.");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    percentage = CommonFunctions.getDataFromApplicationProperties();
                    System.out.println("Percentage from application properties: " + percentage);
                    break;
                case 2:
                    percentage = getPercentageThreshold(scanner);
                    System.out.println("User-defined percentage threshold: " + percentage);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 1 && choice != 2);

        return percentage;
    }

    public int getPercentageThreshold(Scanner scanner) {
        int percentage = -1;
        while (percentage < 0 || percentage > 100) {
            System.out.print("Please Enter the Error Threshold Percentage (0-100): ");
            try {
                percentage = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid integer value ");
                scanner.next();
                continue;
            }

            if (percentage < 0 || percentage > 100) {
                System.out.println("Invalid input. Please enter a percentage between 0 and 100.");
            }
        }
        return  percentage;
    }

    public boolean checkForValidFile(Scanner scanner)
    {
        System.out.println("Please Enter the file path");
        filePath = scanner.nextLine();
        file = new File(filePath);
        return file.exists();
    }

    public long getTotalRowsCount(File file) {
        long totalRows = -1;
        try (Scanner scanner = new Scanner(new FileReader(file))) {
            while (scanner.hasNext()) {
                scanner.nextLine();
                totalRows++;
            }
            return totalRows;
        } catch (IOException e) {
            ErrorManager.checkForErrorThreshold(errorThresholdCount,"\n Failed to open File : getTotalRowsCount ",true);
        }
        return totalRows;
    }

}

