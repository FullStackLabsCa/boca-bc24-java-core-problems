package problems;

import org.junit.Before;
import org.junit.Test;
import problems.tradingPlatform.databasehelpers.DatabaseConnection;
import problems.tradingPlatform.databasehelpers.DatabaseOperations;
import problems.tradingPlatform.exceptions.HitErrorsThresholdException;
import problems.tradingPlatform.helpers.CommonFunctions;
import problems.tradingPlatform.helpers.ErrorManager;
import problems.tradingPlatform.models.Trade;
import problems.tradingPlatform.services.TradeReader;
import problems.tradingPlatform.services.TradeWriter;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class WriteTradeDataTest {

    private TradeWriter tradeWriter;
    private TradeReader tradeReader;
    private static final String CORRUPT_TEST_FILE_PATH = "/Users/Parth.Shah/Documents/Projects/boca-bc24-java-core-problems/src/problems/tradingPlatform/trades_corrupt_1000_.csv";
    private static final int ERROR_PERCENTAGE = 80;
    Connection connection;
    // This method will be called before every test
    @Before
    public void setUp() throws SQLException {
        tradeWriter = new TradeWriter();
        tradeReader = new TradeReader();
        connection = DatabaseConnection.getConnection(true);
        DatabaseOperations.totalRowsOfFile = CommonFunctions.getTotalRowsCount(new File(CORRUPT_TEST_FILE_PATH));
        DatabaseOperations.errorThresholdCount = (double) ( DatabaseOperations.totalRowsOfFile * ERROR_PERCENTAGE) / 100;
    }

    // Test method to check the addition functionality
   @Test
    public void testFileWrite() {
        List<Trade> tradeList =  tradeReader.readTradeData(CORRUPT_TEST_FILE_PATH);
        assertNotEquals(2,tradeWriter.writeTradeData(tradeList,connection,true));
    }

    @Test
    public void testCorruptFileWrite() {
        List<Trade> tradeList =  tradeReader.readTradeData(CORRUPT_TEST_FILE_PATH);
        assertNotEquals(0,ErrorManager.writeErrorCount);
    }


    @Test
    public void testWriteTradeDataHandlesNullInput() {
        assertEquals(0,tradeWriter.writeTradeData(null,connection,true));
    }

}
