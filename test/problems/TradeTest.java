package problems;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import problems.tradefileparser.controller.TradeDOAImplementation;
import problems.tradefileparser.model.TradeModel;
import problems.tradefileparser.reader.ThresholdReaderImplementation;
import java.sql.*;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TradeTest {
    private TradeDOAImplementation tradeDOAImplementation;
    private ThresholdReaderImplementation thresholdReaderImplementation;

    final String URL = "jdbc:mysql://localhost:3308/bootcamp";
    final String USER = "root";
    final String PASSWORD = "password123";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    @Before
    public void setUp() throws SQLException {
        tradeDOAImplementation= new TradeDOAImplementation();
        thresholdReaderImplementation= new ThresholdReaderImplementation();

        getConnection();

        createTable();

        insertIntoSecurity();
    }

    @After
    public void cleanUp() throws SQLException {
        dropAllTables();
    }

    public void createTable() throws SQLException {
        String TradeTableQuery = "CREATE TABLE Trades (\n" +
                "    trade_id VARCHAR(20) PRIMARY KEY,\n" +
                "    trade_identifier VARCHAR(20),\n" +
                "    ticker_symbol VARCHAR(10),\n" +
                "    quantity INT,\n" +
                "    price DECIMAL(15, 2),\n" +
                "    trade_date DATE\n" +
                ")";
        String SecurityTableQuery = "CREATE TABLE SecuritiesReference (\n" +
                "    symbol VARCHAR(10) PRIMARY KEY,\n" +
                "    description VARCHAR(100)\n" +
                ")";
        try (Connection connection = getConnection()) {
            PreparedStatement TradeTableStatement = connection.prepareStatement(TradeTableQuery);
            TradeTableStatement.executeUpdate();

            PreparedStatement SecurityTableStatement = connection.prepareStatement(SecurityTableQuery);
            SecurityTableStatement.executeUpdate();
        }
    }

    public void insertIntoSecurity() throws SQLException {
        String securityInsertQuery = "INSERT INTO SecuritiesReference (symbol, description) VALUES " +
                "('AAPL', 'Apple Inc.'), " +
                "('GOOGL', 'Alphabet Inc.'), " +
                "('AMZN', 'Amazon.com Inc.'), " +
                "('MSFT', 'Microsoft Corporation'), " +
                "('TSLA', 'Tesla Inc.'), " +
                "('NFLX', 'Netflix Inc.'), " +
                "('FB', 'Meta Platforms Inc.'), " +
                "('NVDA', 'NVIDIA Corporation'), " +
                "('JPM', 'JP Morgan Chase & Co.'), " +
                "('VISA', 'Visa Inc.'), " +
                "('MA', 'Mastercard Inc.'), " +
                "('BAC', 'Bank of America Corp.'), " +
                "('DIS', 'The Walt Disney Company'), " +
                "('INTC', 'Intel Corporation'), " +
                "('CSCO', 'Cisco Systems Inc.'), " +
                "('ORCL', 'Oracle Corporation'), " +
                "('WMT', 'Walmart Inc.'), " +
                "('T', 'AT&T Inc.'), " +
                "('VZ', 'Verizon Communications Inc.'), " +
                "('ADBE', 'Adobe Inc.'), " +
                "('CRM', 'Salesforce Inc.'), " +
                "('PYPL', 'PayPal Holdings Inc.'), " +
                "('PFE', 'Pfizer Inc.'), " +
                "('XOM', 'Exxon Mobil Corporation'), " +
                "('UNH', 'UnitedHealth Group Inc.');";

        try (
                Connection connection = getConnection()) {
            PreparedStatement InsertSecurityStatement = connection.prepareStatement(securityInsertQuery);
            InsertSecurityStatement.executeUpdate();
        }
    }

    public void dropAllTables() throws SQLException {
        String dropQuery= "DROP TABLE Trades, SecuritiesReference";
        try (Connection connection= getConnection()){
            PreparedStatement dropStatement= connection.prepareStatement(dropQuery);
            dropStatement.executeUpdate();
        }
    }

    @Test
    public void testFilePath() {
        String path= "trades.csv";
        assertEquals("trades.csv", path);
    }

    @Test
    public void testThresholdValue(){
        double thresholdValue= thresholdReaderImplementation.readThreshold();
        assertTrue("Value should be between 1 to 100", thresholdValue>=1 && thresholdValue<=100);
    }

    @Test
    public void testInsertValidTrade() throws SQLException {
        // Prepare a valid trade
        TradeModel validTrade = new TradeModel("T001", "ID001", "AAPL", 100, 150.00, "2024-09-20");

        // Insert the trade
        tradeDOAImplementation.insertTrade(List.of(validTrade));

        // Check if the trade was inserted correctly
        String query = "SELECT COUNT(*) FROM Trades WHERE trade_id = 'T001'";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                System.out.println("Trade Count: " + count); // Debug output
                assertEquals(1, count); // There should be one valid trade
            }
        }
    }

}
