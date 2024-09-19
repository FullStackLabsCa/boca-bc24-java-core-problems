package problems;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.Before;
import org.junit.Test;
import problems.jdbc.trading.database.DatabaseConnection;
import problems.jdbc.trading.exception.HitErrorsThresholdException;
import problems.jdbc.trading.model.Trade;
import problems.jdbc.trading.service.TradeService;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;


public class TradingTest {
    HikariDataSource dataSource;
    Connection connection;
    String pathWithReadErrors;
    String pathWithoutReadErrors;

    @Before
    public void setUp() {
        pathWithoutReadErrors = "/Users/Anant.Jain/source/student/boca-bc24-java-core\" +\n" +
                "                    \"-problems/test/problems/tradingsamples/samples_without_read_errors.csv";
        pathWithReadErrors = "/Users/Anant.Jain/source/student/boca-bc24-java-core-problems/test/problems" +
                "/tradingsamples/samples_with_read_errors.csv";
        try {
            dataSource = DatabaseConnection.configureHikariCP("3308", "trade_test");
            connection = dataSource.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testReadCSVFileWithCorrectPathAndHighThreshold() {
        try {
            List<Trade> trades = TradeService.readCSVFile(pathWithoutReadErrors, 0, 0, 45);
            assertFalse(trades.isEmpty());
        } catch (Exception e) {
            System.out.println();
        }
    }

    @Test(expected = HitErrorsThresholdException.class)
    public void testReadCSVFileWithCorrectPathAndLowThreshold() {
        try {
            TradeService.readCSVFile(pathWithReadErrors, 0, 0, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    @Test
//    public void testReadCSVFileWithCorrectPathAndHighThreshold() {
//        try {
//            List<Trade> trades = TradeService.readCSVFile("/Users/Anant.Jain/source/student/boca-bc24-java-core" +
//                    "-problems/test/problems/tradingsamples/samples_without_read_errors.csv", 0, 0, 45);
//            assertFalse(trades.isEmpty());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
