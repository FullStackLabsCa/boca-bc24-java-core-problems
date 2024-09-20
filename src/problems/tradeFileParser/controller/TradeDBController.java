package problems.tradeFileParser.controller;

import problems.tradeFileParser.databaseConnection.HikariCP;
import problems.tradeFileParser.exceptions.InsertThresholdException;
import problems.tradeFileParser.model.TradeModel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static problems.tradeFileParser.MainRunner.*;

public class TradeDBController {
    public static List<String> errorList = new ArrayList<>();
    static double error = readThreshold();

    public void insertTrade() throws SQLException, IOException {
        boolean flag = true;
        String query = "INSERT INTO Trades (trade_id, trade_identifier, ticker_symbol, quantity, price, trade_date) VALUES (?, ?, ?, ?, ?, ?)";
        String getAllSymbolQuery = "SELECT symbol FROM SecuritiesReference";

        try (Connection conn = HikariCP.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {

            List<String> allSymbolList = new ArrayList<>();
            try (PreparedStatement prepareStatementSecuritiesReferenceColumn = conn.prepareStatement(getAllSymbolQuery);
                 ResultSet rs = prepareStatementSecuritiesReferenceColumn.executeQuery()) {
                while (rs.next()) {
                    allSymbolList.add(rs.getString("symbol"));
                }
            }

            int totalRowsToBeInserted = 0;
            int totalRowsInserted = 0;
            int totalRowsFailedtoInsert = 0;
            double errorPercentage;
            int rowNum= 1;

            while (flag) {
                for (TradeModel trade : tradeList) {
                    conn.setAutoCommit(false);
                    totalRowsToBeInserted++;
                    if (allSymbolList.contains(trade.getTicker_symbol())) {
                        preparedStatement.setString(1, trade.getTrade_id());
                        preparedStatement.setString(2, trade.getTrade_identifier());
                        preparedStatement.setString(3, trade.getTicker_symbol());
                        preparedStatement.setInt(4, trade.getQuantity());
                        preparedStatement.setDouble(5, trade.getPrice());
                        preparedStatement.setString(6, trade.getTrade_date());
                        preparedStatement.addBatch();
                        totalRowsInserted++;
                    } else {
                        errorList.add(trade.getTrade_id());
                        errorList.add(trade.getTrade_identifier());
                        errorList.add(trade.getTicker_symbol());
                        errorList.add(String.valueOf(trade.getQuantity()));
                        errorList.add(String.valueOf(trade.getPrice()));
                        totalRowsFailedtoInsert++;

                        logError(trade);
                    }
                }
                errorPercentage = ((double) totalRowsFailedtoInsert / totalRowsToBeInserted) * 100;
                if (errorPercentage < error) {
                    int[] rowsInserted= preparedStatement.executeBatch();

                    conn.commit();
                } else {
                    conn.rollback();
                    throw new InsertThresholdException("Threshold reached...rolling back");
                }
                flag = false;
            }
            System.out.println();
            System.out.println("------------------------While inserting to database------------------------");
            System.out.println("Failed rows: " + totalRowsFailedtoInsert);
            System.out.println("Success rows: " + totalRowsInserted);
            System.out.println("Total rows read: " + totalRowsToBeInserted);
            System.out.println("---------------------------------------------------------------------------");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private void logError(TradeModel trade) {
        File logFile = new File("errorLog.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFile, true))) {
            writer.write("Error inserting trade: ");
            writer.write("Trade ID: " + trade.getTrade_id() + ", ");
            writer.write("Trade Identifier: " + trade.getTrade_identifier() + ", ");
            writer.write("Ticker Symbol: " + trade.getTicker_symbol() + ", ");
            writer.write("Quantity: " + trade.getQuantity() + ", ");
            writer.write("Price: " + trade.getPrice() + ", ");
            writer.write("Trade Date: " + trade.getTrade_date());
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }
}
