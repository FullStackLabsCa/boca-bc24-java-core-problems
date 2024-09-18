/*
package problems.tradeOperations;

import problems.tradeOperations.oldFiles.TradeValidator;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInputCMD {

    public UserInputCMD(DatabaseManager databaseManager) {
        TradeValidator tradeValidator = new TradeValidator();
        // 1. The file path will be provided by the user at runtime.
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please give me trade file path");
        System.out.println("/Users/Jay.Shah/Downloads/tradeFile.csv");
        String filePath = scanner.nextLine();
        File file = new File(filePath);
        readFile(filePath, databaseManager);
    }

    public static void readFile(String filePath, DatabaseManager databaseManager) {

        List<String[]> validTrades = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {

                // Skip header line
                if (line.startsWith("trade_id")) {
                    continue;
                }

                String[] fields = line.split(",");
                if (fields.length != 5) {
                    System.out.println("Invalid row (Incorrect numbeer of fields): " + line);
                    continue;
                }

                // validation - quantity (integer)
                int quantity;
                try {
                    quantity = Integer.parseInt(fields[2]);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid quantity (not an integer): " + fields[2] + " in row: " + line);
                    continue;
                }

                // validation - price (decimal)
                double price;
                try {
                    price = Double.parseDouble(fields[3]);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid price (not a decimal): " + fields[3] + " in row: " + line);
                    continue;
                }

                // validation - trade date (yyyy-MM-dd)
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                dateFormat.setLenient(false);
                try {
                    dateFormat.parse(fields[4]);
                } catch (ParseException e) {
                    System.out.println("Invalid trade date (not in yyyy-MM-dd format): " + fields[4] + " in row: " + line);
                    continue;
                }

                System.out.println("Valid row: " + line);
                validTrades.add(fields);
//                // Insert valid row into the database
//                insertTradeData(fields[0], fields[1], quantity, price, fields[4], databaseManager);

            }
            processBatchInsert(validTrades,databaseManager);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void processBatchInsert(List<String[]> validTrades, DatabaseManager databaseManager) {
        String insertQuery = "INSERT INTO Trades (trade_id, ticker_symbol, quantity, price, trade_date) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = databaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(insertQuery)) {

            connection.setAutoCommit(false); // Start transaction

            for (String[] fields : validTrades) {
                statement.setString(1, fields[0]);
                statement.setString(2, fields[1]);
                statement.setInt(3, Integer.parseInt(fields[2]));
                statement.setDouble(4, Double.parseDouble(fields[3]));
                statement.setString(5, fields[4]);

                statement.addBatch();
            }
            statement.executeBatch();
            connection.commit(); // Commit transaction

            System.out.println("Inserted valid trades into the database.");
        } catch (SQLException e) {
            System.out.println("Failed to insert trade into the database: " + e.getMessage());
            try {
                if (databaseManager.getConnection() != null){
                    databaseManager.getConnection().rollback(); // rollback
                }

            } catch (SQLException ex) {
                System.out.println("Rollback failed: " + ex.getMessage());
            }
        }
    }

//    private static void insertTradeData(String tradeId, String tickerSymbol, int quantity, double price, String tradeDate, DatabaseManager databaseManager) {
//        String insertQuery = "INSERT INTO Trades (trade_id, ticker_symbol, quantity, price, trade_date) VALUES (?, ?, ?, ?, ?)";
//
//        try (Connection connection = databaseManager.getConnection();
//             PreparedStatement statement = connection.prepareStatement(insertQuery)) {
//
//            statement.setString(1, tradeId);
//            statement.setString(2, tickerSymbol);
//            statement.setInt(3, quantity);
//            statement.setDouble(4, price);
//            statement.setString(5, tradeDate);
//
//            statement.addBatch();
//            statement.executeBatch();
//
//            System.out.println("Inserted trade: " + tradeId + " into the database.");
//        } catch (SQLException e) {
//            System.out.println("Failed to insert trade into the database: " + e.getMessage());
//        }
//    }

}*/
