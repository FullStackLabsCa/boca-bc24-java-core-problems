package problems.trade;
import com.zaxxer.hikari.HikariDataSource;
import jdbc.trade.TradeRunner;
import jdbc.trade.databaseconnection.MysqlDBConnection;
import jdbc.trade.exceptions.HitErrorsThresholdException;
import jdbc.trade.exceptions.HitInsertErrorsThresholdException;
import jdbc.trade.exceptions.InvalidThresholdValueRuntimeException;
import jdbc.trade.model.TradeData;
import jdbc.trade.service.CSVTradeFileReader;
import jdbc.trade.service.TradeDBWriterImpl;
import jdbc.trade.tradecontract.DatabaseConnection;
import jdbc.trade.tradecontract.TradeDatabaseWriter;
import jdbc.trade.tradecontract.TradeFileReader;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;

public class TradeTest {
    private HikariDataSource dataSource;
    private Connection connection;
    private String csvFilePathWithError;
    private String csvFilePathWithoutError;
    TradeFileReader tradeFileReader;
    TradeDatabaseWriter tradeDatabaseWriter;
    List<TradeData> tradeDataList;

    @Before
    public void setup() {
        DatabaseConnection mysqlDbConnection = new MysqlDBConnection();
        dataSource = mysqlDbConnection.configureHikariCP("jdbc:mysql://localhost:3308/bootcamp_test");

        try {
            connection = dataSource.getConnection();
            String createTradeTableQuery = "CREATE TABLE IF NOT EXISTS Trades (\n" +
                    "    trade_id VARCHAR(20) PRIMARY KEY,\n" +
                    "    trade_identifier VARCHAR(20),\n" +
                    "    ticker_symbol VARCHAR(10),\n" +
                    "    quantity INT,\n" +
                    "    price DECIMAL(15, 2),\n" +
                    "    trade_date DATE\n" +
                    ");";
            PreparedStatement preparedStatement = connection.prepareStatement(createTradeTableQuery);
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        tradeFileReader = new CSVTradeFileReader();
        tradeDatabaseWriter = new TradeDBWriterImpl();
    }

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Test
    public void testGetInvalidFilePathFromUser() {
        String filePath = "/Users/Kiran.Virani/reactivestax/source/boca-bc24-java-core-problems/test/problems/trade/trades_without_error.csv\n" +
        "/Users/Kiran.Virani/reactivestax/source/boca-bc24-java-core-problems/test/problems/trade/samplefile/trades_without_error.csv";
        InputStream inputStream = new ByteArrayInputStream(filePath.getBytes());
        System.setIn(inputStream);
        String result = TradeRunner.getFilePathFromUser();
        assertEquals("/Users/Kiran.Virani/reactivestax/source/boca-bc24-java-core-problems/test/problems/trade/samplefile/trades_without_error.csv", result);
    }

    @Test
    public void testGetValidFilePathFromUser() {
        String validFilePath = "/Users/Kiran.Virani/reactivestax/source/boca-bc24-java-core-problems/test/problems/trade/samplefile/trades_without_error.csv";
        InputStream inputStream = new ByteArrayInputStream(validFilePath.getBytes());
        System.setIn(inputStream);
        String result = TradeRunner.getFilePathFromUser();
        assertEquals(validFilePath, result);
    }

    @Test
    public void testGetInvalidStringThresholdFromUser() {
        String invalidThreshold = "abc\n25.0";
        InputStream inputStream = new ByteArrayInputStream(invalidThreshold.getBytes());
        System.setIn(inputStream);
        double result = TradeRunner.getThresholdValueFromUser();
        assertEquals(25.0, result, 0.01);
    }

    @Test
    public void testGetInvalidNegativeThresholdFromUser() {
        String invalidThreshold = "-25.0\n25.0";
        InputStream inputStream = new ByteArrayInputStream(invalidThreshold.getBytes());
        System.setIn(inputStream);
        double result = TradeRunner.getThresholdValueFromUser();
        assertEquals(25.0, result, 0.01);
    }

    @Test
    public void testGetInValidOutOfRangeThresholdFromUser() {
        String invalidThreshold = "125.0\n25.0";
        InputStream inputStream = new ByteArrayInputStream(invalidThreshold.getBytes());
        System.setIn(inputStream);
        double result = TradeRunner.getThresholdValueFromUser();
        assertEquals(25.0, result, 0.01);
    }

    @Test
    public void testGetValidOutOfRangeThresholdFromUser() {
        String invalidThreshold = "25.0";
        InputStream inputStream = new ByteArrayInputStream(invalidThreshold.getBytes());
        System.setIn(inputStream);
        double result = TradeRunner.getThresholdValueFromUser();
        assertEquals(25.0, result, 0.01);
    }

    @Test(expected = RuntimeException.class)
    public void testGetInValidStringThresholdFromApplicationProperties() {
        System.setProperty("application.properties", "/Users/Kiran.Virani/reactivestax/source/boca-bc24-java-core-problems/test/problems/trade/samplefile/invalid_string_application.properties");
        TradeRunner.getThresholdFromApplicationProperties();
    }

    @Test(expected = InvalidThresholdValueRuntimeException.class)
    public void testGetInValidOutOfRangeThresholdFromApplicationProperties() {
        System.setProperty("application.properties", "/Users/Kiran.Virani/reactivestax/source/boca-bc24-java-core-problems/test/problems/trade/samplefile/invalid_outofrange_application.properties");
        TradeRunner.getThresholdFromApplicationProperties();
    }

    @Test
    public void testGetValidThresholdFromApplicationProperties() {
        System.setProperty("application.properties", "/Users/Kiran.Virani/reactivestax/source/boca-bc24-java-core-problems/test/problems/trade/samplefile/valid_application.properties");
        double result = TradeRunner.getThresholdFromApplicationProperties();
        assertEquals(25.0, result, 0.01);
    }

    @Test
    public void testReadCsvFileWihNoMaxThresholdHits() {
        List<TradeData> tradeDataList = tradeFileReader.readDataFromCsvFile("/Users/Kiran.Virani/reactivestax/source/boca-bc24-java-core-problems/test/problems/trade/samplefile/trades_without_error.csv", 10);
        assertNotNull(tradeDataList);
        assertEquals(2, tradeDataList.size());
    }

    @Test(expected = HitErrorsThresholdException.class)
    public void testReadCsvFileWihMaxThresholdHits() {
        tradeFileReader.readDataFromCsvFile("/Users/Kiran.Virani/reactivestax/source/boca-bc24-java-core-problems/test/problems/trade/samplefile/trades_with_error.csv", 50);
    }

    @Test(expected = HitInsertErrorsThresholdException.class)
    public void testWriteToDbWihMaxThresholdHits() {
        TradeData trade1 = new TradeData("1", "identifier1", "AAPL", 10, 150.00, "2023-09-01", 1);
        TradeData trade2 = new TradeData("2", "identifier2", "INVALID", 5, 200.00, "2023-09-02", 2);
        tradeDataList = Arrays.asList(trade1, trade2);
        tradeDatabaseWriter.writeRecordsToDB(dataSource, tradeDataList, 50);
    }

    @Test(expected = HitInsertErrorsThresholdException.class)
    public void testWriteToDbWihNotMatchingSecurity() {
        TradeData trade1 = new TradeData("1", "identifier1", "AAPL", 10, 150.00, "2023-09-01", 1);
        TradeData trade2 = new TradeData("2", "identifier2", "GOOG", 5, 200.00, "2023-09-02", 2);
        tradeDataList = Arrays.asList(trade1, trade2);
        tradeDatabaseWriter.writeRecordsToDB(dataSource, tradeDataList, 50);
    }

    @Test
    public void testWriteToDbWihNoMaxThresholdHits() {
        TradeData trade1 = new TradeData("1", "identifier1", "AAPL", 10, 150.00, "2023-09-01", 1);
        TradeData trade2 = new TradeData("2", "identifier2", "GOOGL", 5, 200.00, "2023-09-02", 2);
        tradeDataList = Arrays.asList(trade1, trade2);
        tradeDatabaseWriter.writeRecordsToDB(dataSource, tradeDataList, 50);
    }

    @Test
    public void testWriteToDbPrintSummary() {

        TradeDBWriterImpl.insertErrorCount = 2;
        TradeDBWriterImpl.insertSuccessCount = 5;
        TradeDBWriterImpl.duplicateRecords = 1;
        TradeDBWriterImpl.printSummary(8);

        String expectedOutput = "=================Reports of the trade system - writing data in DB==================\n" +
                "Total record in file = 8\n" +
                "Total records processed = 8, Number of errors = 2, Number of successful insert = 5, Number of duplicates = 1\n";

        assertTrue(systemOutRule.getLog().contains(expectedOutput));
    }

    @After
    public void cleanUp() {
        String dbDropQuery = "Delete from Trades";
        try (PreparedStatement preparedStatement = connection.prepareStatement(dbDropQuery)) {
            preparedStatement.executeUpdate();
            dataSource.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
