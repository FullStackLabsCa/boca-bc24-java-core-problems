package problems.trading;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import problems.trading.exceptions.InvalidInputException;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.LinkedBlockingDeque;

import static org.junit.Assert.assertTrue;

public class TradingTest {
    private Connection connection;
    public static LinkedBlockingDeque<TradeTransaction> tradingTransactionDeQueue = new LinkedBlockingDeque<>(5000);

    @Before()
    public void setup() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bootcamp", "root", "password123");
        connection.setAutoCommit(false);
        //checking and deleting the log files
        TradeService.checkReadLogFileExistOrNot();
        TradeService.checkWriteLogFileExistOrNot();
    }

    @After()
    public void closingResources() throws SQLException {
        connection.setAutoCommit(true);
        connection.close();
    }

    @Test(expected = FileNotFoundException.class)
    public void FileNotExistAndThrowingException() throws FileNotFoundException {
        String fileName = "sample";
        TradeFileReader.checkFileName(fileName);
    }

    @Test
    public void FileFound() throws FileNotFoundException {
        String fileName = "trade_data";
        TradeFileReader.checkFileName(fileName);
        assertTrue(TradeService.isFileExist);
    }

    @Test(expected = InvalidInputException.class)
    public void InvalidThresholdValue() {
        String invalidThreshold = "-1";
        TradeFileReader.checkThresholdValue(invalidThreshold);
    }

    @Test
    public void ValidThresholdValue() {
        String invalidThreshold = "10";
        TradeFileReader.checkThresholdValue(invalidThreshold);
    }
}
