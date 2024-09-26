package problems.tradefileparser.controller;

import problems.tradefileparser.databaseConnection.HikariCP;
import problems.tradefileparser.exceptions.InsertThresholdException;
import problems.tradefileparser.model.TradeModel;
import problems.tradefileparser.reader.ThresholdReader;
import problems.tradefileparser.reader.ThresholdReaderImplementation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class TradeDOAImplementation implements TradeDOA {
    private static final List<String> errorList = new ArrayList<>();
    static ThresholdReader thresholdReader = new ThresholdReaderImplementation();
    static double error = thresholdReader.readThreshold();

    @Override
    public void insertTrade(List<TradeModel> tradeList){
        boolean flag = true;
        String query = "INSERT INTO Trades (trade_id, trade_identifier, ticker_symbol, quantity, price, trade_date) VALUES (?, ?, ?, ?, ?, ?)";
        String getAllSymbolQuery = "SELECT symbol FROM SecuritiesReference";

        try (Connection conn = HikariCP.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            conn.setAutoCommit(false);
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
            double errorPercentage = 0;

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
                    preparedStatement.executeBatch();
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
            System.out.println();
            System.out.println("Error Percentage: " + errorPercentage);
            System.out.println("Threshold: " + error);
            System.out.println("---------------------------------------------------------------------------");

        } catch (IllegalArgumentException | InsertThresholdException e) {
            throw new IllegalArgumentException(e);
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
