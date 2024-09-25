package JDBC.trades.services;

import JDBC.trades.config.DatabaseHelper;
import JDBC.trades.exceptions.HitErrorsThresholdException;
import JDBC.trades.exceptions.InvalidThresholdValueException;
import JDBC.trades.main.TradesMain;
import JDBC.trades.model.TradePOJO;
import JDBC.trades.repo.TradeRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class TradesService implements TradeServiceInterface {
    private static final Logger log = LoggerFactory.getLogger(TradesMain.class);
    static List<TradePOJO> trades = new ArrayList<>();
    public static int threshold = 0;
    public static double errorThreshold = 0;
    public static int currentLine = 0;
    public static double thresholdPercent = 0;
    public static int insertions;
    String[] inputs;
    static String portNumber = "3306";
    static TradeRepo repo = new TradeRepo();

    public void userInput() {
        boolean validInput = false;
        String[] inputs = new String[1];
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
            }
            try {
                validateThresholdIsValid();
                validInput = readCSV(path);
                calculateThresholdPercent();
                System.out.println(thresholdPercent);
                validateThresholdLimitReached();
                if (validInput) {
                    int value = repo.processBatch(trades, DatabaseHelper.getConnection(portNumber));
                    if (value == 0) {
                        insertions = 0;
                        printSummary();
                        currentLine = 0;
                        threshold = 0;
                        trades = new ArrayList<>();
                        validInput = false;
                    } else {
                        printSummary();
                        break;
                    }
                }
            } catch (InvalidThresholdValueException | HitErrorsThresholdException e) {
                System.out.println(e);
                threshold = 0;
            }
        }
    }

    public void validateThresholdIsValid() {
        if (errorThreshold > 100 || errorThreshold < 1) {
            throw new InvalidThresholdValueException("Wrong Value of threshold, Please Try Again!");
        }
    }

    public void readThresholdLimitFromProperties() {
        try {
            FileReader reader = new FileReader("/Users/Ankit.Joshi/Desktop/Reactive/boca-bc24-java-core-problems/src/JDBC/trades/resources/application.properties");
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
        thresholdPercent = (errorThreshold / 100) * currentLine;
    }

    public boolean readCSV(String path) {
        try (BufferedReader reader = new BufferedReader(new FileReader(path));
             BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/Ankit.Joshi/Desktop/Reactive/boca-bc24-java-core-problems/src/JDBC/trades/resources/error_log.txt", true))) {
            String line;
            while ((line = reader.readLine()) != null) {
                currentLine++;
                String[] data = line.split(",");
                try {
                    trades.add(new TradePOJO(data[0], data[1], data[2], Integer.parseInt(data[3]), Double.parseDouble(data[4]), Date.valueOf(data[5])));
                } catch (IllegalArgumentException e) {
                    threshold++;
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
