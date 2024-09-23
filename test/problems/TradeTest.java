package problems;

import JDBC.trades.config.DatabaseHelper;
import JDBC.trades.exceptions.HitErrorsThresholdException;
import JDBC.trades.exceptions.InvalidThresholdValueException;
import JDBC.trades.main.TradesMain;
import JDBC.trades.model.TradePOJO;
import JDBC.trades.services.TradesService;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.*;
import org.junit.contrib.java.lang.system.SystemOutRule;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.PropertyPermission;
import java.util.PropertyResourceBundle;

import static JDBC.trades.services.TradesService.*;
import static JDBC.trades.repo.TradeRepo.processBatch;
import static JDBC.trades.services.TradesService.printSummary;
import static org.junit.Assert.assertTrue;

public class TradeTest {
    String portNumber = "3308";
    HikariDataSource dataSource;
    List<TradePOJO> tradePOJOList = new ArrayList<>();

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Before
    public void setUp() {
        dataSource = DatabaseHelper.getConnection(portNumber);
    }

    private Date dateArgument(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, formatter);
        return Date.valueOf(localDate);
    }

    @Test
    public void validateValidValuesAddedSuccessfully() {
        setThresholds(90, 0, 90);
        tradePOJOList.add(new TradePOJO("1", "TDB_219848", "PYPL", 330, 178.82, dateArgument("2024-08-13")));
        tradePOJOList.add(new TradePOJO("2", "TDB_195199", "AMZN", 350, 2735.94, dateArgument("2024-01-06")));
        tradePOJOList.add(new TradePOJO("3", "TDB_135287", "MSFT", 287, 717.59, dateArgument("2024-07-19")));
        tradePOJOList.add(new TradePOJO("4", "TDB_227475", "MA", 231, 2052.41, dateArgument("2024-03-03")));
        tradePOJOList.add(new TradePOJO("5", "TDB_210749", "INTC", 470, 2361.51, dateArgument("2024-07-22")));
        tradePOJOList.add(new TradePOJO("6", "TDB_171277", "CSCO", 11, 2488.77, dateArgument("2024-08-15")));
        try {
            int value = processBatch(tradePOJOList, DatabaseHelper.getConnection(portNumber));
            Assert.assertEquals(1, value);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void setThresholds(int errorThreshold, int threshold, int thresholdPercent) {
        TradesService.errorThreshold = errorThreshold;
        TradesService.threshold = threshold;
        TradesService.thresholdPercent = thresholdPercent;
    }

    @Test
    public void validateHitErrorsThresholdException() {
        tradePOJOList = new ArrayList<>();
        setThresholds(0, 0, 0);
        tradePOJOList.add(new TradePOJO("1", "TDB_219848", "INVALID_TICKER", 330, 178.82, dateArgument("2024-08-13")));
        int result = processBatch(tradePOJOList, DatabaseHelper.getConnection(portNumber));
        assertTrue(systemOutRule.getLog().contains("JDBC.trades.exceptions.HitErrorsThresholdException: Ticker Symbol doesn't Exist"));
    }


    @Test
    public void validateInvalidThresholdValueException() {
        setThresholds(150, 0, 90);
        try {
            validateThresholdIsValid();
            Assert.fail("Expected InvalidThresholdValueException");
        } catch (InvalidThresholdValueException e) {
            Assert.assertTrue(e.getMessage().contains("Wrong Value of threshold"));
        }
    }


    @Test
    public void validateFileNotFoundException() {
        String invalidPath = "invalid/path/to/file.csv";
        boolean result = readCSV(invalidPath);
        Assert.assertFalse(result);
    }

    @Test
    public void validateCorrectErrorLogs() throws IOException {
        setThresholds(20, 0, 90);
        String errorLine = "[ERROR]: Ticker Symbol doesn't Exist [ERROR-LINE#]: 1 [ERROR LINE]: TradePOJO{trade_id='1', trade_identifier='TDB_219848', ticker_symbol='INVALID_TICKER', quantity=330, price=178.82, date=2024-08-13} [TIME]: 2024-09-22T20:30:28.872424";
        tradePOJOList.add(new TradePOJO("1", "TDB_219848", "INVALID_TICKER", 330, 178.82, dateArgument("2024-08-13")));
        processBatch(tradePOJOList, DatabaseHelper.getConnection(portNumber));
        List<String> logContents = Files.readAllLines(Paths.get("/Users/Ankit.Joshi/Desktop/Reactive/boca-bc24-java-core-problems/src/JDBC/trades/resources/insertion_error_log.txt"));
        Assert.assertEquals(errorLine, logContents.get(0).toString());
    }


    @Test
    public void validateSummaryOutput() {
        setThresholds(20, 0, 90);
        tradePOJOList.add(new TradePOJO("1", "TDB_219848", "ORCL", 330, 178.82, dateArgument("2024-08-13")));
        TradesService.currentLine = 1;
        int value = processBatch(tradePOJOList, DatabaseHelper.getConnection(portNumber));
        printSummary();
        String output = systemOutRule.getLog();
        Assert.assertTrue(output.contains("Records processed: 1"));
        Assert.assertTrue(output.contains("Successful inserts: 1"));
        Assert.assertTrue(output.contains("Error count: 0"));
    }

    @Test
    public void validateMixedRecordsBatchProcessing() {
        setThresholds(20, 0, 90);

        tradePOJOList.add(new TradePOJO("1", "TDB_219848", "VISA", 330, 178.82, dateArgument("2024-08-13")));
        tradePOJOList.add(new TradePOJO("2", "TDB_195199", "INVALID_TICKER", 350, 2735.94, dateArgument("2024-01-06")));

        int value = processBatch(tradePOJOList, DatabaseHelper.getConnection(portNumber));

        Assert.assertEquals(1, value);
        System.out.println(TradesService.threshold);
        Assert.assertTrue(TradesService.threshold > 0);
    }


    @After
    public void tearDown() {
        String query = "DELETE FROM TRADES";
//        try (Connection connection = dataSource.getConnection();
//             PreparedStatement statement = connection.prepareStatement(query)) {
//            statement.executeUpdate(); // Use executeUpdate for DELETE
//        } catch (SQLException e) {
//            throw new RuntimeException("Failed to clean up the database: " + e.getMessage(), e);
//        }
    }


}
