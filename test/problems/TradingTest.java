package problems;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import problems.jdbc.trading.database.DatabaseConnection;
import problems.jdbc.trading.exception.HitErrorsThresholdException;
import problems.jdbc.trading.model.ErrorChecking;
import problems.jdbc.trading.model.Trade;
import problems.jdbc.trading.repository.TradeRepository;
import problems.jdbc.trading.service.TradeService;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;


public class TradingTest {
    HikariDataSource dataSource;
    Connection connection;
    String pathWithReadErrors;
    String pathWithoutReadErrors;
    Map<Integer, Trade> mapWithValidSecurities = new HashMap<>();
    Map<Integer, Trade> mapWithInvalidSecurities = new HashMap<>();
    ErrorChecking errorChecking;
    TradeService tradeService;
    TradeRepository tradeRepository;

    @Before
    public void setUp() {
        pathWithoutReadErrors = "/Users/Anant.Jain/source/student/boca-bc24-java-core-problems/test/problems" +
                "/tradingsamples/samples_without_read_errors.csv";
        pathWithReadErrors = "/Users/Anant.Jain/source/student/boca-bc24-java-core-problems/test/problems" +
                "/tradingsamples/samples_with_read_errors.csv";

        mapWithValidSecurities.put(1, new Trade("1", "T1", "AAPL", 10, 455.0, LocalDate.parse("2024-08-13")));
        mapWithValidSecurities.put(2, new Trade("2", "T2", "ADBE", 20, 755.0, LocalDate.parse("2024-08-14")));
        mapWithValidSecurities.put(3, new Trade("3", "T3", "AMZN", 30, 855.0, LocalDate.parse("2024-08-15")));
        mapWithValidSecurities.put(4, new Trade("4", "T4", "GOOGL", 40, 955.0, LocalDate.parse("2024-08-16")));
        mapWithValidSecurities.put(5, new Trade("5", "T5", "FB", 5, 45.0, LocalDate.parse("2024-08-17")));

        mapWithInvalidSecurities.put(1, new Trade("1", "T1", "KJHKJ", 10, 455.0, LocalDate.parse("2024-08-13")));
        mapWithInvalidSecurities.put(2, new Trade("2", "T2", "JHBKH", 20, 755.0, LocalDate.parse("2024-08-14")));
        mapWithInvalidSecurities.put(3, new Trade("3", "T3", "KJBKH", 30, 855.0, LocalDate.parse("2024-08-15")));
        mapWithInvalidSecurities.put(4, new Trade("4", "T4", "HGHJB", 40, 955.0, LocalDate.parse("2024-08-16")));
        mapWithInvalidSecurities.put(5, new Trade("5", "T5", "HBJHB", 5, 45.0, LocalDate.parse("2024-08-17")));


        try {
            dataSource = DatabaseConnection.configureHikariCP("3308", "trade_test");
            connection = dataSource.getConnection();
        } catch (Exception e) {
            System.out.println(e);
        }

        errorChecking = new ErrorChecking();
        tradeService = new TradeService();
        tradeRepository = new TradeRepository();

    }

    @After
    public void cleanUp() {
        String sql = "Delete from Trades";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.execute();
            dataSource.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Test
    public void testReadCSVFileWithCorrectPathAndWithoutReadErrors() {
        try {
            errorChecking.setThreshold(15.0);
            Map<Integer, Trade> trades = tradeService.readCSVFile(pathWithoutReadErrors, errorChecking);
            assertFalse(trades.isEmpty());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test(expected = HitErrorsThresholdException.class)
    public void testReadCSVFileWithCorrectPathAndWithReadErrorsAndLowThreshold() {
        try {
            errorChecking.setThreshold(1.0);
            tradeService.readCSVFile(pathWithReadErrors, errorChecking);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Test
    public void testReadCSVFileWithCorrectPathAndWithReadErrorsAndHighThreshold() {
        try {
            errorChecking.setThreshold(30.0);
            Map<Integer, Trade> trades = tradeService.readCSVFile(pathWithReadErrors, errorChecking);
            assertEquals(trades.get(5), new Trade("5", "TDB_210749", "GOOGL", 470, 2361.51,
                    LocalDate.parse("2024-07-22")));
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Test
    public void testFetchThresholdFromApplicationProperties() {
        errorChecking.setThreshold(30.0);
        assertEquals(tradeService.fetchThresholdValue(), 25.0, 0.01);
    }

    @Test(expected = HitErrorsThresholdException.class)
    public void testInsertTradeListWithInvalidSecurities() {
        try {
            errorChecking.setThreshold(30.0);
            tradeRepository.insertTrade(mapWithInvalidSecurities, dataSource, errorChecking);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

//    @Test
//    public void testInsertTradeListWithValidSecurities() {
//        try {
//            errorChecking.setThreshold(30.0);
//            tradeRepository.insertTrade(mapWithValidSecurities, dataSource, errorChecking);
//            Connection conn = dataSource.getConnection();
//
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
//    }

    @Test
    public void testCheckSecuritiesWithNonExistentSecurity() {
        assertFalse(tradeRepository.checkSecurities(connection, "THDP"));
    }

    @Test
    public void testCheckSecuritiesWithExistingSecurity() {
        assertTrue(tradeRepository.checkSecurities(connection, "AMZN"));
    }

    @Test(expected = NullPointerException.class)
    public void testCheckSecuritiesWithNullConnection() {
        tradeRepository.checkSecurities(null, "JHKH");
    }

    @Test
    public void testPrintSummary() {
        tradeService.printSummary(10, 8, 2);
        assertTrue(systemOutRule.getLog().contains("""
                Summary:
                Records processed: 10
                Successful inserts: 8
                Error count: 2"""));
    }

}
