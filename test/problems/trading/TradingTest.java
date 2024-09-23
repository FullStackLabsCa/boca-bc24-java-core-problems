package problems.trading;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import problems.trading.exceptions.HitErrorsThresholdException;
import problems.trading.exceptions.InvalidInputException;

import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TradingTest {
    private Connection connection;
    public static ArrayList<TradeTransaction> tradingTransactionArrayList = new ArrayList<>(5000);

    @Before()
    public void setup() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3308/bootcamp", "root", "password123");
        connection.setAutoCommit(false);
        TradeService.tradingTransactionArrayList.clear();
        tradingTransactionArrayList.clear();
        //checking and deleting the log files
        TradeService.checkReadLogFileExistOrNot();
        TradeService.checkWriteLogFileExistOrNot();
    }

    @After()
    public void closingResources() throws SQLException {
        TradeService.errorCount = 0;
        TradeService.errorThreshold = 0;
        String truncateQuery = "TRUNCATE TABLE Trades";
        PreparedStatement preparedStatement = connection.prepareStatement(truncateQuery);
        preparedStatement.executeUpdate();
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

    @Test
    public void fetchSecuritiesReferenceTable() throws SQLException {
        String querySecuritiesReference = "SELECT * FROM SecuritiesReference";
        PreparedStatement preparedStatement = connection.prepareStatement(querySecuritiesReference);
        ResultSet resultSet = preparedStatement.executeQuery();
        int count = 0;
        while (resultSet.next()) {
            count++;
        }

        assertEquals(25, count);
    }

    @Test
    public void readTransactionFileAndWriteToList() {
        TradeService.errorThreshold = 20;
        String filePath = "/Users/Gaurav.Manchanda/src/boca-bc24-java-core-problems/test_trade_data.csv";
        tradingTransactionArrayList = TradeFileReader.readTransactionFileAndWriteToList(filePath);
        assertEquals(9, tradingTransactionArrayList.size());
    }

    @Test(expected = HitErrorsThresholdException.class)
    public void readTransactionHitErrorsThresholdException() {
        TradeService.errorThreshold = 0;
        String filePath = "/Users/Gaurav.Manchanda/src/boca-bc24-java-core-problems/test_trade_data.csv";
        tradingTransactionArrayList = TradeFileReader.readTransactionFileAndWriteToList(filePath);
    }

    @Test
    public void writeTradeTransactionToDB() throws Exception {
        TradeService.errorThreshold = 40;
        String filePath = "/Users/Gaurav.Manchanda/src/boca-bc24-java-core-problems/test_trade_data.csv";
        tradingTransactionArrayList = TradeFileReader.readTransactionFileAndWriteToList(filePath);
        TradeFileWriter.insertQuery(tradingTransactionArrayList, connection);
        assertEquals(7, (tradingTransactionArrayList.size() - TradeService.errorCount + 1));
    }

    @Test(expected = HitErrorsThresholdException.class)
    public void writeTransactionHitErrorsThresholdException() throws Exception {
        TradeService.errorThreshold = 0;
        String filePath = "/Users/Gaurav.Manchanda/src/boca-bc24-java-core-problems/test_trade_data.csv";
        tradingTransactionArrayList = TradeFileReader.readTransactionFileAndWriteToList(filePath);
        TradeFileWriter.insertQuery(tradingTransactionArrayList, connection);
    }
}
