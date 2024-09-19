package fileIoTradeAssignment;

import com.zaxxer.hikari.HikariDataSource;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
    private final int readFileErrorThreshold = 6;


    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }


    public void readTradeFile() throws FileNotFoundException, HitReadFileErrorsThresholdException {
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


                    if (errorCounter >= readFileErrorThreshold) {
                        System.out.println("errorThreshold : " + readFileErrorThreshold + " errorCounter : " + errorCounter);


                        throw new HitReadFileErrorsThresholdException("threshold limit exceeded while reading file");

                    }
                } else {

                    validLines.add(newLine);
                }
            }

        } catch (FileNotFoundException e) {

            throw new FileNotFoundException("file doesn't exists ");
//            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private boolean validateTradeDetails(String[] tradeDetails) throws Exception {
        try {


            int trade_id = Integer.parseInt(tradeDetails[0]);


            String ticker_identifier = tradeDetails[1];
            if (ticker_identifier.isEmpty()) {
                return false;
            }

            String ticker_symbol = tradeDetails[2];
            if (ticker_symbol.isEmpty()) {
                return false;
            }

            int quantity = Integer.parseInt(tradeDetails[3]);


            double price = Double.parseDouble(tradeDetails[4]);


            Date trade_date = Date.valueOf(tradeDetails[5]);

            return true;
        } catch (Exception e) {

            System.err.println("Validation error: " + e.getMessage());
            return false;
        }
    }


    public void tradesInsertionMaker() throws SQLException {
        if (validLines.isEmpty()) {
            System.out.println("No valid lines to insert into the database.");
            return;
        }

        try (Connection conn = dataSource.getConnection()) {
            conn.setAutoCommit(false);
            String query = "INSERT INTO Trades(trade_id, trade_identifier, ticker_symbol, quantity, price, trade_date) VALUES (?,?,?,?,?,?)";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                for (String line : validLines) {
                    String[] tradeDetails = line.split(",");

                    int trade_id = Integer.parseInt(tradeDetails[0]);
                    String trade_identifier = tradeDetails[1];
                    String ticker_symbol = tradeDetails[2];
                    int quantity = Integer.parseInt(tradeDetails[3]);
                    double price = Double.parseDouble(tradeDetails[4]);
                    Date trade_date = Date.valueOf(tradeDetails[5]);

                    stmt.setInt(1, trade_id);
                    stmt.setString(2, trade_identifier);
                    stmt.setString(3, ticker_symbol);
                    stmt.setInt(4, quantity);
                    stmt.setDouble(5, price);
                    stmt.setDate(6, trade_date);

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


class HitReadFileErrorsThresholdException extends Exception {
    public HitReadFileErrorsThresholdException(String str) {
        super(str);
    }
}


class HitDatabaseInsertErrorsThresholdException extends Exception {
    public HitDatabaseInsertErrorsThresholdException(String str) {
        super(str);
    }
}
































