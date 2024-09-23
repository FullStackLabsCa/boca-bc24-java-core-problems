package tradefileprocessing;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TradesProcessor {

    private Connection connection;
    private List<String[]> trades;
    private List<String> symbolOfTrades;
    private List<String> wrongTrades = new ArrayList<>();

    public TradesProcessor(Connection connection, List<String[]> trades) {
        this.connection = connection;
        this.trades = trades;
        this.symbolOfTrades = getSymbol();
    }

    private List<String> getSymbol() {
        String query = "SELECT symbol FROM SecuritiesReference";
        List<String> symbols = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                symbols.add(resultSet.getString("symbol"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return symbols;
    }

    public void processTrades() throws HitErrorsThresholdException {
        int count = 0;
        int tradeSize = trades.size();
        double wrongRows = 0.0;
        double threshold = Double.parseDouble(PropertiesLoader.getProperty("app.database.threshold"));
        String query = "INSERT INTO trades (trade_id, trade_identifier, ticker_symbol, quantity, price, trade_date) VALUES (?,?,?,?,?,?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            connection.setAutoCommit(false);
            for (String[] trade : trades) {
                String tickerSymbol = trade[2];
                if (!symbolOfTrades.contains(tickerSymbol)) {
                    wrongTrades.add(Arrays.toString(trade));
                    errorLogging(" has error and the symbol which is not present is ");
                    count++;
                    continue;
                }
                preparedStatement.setString(1, trade[0]);
                preparedStatement.setString(2, trade[1]);
                preparedStatement.setString(3, trade[2]);
                preparedStatement.setInt(4, Integer.parseInt(trade[3]));
                preparedStatement.setDouble(5, Double.parseDouble(trade[4]));
                preparedStatement.setDate(6, Date.valueOf(trade[5]));
                preparedStatement.addBatch();
            }

            int[] batchResult = preparedStatement.executeBatch();
            wrongRows = (double) (count * 100) / tradeSize;
            if (wrongRows > threshold) {

                errorLogging("You have passed the error threshold");
                connection.rollback();
                throw new HitErrorsThresholdException("Database threshold has passed");
            } else {
                try {
                    batchResult = preparedStatement.executeBatch();
                } catch (BatchUpdateException batchUpdateException) {
                    System.out.println("Error message: " + batchUpdateException.getMessage());
                    batchResult = batchUpdateException.getUpdateCounts();
                } finally {
                    if (batchResult != null) {
                        for (int i = 0; i < batchResult.length; i++) {
                            if (batchResult[i] == -3) {
                                System.out.println("Index: " + i + ", Value: " + batchResult[i]);
                                wrongTrades.add(Arrays.toString(trades.get(i)));
                                errorLogging("Duplicate insertion was reported for "+trades.get(i)[2]);
                            }
                        }
                    }
                }
                connection.commit();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void errorLogging(String errorInfo) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("error.txt"))) {
            for (String wrongTrade : wrongTrades) {
                String[] index = wrongTrade.split(",");
                bufferedWriter.write( "Line "+index[0].substring(1) + errorInfo + index[2]);
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
