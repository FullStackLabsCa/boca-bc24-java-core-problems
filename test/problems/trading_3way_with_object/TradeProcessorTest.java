package problems.trading_3way_with_object;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.Before;
import problems.trading_3way_with_object.utility.CsvTradeReader;
import problems.trading_3way_with_object.utility.TradeDatabaseWriter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TradeProcessorTest {

    static HikariDataSource dataSource;
    static HikariConfig config = new HikariConfig();

    public static void configureHikari() {
        config.setJdbcUrl("jdbc:mysql://localhost:3308/trades_withobject_test");
        config.setUsername("root");
        config.setPassword("password123");
        config.setMaximumPoolSize(10);
        config.setConnectionTimeout(30000);
        config.setIdleTimeout(10000);
        dataSource = new HikariDataSource(config);
    }

    public void cleanupDatabase() {
        String useQuery = "use trades_withobject_test;";
        String truncateTableQuery = "truncate Trades;";
        try(Connection conn = dataSource.getConnection();
            PreparedStatement useDataBaseStat = conn.prepareStatement(useQuery);
            PreparedStatement truncateTableStat = conn.prepareStatement(truncateTableQuery)) {
            useDataBaseStat.executeUpdate();
            truncateTableStat.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Before
    public void setup() {
        configureHikari();
        CsvTradeReader.errorReaderMessageList.clear();
        CsvTradeReader.errorReaderCounter = 0;
        CsvTradeReader.successfullyParsedCounter = 0;
        TradeDatabaseWriter.errorWriterMessageList.clear();
    }

    public void teardown() {
        cleanupDatabase();
    }
}
