package problems;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import problems.tradefileparser.controller.TradeDOAImplementation;
import problems.tradefileparser.model.TradeModel;
import problems.tradefileparser.reader.ThresholdReaderImplementation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TradeTest {

    private List<TradeModel> listModel;
    private Connection connection;
    private int count = 0;

    @Before
    public void setUp() throws SQLException {
        final String URL = "jdbc:mysql://localhost:3308/bootcamp";
        final String USER = "root";
        final String PASSWORD = "password123";
        connection = DriverManager.getConnection(URL, USER, PASSWORD);

        listModel = new ArrayList<>();
    }

    @After
    public void cleanUp() {
        dropAllTables();
    }

    public void dropAllTables() {
        String dropQuery = "TRUNCATE TABLE Trades";
        try {
            PreparedStatement dropStatement = connection.prepareStatement(dropQuery);
            dropStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testFilePath() {
        String path = "trades.csv";
        assertEquals("trades.csv", path);
    }

    @Test
    public void testThresholdValue() {
        ThresholdReaderImplementation thresholdReaderImplementation = new ThresholdReaderImplementation();

        double thresholdValue = thresholdReaderImplementation.readThreshold();
        assertTrue("Value should be between 1 to 100", thresholdValue >= 1 && thresholdValue <= 100);
    }

    @Test
    public void testInsertValidTrade() throws SQLException {
        TradeModel validTrade1 = new TradeModel("T001", "ID001", "AAPL", 100, 150.00, "2024-09-20");
        TradeModel validTrade2 = new TradeModel("T002", "ID002", "AAPL", 100, 150.00, "2024-09-20");
        listModel.add(validTrade1);
        listModel.add(validTrade2);
        TradeDOAImplementation tradeDOAImplementation = new TradeDOAImplementation(listModel, connection);
        tradeDOAImplementation.insertTrade();
        String query = "SELECT * FROM Trades";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                count++;
            }
        }
        assertEquals(2, count);
    }

}
