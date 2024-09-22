package problems;

import com.zaxxer.hikari.HikariDataSource;
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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class TradeOperationsTest {

    private Connection connection;


    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Before
    public void setUp() {
        try {
            DatabaseConnection.configureHikariCP("3308", "tradesDB");
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testGetConnectionSuccess() throws SQLException {
        connection = DatabaseConnection.getConnection();
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


    // *******   Test cases for trade read file    *******

    @Test(expected = HitErrorsThresholdException.class)
    public void testReadFileExceedingErrorThreshold() throws HitErrorsThresholdException {
        // Prepare a trades file with invalid data
        String filePath = "src/problems/tradeOperations/testCasesFiles/test_tradeFileN.csv";

        // This should throw HitErrorsThresholdException
        TradeRWFile.readFileStatic(filePath, 50.0);
        // Assuming more than 50% errors
    }

    @Test
    public void testReadFileEmptyFile() throws HitErrorsThresholdException {
        // an empty trades file
        String filePath = "src/problems/tradeOperations/testCasesFiles/test_tradeFile_empty.csv";

        TradeRWFile.readFileStatic(filePath, 50.0);

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

        TradeRWFile.readFileStatic(filePath, 50.0);

        // Check output for summary
        String output = systemOutRule.getLog();
        assertTrue(output.contains("Total rows processed: 3"));
        assertTrue(output.contains("Valid rows inserted: 2"));
        assertTrue(output.contains("Failed rows due to validation errors: 0"));
        assertTrue(output.contains("Failed rows during insertion: 2"));
    }

    @Test(expected = HitErrorsThresholdException.class)
    public void testReadFileInvalidTrades() throws HitErrorsThresholdException {
        String filePath = "src/problems/tradeOperations/testCasesFiles/test_tradeFile_InvalidTrades.csv";

        TradeRWFile.readFileStatic(filePath, 10.0);

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

        TradeRWFile.readFileStatic(filePath, 25.0);

        // Check output for summary
        String output = systemOutRule.getLog();
        assertTrue(output.contains("Total rows processed: 16"));
        assertTrue(output.contains("Valid rows inserted: 15"));
        assertTrue(output.contains("Failed rows due to validation errors: 0"));
        assertTrue(output.contains("Failed rows during insertion: 5"));
    }


}
