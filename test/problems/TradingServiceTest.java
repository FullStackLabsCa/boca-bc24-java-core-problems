package problems;

import org.junit.Before;
import problems.trading.TradeFileWriter;
import problems.trading.TradeService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TradingServiceTest {
    private Connection connection;
    private TradeFileWriter tradeFileWriter;
    private TradeService tradeService;

    @Before()
    public void setup() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bootcamp", "root", "password123");
        connection.setAutoCommit(false);
    }
}
