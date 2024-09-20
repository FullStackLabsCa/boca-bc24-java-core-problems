package problems.tradeFileParser;

import problems.tradeFileParser.controller.TradeDBController;
import problems.tradeFileParser.exceptions.ParseingThresholdException;
import problems.tradeFileParser.model.TradeModel;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MainRunner {
    public static List<TradeModel> tradeList = new ArrayList<>();
    public static double threshold = 0.0;

    public static void main(String[] args) throws SQLException, IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter file path: ");
        String filePath = scanner.next();

        readTrade(filePath);

        new TradeDBController().insertTrade();
        readThreshold();
        scanner.close();
    }

    public static double readThreshold() {
        try (Scanner scanner = new Scanner(new FileReader("/Users/Dhruv.Desai/source/Student/boca-bc24-java-core-problems/src/problems/tradeFileParser/application.properties"))) {
            scanner.useDelimiter("=");
            scanner.next();
            threshold = scanner.nextDouble();
        } catch (IOException | InputMismatchException e) {
            System.out.println("Error");
        }
        return threshold;
    }

    public static void readTrade(String filePath) {
        int countTry = 0;
        int countSuccess = 0;
        int totalrows = 0;

        try (Scanner scanner = new Scanner(new FileReader(filePath))) {
            scanner.useDelimiter("\n|,");

            while (scanner.hasNext()) {
                try {
                    totalrows++;
                    String trade_id = scanner.next();
                    String trade_identifier = scanner.next();
                    String ticker_symbol = scanner.next();
                    int quantity = scanner.nextInt();
                    double price = scanner.nextDouble();
                    String trade_date = scanner.next();

                    TradeModel tradeModel = new TradeModel(trade_id, trade_identifier, ticker_symbol, quantity, price, trade_date);
                    tradeList.add(tradeModel);
                    countSuccess++;
                } catch (InputMismatchException e) {
                    countTry++;
                    System.err.println("Error at line: " + totalrows + " >>> " + scanner.next());
                    e.printStackTrace();
                    if (scanner.hasNext()) {
                        scanner.nextLine();
                    }
                    double incorrectRows = (((double) countTry / totalrows) * 100);
                    if (incorrectRows > threshold) {
                        throw new ParseingThresholdException("Threshold reached max number of incorrect rows");
                    }
                }
            }
        } catch (FileNotFoundException | ParseingThresholdException e) {
            throw new RuntimeException(e);
        }
        System.out.println("-------------------------------After parsing-------------------------------");
        System.out.println("Failed rows: " + countTry);
        System.out.println("Success rows: " + countSuccess);
        System.out.println("Total rows read: " + totalrows);
        System.out.println("---------------------------------------------------------------------------");
        System.out.println();
    }
}