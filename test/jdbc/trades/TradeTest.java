package jdbc.trades;

import jdbc.trades.config.DatabaseHelper;
import jdbc.trades.exceptions.InvalidThresholdValueException;
import jdbc.trades.model.TradePOJO;
import jdbc.trades.repo.TradeRepo;
import jdbc.trades.services.TradesService;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.*;
import org.junit.contrib.java.lang.system.SystemOutRule;


import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
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
            TradeRepo repo = new TradeRepo();
            int value = repo.processBatch(tradePOJOList, DatabaseHelper.getConnection(portNumber));
            Assert.assertEquals(1, value);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void setThresholds(int errorThreshold, int threshold, double thresholdPercent) {

        TradesService.setThreshold(errorThreshold);
        TradesService.setThreshold(threshold);
        TradesService.setThresholdPercent(thresholdPercent);
    }

    @Test
    public void validateHitErrorsThresholdException() {
        tradePOJOList = new ArrayList<>();
        TradeRepo repo = new TradeRepo();
        setThresholds(0, 0, 0);
        tradePOJOList.add(new TradePOJO("1", "TDB_219848", "INVALID_TICKER", 330, 178.82, dateArgument("2024-08-13")));
        repo.processBatch(tradePOJOList, DatabaseHelper.getConnection(portNumber));
        assertTrue(systemOutRule.getLog().contains("Exception: jdbc.trades.exceptions.HitErrorsThresholdException: Ticker Symbol doesn't Exist"));
    }


    @Test
    public void validateInvalidThresholdValueException() {
        TradesService tradesService = new TradesService();
        setThresholds(150, 0, 90);
        try {
            tradesService.validateThresholdIsValid();
            Assert.fail("Expected InvalidThresholdValueException");
        } catch (InvalidThresholdValueException e) {
            Assert.assertTrue(e.getMessage().contains("Wrong Value of threshold"));
        }
    }


    @Test
    public void validateFileNotFoundException() {
        TradesService tradesService = new TradesService();
        String invalidPath = "invalid/path/to/file.csv";
        boolean result = tradesService.readCSV(invalidPath);
        Assert.assertFalse(result);
    }

//    @Test
//    public void validateCorrectErrorLogs() throws IOException {
//        TradeRepo repo = new TradeRepo();
//        setThresholds(20, 0, 90);
//        String errorLine = "[ERROR]: Ticker Symbol doesn't Exist [ERROR-LINE#]: 3 [ERROR LINE]: TradePOJO{trade_id='2', trade_identifier='TDB_195199'";
//        tradePOJOList.add(new TradePOJO("1", "TDB_219848", "INVALID_TICKER", 330, 178.82, dateArgument("2024-08-13")));
//        repo.processBatch(tradePOJOList, DatabaseHelper.getConnection(portNumber));
//        List<String> logContents = Files.readAllLines(Paths.get("/Users/Ankit.Joshi/Desktop/Reactive/boca-bc24-java-core-problems/src/JDBC/trades/resources/insertion_error_log.txt"));
//        Assert.assertTrue(errorLine.contains(logContents.get(0).toString()));
//    }


    @Test
    public void validateSummaryOutput() {
        TradeRepo repo = new TradeRepo();
        TradesService tradesService = new TradesService();
        setThresholds(20, 0, 90);
        tradePOJOList.add(new TradePOJO("1", "TDB_219848", "ORCL", 330, 178.82, dateArgument("2024-08-13")));
        TradesService.setCurrentLine(1);
        repo.processBatch(tradePOJOList, DatabaseHelper.getConnection(portNumber));
        tradesService.printSummary();
        String output = systemOutRule.getLog();
        Assert.assertTrue(output.contains("Records processed: 1"));
        Assert.assertTrue(output.contains("Successful inserts: 1"));
        Assert.assertTrue(output.contains("Error count: 0"));
    }

    @Test
    public void validateMixedRecordsBatchProcessing() {
        TradeRepo repo = new TradeRepo();
        setThresholds(20, 0, 90);

        tradePOJOList.add(new TradePOJO("1", "TDB_219848", "VISA", 330, 178.82, dateArgument("2024-08-13")));
        tradePOJOList.add(new TradePOJO("2", "TDB_195199", "INVALID_TICKER", 350, 2735.94, dateArgument("2024-01-06")));

        int value = repo.processBatch(tradePOJOList, DatabaseHelper.getConnection(portNumber));

        Assert.assertEquals(1, value);
        System.out.println(TradesService.getThreshold());
        Assert.assertTrue(TradesService.getThreshold() > 0);
    }

    @Test
//    public void validateUserInput(){
//        String simulatedInput = "case1\n/path/to/file.txt 0.5\n"; // Simulate valid input
//        InputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
//        System.setIn(in);
//        TradesService.userInput();
//    }


    @After
    public void tearDown() {
//        String query = "DELETE FROM TRADES";
//        try (Connection connection = dataSource.getConnection();
//             PreparedStatement statement = connection.prepareStatement(query)) {
//            statement.executeUpdate(); // Use executeUpdate for DELETE
//        } catch (SQLException e) {
//            throw new RuntimeException("Failed to clean up the database: " + e.getMessage(), e);
//        }
    }


}
