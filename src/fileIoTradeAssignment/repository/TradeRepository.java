package fileIoTradeAssignment.repository;

import fileIoTradeAssignment.customExceptionClasses.HitDatabaseInsertErrorsThresholdException;
import fileIoTradeAssignment.model.TradePOJO;
import fileIoTradeAssignment.service.TradeService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static fileIoTradeAssignment.service.TradeService.dataSource;

public class TradeRepository implements TradeRepositoryInterface {

    TradeService service = new TradeService();
    private final int BATCH_SIZE = 50;


@Override
    public boolean checkTickerSymbol(String currentTicker) throws SQLException {
        String query = "SELECT COUNT(*) FROM SecuritiesReference WHERE symbol = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, currentTicker);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count > 0; // If count is greater than 0, the ticker exists
                }
            }
        } catch (SQLException e) {
            String errorMessage = "Error checking ticker symbol: " + e.getMessage();
            System.err.println(errorMessage);
            service.logError(errorMessage, "DBInsertion_error_log.txt");
            throw e; // Propagate exception for further handling
        }

        return false; // If no match found
    }


@Override
    public void tradesInsertionMaker() throws SQLException, HitDatabaseInsertErrorsThresholdException {
        if (service.validLines.isEmpty()) {
            System.out.println("No valid trades to insert into the database.");
            return;
        }

        try (Connection conn = dataSource.getConnection()) {
            conn.setAutoCommit(false);
            String query = "INSERT INTO Trades(trade_id, trade_identifier, ticker_symbol, quantity, price, trade_date) VALUES (?,?,?,?,?,?)";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                for (TradePOJO tradePOJO : service.validLines) {

                    // Validate ticker symbol before adding to batch
                    if (!checkTickerSymbol(tradePOJO.getTicker_symbol())) {
                        service.errorCounter++;
                        String errorMessage = "Invalid ticker symbol: " + tradePOJO.getTicker_symbol();
                        System.err.println(errorMessage);
                        service.logError(errorMessage, "DBInsertion_error_log.txt");

                        if (service.errorCounter >= service.DBInsertErrorThreshold) {
                            throw new HitDatabaseInsertErrorsThresholdException("Error threshold limit reached during database insertion.");
                        }
                        continue; // Skip invalid trade
                    }

                    stmt.setInt(1, tradePOJO.getTrade_id());
                    stmt.setString(2, tradePOJO.getTrade_identifier());
                    stmt.setString(3, tradePOJO.getTicker_symbol());
                    stmt.setInt(4, tradePOJO.getQuantity());
                    stmt.setDouble(5, tradePOJO.getPrice());
                    stmt.setDate(6, tradePOJO.getDate());

                    stmt.addBatch(); // Add only valid trades to batch

                    // Execute batch if it reaches the BATCH_SIZE
                    if (stmt.getUpdateCount() % BATCH_SIZE == 0) {
                        stmt.executeBatch();
                        conn.commit();
                    }
                }

                // Execute any remaining batch
                stmt.executeBatch();
                conn.commit();
                System.out.println("Valid trades have been inserted into the database.");

            } catch (SQLException e) {
                conn.rollback();
                service.errorCounter++;
                String errorMessage = "Error during batch insertion: " + e.getMessage();
                System.err.println(errorMessage);
                service.logError(errorMessage, "/Users/akshitabajaj/Documents/reactiveStax/boca-bc24-java-core-problems/src/fileIoTradeAssignment/errorLogs/DBInsertion_error_log.txt");
                throw e;
            }
        }
    }
}
