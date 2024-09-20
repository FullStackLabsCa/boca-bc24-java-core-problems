package problems;

import org.junit.Before;
import org.junit.Test;
import problems.tradingPlatform.databasehelpers.DatabaseOperations;
import problems.tradingPlatform.helpers.CommonFunctions;
import problems.tradingPlatform.services.TradeReader;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import static org.junit.Assert.assertNotNull;

public class ReadTradeDataTest {

    private TradeReader tradeReader;
    private static final String CORRUPT_TEST_FILE_PATH = "/Users/Parth.Shah/Documents/Projects/boca-bc24-java-core-problems/src/problems/tradingPlatform/trades_corrupt_1000_.csv";
    private static final String TEST_FILE_PATH = "/Users/Parth.Shah/Documents/Projects/boca-bc24-java-core-problems/src/problems/tradingPlatform/trades_sample_1000.csv";
    private static final int ERROR_PERCENTAGE = 25;
   // private static double totalRows = 0;
    // This method will be called before every test
    @Before
    public void setUp() {
        tradeReader = new TradeReader();
        DatabaseOperations.totalRowsOfFile = CommonFunctions.getTotalRowsCount(new File(TEST_FILE_PATH));
        DatabaseOperations.errorThresholdCount = (double) ( DatabaseOperations.totalRowsOfFile * ERROR_PERCENTAGE) / 100;
    }

    // Test method to check the addition functionality
    @Test
    public void testFileRead() {
        assertNotNull(tradeReader.readTradeData(CORRUPT_TEST_FILE_PATH));
    }
}
