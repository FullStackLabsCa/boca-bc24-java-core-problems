package problems.tradingplatformtest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import problems.tradingplatform.customexceptions.HitErrorsThresholdException;
import problems.tradingplatform.services.TradingOperation;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class TradingPlatformTest {
    private static final String TEST_FILE = "/Users/Rushikumar.Patel/source/problems/src/problems/tradingplatform/csvfiles/tradetestdata.csv";
    private static final String USER = "root";
    private static final String PASSWORD = "password123";
    private TradingOperation tradingOperation;
    private static final String ERROR_LOG_FILE = "/Users/Rushikumar.Patel/source/problems/error_log_test.txt";
    private static final String TEST_DB_URL = "jdbc:mysql://localhost:3308/bootcamp_test";

    @Before
    public void setup() throws IOException {
//        try(Connection connection = DriverManager.getConnection(TEST_DB_URL,USER,PASSWORD)){
            try (PrintWriter writer = new PrintWriter(new FileWriter(TEST_FILE))) {
            writer.println("1,T123,AAPL,100,150.50,2023-09-01");
            writer.println("2,T124,GOOG,50,2800.75,2023-09-02");
        }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
        tradingOperation = new TradingOperation();
    }

    @After
    public void tearDown() throws IOException, SQLException {
        Files.deleteIfExists(Path.of(TEST_FILE));
        Files.deleteIfExists(Path.of(ERROR_LOG_FILE));

        // Clear test database table
        try (Connection connection = DriverManager.getConnection(TEST_DB_URL, USER, PASSWORD)) {
            String deleteQuery = "DELETE FROM TradesTest";
            try (PreparedStatement statement = connection.prepareStatement(deleteQuery)) {
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Test
    public void testTradeReadingOperation_WithValidRows(){
        assertDoesNotThrow(() -> tradingOperation.tradeReadingOperation(TEST_FILE, 0.25));

        assertEquals(0, TradingOperation.errorCount);
        assertEquals(2, TradingOperation.validRowCount);

    }

    @Test
    public void testTradeReadingOperation_WithInvalidThreshold() {
        String invalidFile = "test_invalid_threshold.csv";

            try (PrintWriter writer = new PrintWriter(new FileWriter(invalidFile))) {
            writer.println("3,T125,MSFT,INVALID_QUANTITY,299.99,2023-09-03");
            writer.println("4,T126,AMZN,INVALID_QUANTITY,INVALID_PRICE,2023-09-04");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        assertThrows(HitErrorsThresholdException.class, () ->
                tradingOperation.tradeReadingOperation(invalidFile, 0.01)
        );
    }

    @Test
    public void testLogError() throws IOException {
        TradingOperation.logError("TestError", "Row 1", "Invalid data format", 1);

        File logFile = new File(ERROR_LOG_FILE);
        assertTrue(logFile.exists());

        try (BufferedReader reader = new BufferedReader(new FileReader(ERROR_LOG_FILE))) {
            String logEntry = reader.readLine();
            assertNotNull(logEntry);
            assertTrue(logEntry.contains("TestError"));
            assertTrue(logEntry.contains("Row 1"));
            assertTrue(logEntry.contains("Invalid data format"));
        }
    }

    @Test
    public void testErrorThresholdExceeded() throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(TEST_FILE))) {
            writer.println("1,T123,AAPL,INVALID_QUANTITY,150.50,2023-09-01");
            writer.println("2,T124,GOOG,INVALID_QUANTITY,INVALID_PRICE,2023-09-02");
        }

        assertThrows(HitErrorsThresholdException.class, () ->
                tradingOperation.tradeReadingOperation(TEST_FILE, 0.5)
        );

        assertTrue(TradingOperation.errorCount > 0);
    }

    @Test
    public void testTradeWritingToDatabase() throws SQLException, HitErrorsThresholdException {
        // Execute the trade reading operation
        tradingOperation.tradeReadingOperation(TEST_FILE, 0.25);

        // Validate the insertion into the database
        try (Connection connection = DriverManager.getConnection(TEST_DB_URL, USER, PASSWORD)) {
            String query = "SELECT COUNT(*) FROM TradesTest";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                ResultSet rs = statement.executeQuery();
                if (rs.next()) {
                    int count = rs.getInt(1);
                    assertEquals(2, count);  // Since we are inserting 2 rows in setup()
                }
            }
        }
    }

}