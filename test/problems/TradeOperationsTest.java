package problems;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import problems.tradeOperations.exceptionFiles.HitErrorsThresholdException;
import problems.tradeOperations.exceptionFiles.InvalidThresholdValueException;
import problems.tradeOperations.exceptionFiles.InvalidThresholdValueRuntimeException;
import problems.tradeOperations.manager.DatabaseConnection;
import problems.tradeOperations.manager.ThresholdManager;
import problems.tradeOperations.tradeFiles.*;

import java.sql.*;

import static org.junit.Assert.*;

public class TradeOperationsTest {

    private DatabaseConnection dbManager;
    private Connection connection;

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    public TradeOperationsTest() throws SQLException {
        // Initialize the DatabaseConnection
        dbManager = new DatabaseConnection("3308", "tradesDB");
        connection = dbManager.getConnection();
    }

    @Before
    public void setUp() {
        try {
            if (dbManager == null) {
                dbManager = new DatabaseConnection("3308", "tradesDB");
                connection = dbManager.getConnection();
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @After
    public void tearDown() {
        DatabaseConnection.closeDataSource();
    }

    @Test
    public void testGetConnectionSuccess() throws SQLException {
        connection = dbManager.getConnection();
        //Assert
        assertNotNull(connection); //Ensure the connection is not null;
        DatabaseConnection.closeDataSource();
    }

    @Test
    public void testValidThresholdFormatCommandLine() throws InvalidThresholdValueException {
        String[] args = {"50.00"};
        ThresholdManager thresholdManager = new ThresholdManager(args);
        double expectedThreshold = 50.00;
        double actualThreshold = thresholdManager.getErrorThreshold();
        assertEquals(expectedThreshold, actualThreshold, 0.001); // assertEquals(expected, actual, delta);
/*
    Why Use a Delta?
Precision Errors: Floating-point numbers can’t always represent decimal values exactly. For example, 0.1 cannot be precisely represented in binary, leading to small inaccuracies. As a result, two values that should be equal might not be exactly the same when compared directly.

Comparison Sensitivity: If you compare two floating-point numbers directly using ==, it may return false even if they are "close enough" in a practical sense.

What is a Delta?
A delta is a small value that defines the acceptable difference between two floating-point numbers for them to be considered equal. When using assertEquals for floating-point comparisons, you provide this delta as the third argument:

*/
    }

    @Test(expected = InvalidThresholdValueException.class)
    public void testInvalidThresholdFormatCommandLine() throws InvalidThresholdValueException {
        String[] args = {"invalid"};
        ThresholdManager thresholdManager = new ThresholdManager(args);
        thresholdManager.getErrorThreshold(); // This should throw the exception
    }

    @Test
    public void testValidThresholdFormatFromProperties() throws InvalidThresholdValueException {
        String[] args = {"50.00"};
        ThresholdManager thresholdManager = new ThresholdManager(args);
        double expectedThreshold = 50.00;
        double actualThreshold = thresholdManager.getErrorThreshold();
        assertEquals(expectedThreshold, actualThreshold, 0.001); // assertEquals(expected, actual, delta);

    }

    @Test(expected = InvalidThresholdValueException.class)
    public void testInvalidThresholdFormatFromProperties() throws InvalidThresholdValueException {
        String[] args = {"999"};
        ThresholdManager thresholdManager = new ThresholdManager(args);
        thresholdManager.getErrorThreshold(); // This should throw the exception
    }

    @Test
    public void testThresholdLowerBoundary() throws InvalidThresholdValueException {
        String[] args = {"1.0"};
        ThresholdManager thresholdManager = new ThresholdManager(args);
        assertEquals(1.0, thresholdManager.getErrorThreshold(), 0.001);
    }

    @Test
    public void testThresholdUpperBoundary() throws InvalidThresholdValueException {
        String[] args = {"100.0"};
        ThresholdManager thresholdManager = new ThresholdManager(args);
        assertEquals(100.0, thresholdManager.getErrorThreshold(), 0.001);
    }

    // *******   Test cases for trade read file    *******

    @Test(expected = HitErrorsThresholdException.class)
    public void testReadFileExceedingErrorThreshold() throws HitErrorsThresholdException {
        // Prepare a trades file with invalid data
        String filePath = "src/problems/tradeOperations/testCasesFiles/test_tradeFileN.csv";

        // This should throw HitErrorsThresholdException
        TradeRWFile.readFileStatic(filePath, 50.0, connection);
        // Assuming more than 50% errors
    }


    @Test
    public void testReadFileEmptyFile() throws HitErrorsThresholdException {
        // an empty trades file
        String filePath = "src/problems/tradeOperations/testCasesFiles/test_tradeFile_empty.csv";

        TradeRWFile.readFileStatic(filePath, 50.0, connection);

        // Check output for summary
        String output = systemOutRule.getLog();
        assertTrue(output.contains("Total rows processed: 0"));
        assertTrue(output.contains("Valid rows inserted: 0"));
        assertTrue(output.contains("Failed rows due to validation errors: 0"));
        assertTrue(output.contains("Failed rows during insertion: 0"));
    }

    @Test
    public void testReadFileValidTrades() throws HitErrorsThresholdException {
        String filePath = "src/problems/tradeOperations/testCasesFiles/test_tradeFile_ValidTrades.csv";

        TradeRWFile.readFileStatic(filePath, 50.0, connection);

        // Check output for summary
        String output = systemOutRule.getLog();
        assertTrue(output.contains("Total rows processed: 3"));
        assertTrue(output.contains("Valid rows inserted: 2"));
        assertTrue(output.contains("Failed rows due to validation errors: 0"));
        assertTrue(output.contains("Failed rows during insertion: 0"));
    }

    @Test
    public void testDatabaseStateAfterValidTradeProcessing() throws HitErrorsThresholdException, SQLException {

        // Query database and assert state
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Trades");

        // Iterate over the ResultSet and print each row's data
        while (rs.next()) {
            // Retrieve data from the current row
            String trade_id = rs.getString("trade_id");  // Example column
            String trade_identifier = rs.getString("trade_identifier");  // Example column
            String ticker_symbol = rs.getString("ticker_symbol");  // Example column
            int quantity = rs.getInt("quantity");  // Example column
            double price = rs.getDouble("price");  // Example column
            Date trade_date = rs.getDate("trade_date");  // Example column

            // Print the retrieved data
            System.out.println("Trade ID: " + trade_id);
            System.out.println("Trade Identifier: " + trade_identifier);
            System.out.println("Ticker Symbol: " + ticker_symbol);
            System.out.println("Quantity: " + quantity);
            System.out.println("Price: " + price);
            System.out.println("Trade Date: " + trade_date);
            System.out.println("----------------------------");

            // Add assertions to validate data if necessary
            assertNotNull(ticker_symbol);  // Ensure symbol is not null
            // Check output for summary
            String output = systemOutRule.getLog();
            assertTrue(output.contains("Trade ID: "));
            assertTrue(output.contains("Trade Identifier: "));
            assertTrue(output.contains("Ticker Symbol: "));
            assertTrue(output.contains("Quantity: "));
            assertTrue(output.contains("Price: "));
            assertTrue(output.contains("Trade Date: "));
        }
    }


    @Test(expected = HitErrorsThresholdException.class)
    public void testReadFileInvalidTrades() throws HitErrorsThresholdException {
        String filePath = "src/problems/tradeOperations/testCasesFiles/test_tradeFile_InvalidTrades.csv";

        TradeRWFile.readFileStatic(filePath, 10.0, connection);

        // Check output for summary
        String output = systemOutRule.getLog();
        assertTrue(output.contains("Total rows processed: 4"));
        assertTrue(output.contains("Valid rows inserted: 0"));
        assertTrue(output.contains("Failed rows due to validation errors: 3"));
        assertTrue(output.contains("Failed rows during insertion: 0"));
    }

    @Test
    public void testReadWriteFileValidTrades() throws HitErrorsThresholdException {
        String filePath = "src/problems/tradeOperations/testCasesFiles/test_trades_readwrite_validle.csv";

        TradeRWFile.readFileStatic(filePath, 25.0, connection);

        // Check output for summary
        String output = systemOutRule.getLog();
        assertTrue(output.contains("Total rows processed: 16"));
        assertTrue(output.contains("Valid rows inserted: 15"));
        assertTrue(output.contains("Failed rows due to validation errors: 0"));
        assertTrue(output.contains("Failed rows during insertion: 5"));
    }


}
