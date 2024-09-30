package io.reactivestax.jdbc.trades.services;

import io.reactivestax.jdbc.trades.config.DatabaseHelper;
import io.reactivestax.jdbc.trades.exceptions.HitErrorsThresholdException;
import io.reactivestax.jdbc.trades.exceptions.InvalidThresholdValueException;
import io.reactivestax.jdbc.trades.model.TradePOJO;
import io.reactivestax.jdbc.trades.repo.TradeRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class TradesService implements TradeServiceInterface {
     static int threshold = 0;
     double errorThreshold = 0;
     static int currentLine = 0;
     static double thresholdPercent = 0;
     static int insertions;
    static List<TradePOJO> trades = new ArrayList<>();
    static String portNumber = "3306";
    String[] inputs;

    public static List<TradePOJO> getTrades() {
        return trades;
    }

    public static void setTrades() {
        TradesService.trades = new ArrayList<>();
    }

    public static int getThreshold() {
        return threshold;
    }

    public static void setThreshold(int threshold) {
        TradesService.threshold = threshold;
    }

    public double getErrorThreshold() {
        return errorThreshold;
    }

    public void setErrorThreshold(double errorThreshold) {
        this.errorThreshold = errorThreshold;
    }

    public static int getCurrentLine() {
        return currentLine;
    }

    public static void setCurrentLine(int currentLine) {
        TradesService.currentLine = currentLine;
    }

    public static double getThresholdPercent() {
        return thresholdPercent;
    }

    public static void setThresholdPercent(double thresholdPercent) {
        TradesService.thresholdPercent = thresholdPercent;
    }

    public static int getInsertions() {
        return insertions;
    }

    public static void setInsertions(int insertions) {
        TradesService.insertions = insertions;
    }


    private static final Logger log = LoggerFactory.getLogger(TradesService.class);


    public void userInput() {
        TradeRepo repo = new TradeRepo();
        boolean validInput = false;
        String[] inputs;
        Scanner sc = new Scanner(System.in);

        while (!validInput) {
            System.out.println("\nMENU:\nEnter 'case1': Console Read \nEnter 'case2': File Read");
            String path = "";
            String inputCase = sc.nextLine().toLowerCase();
            switch (inputCase) {
                case "case1":
                    System.out.println("Enter The File Path and Error Threshold");
                    String input = sc.nextLine();
                    inputs = input.split(" ");
                    if (inputs.length != 2) {
                        System.out.println("Invalid input. Please provide both file path and error threshold.");
                        continue;
                    }
                    path = inputs[0];
                    errorThreshold = Double.parseDouble(inputs[1]);
                    break;
                case "case2":
                    System.out.println("Enter The File Path");
                    path = sc.nextLine();
                    readThresholdLimitFromProperties();
                    break;
                default:
                    System.out.println("Please Select from one of the two options");
            }
            try {
                validateThresholdIsValid();
                validInput = readCSV(path);
                calculateThresholdPercent();
                validateThresholdLimitReached();
                if (validInput) {
                    int value = repo.processBatch(trades, DatabaseHelper.getConnection(portNumber));
                    if (value == 0) {
                        TradesService.setInsertions(0);
                        printSummary();
                        TradesService.setCurrentLine(0);
                        TradesService.setThreshold(0);
                        TradesService.setTrades();
                        validInput = false;
                    } else {
                        printSummary();
                        break;
                    }
                }
            } catch (InvalidThresholdValueException | HitErrorsThresholdException e) {
                log.error("Error",e);
                TradesService.setThreshold(0);
            }
        }
    }

    public void validateThresholdIsValid() {
        if (errorThreshold > 100 || errorThreshold < 1) {
            throw new InvalidThresholdValueException("Wrong Value of threshold, Please Try Again!");
        }
    }

    public void readThresholdLimitFromProperties() {
        try(FileReader reader = new FileReader("/Users/Ankit.Joshi/Desktop/Reactive/boca-bc24-java-core-problems/src/JDBC/trades/resources/application.properties");) {
            Properties properties = new Properties();
            properties.load(reader);
            errorThreshold = Double.parseDouble((String) properties.get("error.threshold"));
            System.out.println("errorThreshold: " + errorThreshold);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void validateThresholdLimitReached() {
        if (threshold > thresholdPercent) {
            throw new HitErrorsThresholdException("Threshold Reached, Try Again!");
        }
    }

    public void calculateThresholdPercent() {
        TradesService.setThresholdPercent((errorThreshold / 100) * TradesService.getCurrentLine());
    }

    public boolean readCSV(String path) {
        try (BufferedReader reader = new BufferedReader(new FileReader(path));
             BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/Ankit.Joshi/Desktop/Reactive/boca-bc24-java-core-problems/src/JDBC/trades/resources/error_log.txt", true))) {
            String line;
            while ((line = reader.readLine()) != null) {
                TradesService.setCurrentLine(TradesService.getCurrentLine() +1);
                String[] data = line.split(",");
                try {
                    trades.add(new TradePOJO(data[0], data[1], data[2], Integer.parseInt(data[3]), Double.parseDouble(data[4]), Date.valueOf(data[5])));
                } catch (IllegalArgumentException e) {
                    TradesService.setThreshold(TradesService.getThreshold() + 1);
                    writer.write(line + "\n");
                    log.error("Error processing line: {}. Exception: {}", line, e.getMessage());
                }
            }
        } catch (IOException ex) {
            log.error("Failed to read the CSV file: {}", ex.getMessage());
            return false;
        }
        return true;
    }

    public void printSummary() {
        System.out.println("Summary:");
        System.out.println("Records processed: " + currentLine);
        System.out.println("Successful inserts: " + insertions);
        System.out.println("Error count: " + threshold);
    }
}
