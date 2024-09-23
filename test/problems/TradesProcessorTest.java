package problems;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import tradefileprocessing.HitErrorsThresholdException;
import tradefileprocessing.PropertiesLoader;
import tradefileprocessing.TradeFileReader;
import tradefileprocessing.TradesProcessor;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TradesProcessorTest {
    private Connection connection;
    private TradesProcessor tradesProcessor;
private TradeFileReader tradeFileReader;
private String filePath = "/Users/Abhay.Nimavat/IdeaProjects/StudentJavaCore/boca-bc24-java-core-problems/src/tradefileprocessing/trades_sample_1000.csv";

    @Rule
    public final TextFromStandardInputStream inputStream = TextFromStandardInputStream.emptyStandardInputStream();
    @Before
    public void setUp() throws SQLException {
        // Database connection setup on port 3308
        String url = "jdbc:mysql://localhost:3308/bootcamp";
        String user = "root";
        String password = "password123";
        connection = DriverManager.getConnection(url, user, password);


        List<String[]> sampleTrades = Arrays.asList(
                new String[]{"1","T123", "AAPL", "100", "150.0", "2023-09-19"},
                new String[]{"2","T124",  "GOOGL", "200", "2500.0", "2023-09-18"}
        );
         tradeFileReader = new TradeFileReader();
        tradesProcessor = new TradesProcessor(connection, sampleTrades);
    }
    @After public void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            String query = "truncate table trades";
            try(PreparedStatement statement = connection.prepareStatement(query)){
                statement.executeUpdate();
            }
            connection.close();
        }
    }
    @Test
    public void testReadValidFile() throws IOException {
        List<String[]> list = new ArrayList<>();
        list = tradeFileReader.readFile(filePath);
        long fileSize = list.size();
        assertEquals(fileSize,list.size());


    }
@Test
public void testIfValidThresholdGiven() throws SQLException, HitErrorsThresholdException {
    double validThreshold = 5.0;
    PropertiesLoader.putProperty("app.database.threshold", validThreshold);
    tradesProcessor.processTrades();
    String thresholdInProperties = PropertiesLoader.getProperty("app.database.threshold");
    assertEquals(String.valueOf(validThreshold), thresholdInProperties);
    String query = "SELECT COUNT(*) FROM trades";
    try (Statement stmt = connection.createStatement();
         ResultSet rs = stmt.executeQuery(query)) {
        rs.next();
        int rowCount = rs.getInt(1);
        assertEquals(2, rowCount);
    }
}
    @Test
    public void testInsertTradeToDatabase() throws SQLException, HitErrorsThresholdException {
        tradesProcessor.processTrades();
        String query = "SELECT * FROM trades";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            int rowCount = 0;
            while (rs.next()) {
                rowCount++;
            }
            assertEquals(2,rowCount);
        }
    }


}
