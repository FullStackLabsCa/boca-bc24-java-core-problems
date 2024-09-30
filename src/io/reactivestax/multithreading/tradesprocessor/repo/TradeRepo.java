package io.reactivestax.multithreading.tradesprocessor.repo;


import com.zaxxer.hikari.HikariDataSource;
import io.reactivestax.jdbc.trades.services.TradesService;
import io.reactivestax.multithreading.tradesprocessor.utils.GetDataFromProperties;

import java.sql.*;

public class TradeRepo {
    String propertiesFile = "/Users/Ankit.Joshi/Desktop/Reactive/boca-bc24-java-core-problems/src/io/reactivestax/multithreading/tradesprocessor/resources/ data.properties";
    GetDataFromProperties getDataFromProperties = new GetDataFromProperties();


    public void addTradeToTradePayloads(String line, String tradeId, String status, String reason, HikariDataSource dataSource) {
        String query = getDataFromProperties.readValueFromPropertiesFile(propertiesFile, "trade_payload.insert");
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, tradeId);
            preparedStatement.setString(2, status);
            preparedStatement.setString(3, reason);
            preparedStatement.setString(4, line);
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public String getTradePayLoad(String trade_id, HikariDataSource dataSource) {
        String payload = "";
        String query = getDataFromProperties.readValueFromPropertiesFile(propertiesFile, "trade_payload.getSpecific");
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, trade_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                payload = resultSet.getString("payload");
            }
        } catch (SQLException e) {
            TradesService.setThreshold(TradesService.getThreshold() + 1);
            System.out.println("Exception: " + e);
        }
        return payload;
    }

    public boolean isCusipPresent(String cusip, HikariDataSource dataSource) {
        boolean status = false;
        String symbol = "empty";
        //securities_custom_reference.getcusip
        String query = getDataFromProperties.readValueFromPropertiesFile(propertiesFile, "securities_custom_reference.getcusip");
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, cusip);
            ResultSet resultSet = preparedStatement.executeQuery();
            status = isTickerPresent(cusip, resultSet, symbol, status);
        } catch (SQLException e) {
            TradesService.setThreshold(TradesService.getThreshold() + 1);
            System.out.println("Exception: " + e);
        }
        return status;
    }

    private static boolean isTickerPresent(String tickerSymbol, ResultSet resultSet, String symbol, boolean status) {
        try {
            if (resultSet.next()) {
                symbol = resultSet.getString("cusip");
            }
            if (!symbol.equals(tickerSymbol)) {
                status = false;
                //throw new HitErrorsThresholdException("Ticker Symbol doesn't Exist");
            } else {
                status = true;
            }

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return status;
    }


    public void addTradeToJournalEntry(String payload, String postedStatus, HikariDataSource dataSource) {
        String[] values = payload.split(",");
        String query = getDataFromProperties.readValueFromPropertiesFile(propertiesFile, "journalEntry.setData");
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            // trade_id,transaction_time,account_number,cusip,activity,quantity,price
            //TDB_00000000,2024-09-19 22:16:18,TDB_CUST_5214938,V,SELL,683,638.02
            //  (account_number, security_cusip, direction, quantity, posted_status, transaction_time, entry_time, trade_id)
            preparedStatement.setString(1, values[2]);
            preparedStatement.setString(2, values[3]);
            preparedStatement.setString(3, values[4]);
            preparedStatement.setString(4, values[5]);
            preparedStatement.setString(5, postedStatus);
            preparedStatement.setString(6, values[1]);
            preparedStatement.setString(7, String.valueOf(new Timestamp(System.currentTimeMillis())));
            preparedStatement.setString(8, values[0]);
            preparedStatement.execute();
        } catch (SQLException e) {
            TradesService.setThreshold(TradesService.getThreshold() + 1);
            System.out.println("Exception: " + e);
        }

    }

    public void addTradeToPosition(String accountNumber, String cusip, String position, String buySell, HikariDataSource dataSource) {


        try(Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            int version = getTradePositionVesion(accountNumber, cusip, connection);
            if(version != -1) {
                int currentPosition = getPosition(accountNumber, cusip, connection);
                String updateQuery = "UPDATE positions SET positions = ?, version = version + 1 WHERE account_number = ? AND version = ?";
                System.out.println("Update");
                System.out.println(currentPosition);

                try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

                    if (buySell.equals("Buy")) {
                        preparedStatement.setInt(1, Integer.parseInt(position) +currentPosition);
                    } else {
                        preparedStatement.setInt(1, currentPosition - Integer.parseInt(position));
                    }
                    preparedStatement.setString(2, accountNumber);
                    preparedStatement.setInt(3, version);
                    System.out.println("one ||-->");
                    int rowsUpdated = preparedStatement.executeUpdate();
                    if (rowsUpdated > 0) {
                        // insertIntoJournal(accountNumber, cusip, position, dataSource);
                    }
                }
            }else{
                String insertQuery = "INSERT INTO positions (account_number, security_cusip, positions, posted_time, version) VALUES (?, ?, ?, ?, 0)";
                try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
                    insertStatement.setString(1, accountNumber);
                    insertStatement.setString(2, cusip);
                    insertStatement.setInt(3, Integer.parseInt(position));
                    insertStatement.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
                    insertStatement.executeUpdate();
                    connection.commit();
                    // insertIntoJournal(accountNumber, cusip, position, dataSource);
                }
            }
            } catch (SQLException e) {
            System.out.println("É"+e);
        }


    }

    private int getPosition(String accountNumber, String cusip, Connection connection) {
        String query = "SELECT positions FROM positions WHERE account_number = ? AND security_cusip = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, accountNumber);
            statement.setString(2, cusip);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("positions");
            } else {
                return -1;
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static int getTradePositionVesion(String accountNumber, String cusip, Connection connection) {
        String query = "SELECT version FROM positions WHERE account_number = ? AND security_cusip = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, accountNumber);
            statement.setString(2, cusip);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("version");
            } else {
                return -1;
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }
}


