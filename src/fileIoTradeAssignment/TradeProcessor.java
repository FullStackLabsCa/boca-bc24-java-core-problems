package fileIoTradeAssignment;

import com.zaxxer.hikari.HikariDataSource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import static fileIoTradeAssignment.DatabaseHelper.getConnection;

public class TradeProcessor {

    private String filePath;
    private ArrayList<String> validLines;
    private static HikariDataSource dataSource = getConnection();

    private int errorCounter = 0;
    private final int errorThreshold = 2;


    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }


    public void readFile() throws IOException {
        validLines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String newLine;
            boolean isFirstLine = true;

            while ((newLine = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] tradeDetails = newLine.split(",");


                if (!validateTradeDetails(tradeDetails)) {
                    errorCounter++;
                    System.err.println("Invalid data at line: " + newLine);


                    if (errorCounter >= errorThreshold) {
                        System.err.println("Error threshold reached. Stopping file processing.");
                        break;
                    }
                } else {

                    validLines.add(newLine);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
            throw e;
        }
    }


    private boolean validateTradeDetails(String[] tradeDetails) {
        try {

            String ticker_identifier = tradeDetails[1];
            if (ticker_identifier.isEmpty()) {
                return false;
            }

            String ticker_symbol = tradeDetails[1];
            if (ticker_symbol.isEmpty()) {
                return false;
            }

            int quantity = Integer.parseInt(tradeDetails[2]);


            double price = Double.parseDouble(tradeDetails[3]);


            Date trade_date = Date.valueOf(tradeDetails[4]);

            return true;
        } catch (IllegalArgumentException e) {

            System.err.println("Validation error: " + e.getMessage());
            return false; //
        }
    }


    public void tradesInsertionMaker() throws SQLException {
        if (validLines.isEmpty()) {
            System.out.println("No valid lines to insert into the database.");
            return;
        }

        try (Connection conn = dataSource.getConnection()) {
            conn.setAutoCommit(false);
            String query = "INSERT INTO Trades(trade_identifier, ticker_symbol, quantity, price, trade_date) VALUES (?,?,?,?,?)";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                for (String line : validLines) {
                    String[] tradeDetails = line.split(",");

                    String trade_identifier =tradeDetails[0];
                    String ticker_symbol = tradeDetails[1];
                    int quantity = Integer.parseInt(tradeDetails[2]);
                    double price = Double.parseDouble(tradeDetails[3]);
                    Date trade_date = Date.valueOf(tradeDetails[4]);

                    stmt.setString(1, trade_identifier);
                    stmt.setString(2, ticker_symbol);
                    stmt.setInt(3, quantity);
                    stmt.setDouble(4, price);
                    stmt.setDate(5, trade_date);

                    stmt.addBatch();
                }
                stmt.executeBatch();
                conn.commit();
                System.out.println("Valid trades have been inserted into the database.");
            } catch (SQLException e) {
                conn.rollback();
                System.err.println("Error during batch insertion: " + e.getMessage());
            }
        }
    }
}


class HitErrorsThresholdException extends Exception {
    public HitErrorsThresholdException(String str) {
        super(str);
    }
}
































