package problems;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import trading.TradeRunner;
import trading.service.Reader.TradeReaderService;
import trading.service.TradingDBOperationsService;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class TradingTest {
    private TradingDBOperationsService tradeDBOperations;
    private TradeReaderService tradeReaderService;

    final String URL = "jdbc:mysql://localhost:3308/test";
    final String USER = "root";
    final String PASSWORD = "password123";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Before
    public void setUp() throws SQLException {
        tradeDBOperations= new TradingDBOperationsService();
        tradeReaderService= new TradeReaderService();

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
    public void testReadFile() throws FileNotFoundException {
        String filePath = "xyz";
        try {
            tradeReaderService.readFile("/Users/Mann.Bhatt/source/problems/mabh/boca-bc24-java-core-problems/trades.csv");
        } catch (FileNotFoundException e) {
            fail("File not found at path: " + filePath);
        }
    }

    @Test
    public void testFilterFile() throws Exception{
        List<String> stringList = new ArrayList<>();
        stringList.add("1,TDB_219848,PYPL,330,178.82,2024-08-13");
        try{
            tradeReaderService.filterFile(stringList);
        }catch (ParseException e){
            fail("error while filtering file");
        }
    }
}
