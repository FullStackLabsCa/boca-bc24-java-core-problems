package trading_problem;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import trading_parser.repo.TradeParserRepo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class TradePareseRepoTest {

    private TradeParserRepo tradeParserRepo;
    Connection conn;
    public TradePareseRepoTest() throws SQLException {
    }

    @Before
    public void setup() throws SQLException {
        String URL = "jdbc:mysql://localhost:3308/bootcamp";
        String USER = "root";
        String PASS = "password123";
        conn = DriverManager.getConnection(URL, USER, PASS);

        createTable(conn);
        populateTable(conn);
    }

    @After
    public void cleanUp(){
        dropAllTables(conn);
    }

    public void createTable(Connection conn) throws SQLException {
        String createTradesTable = """
                CREATE TABLE Trades (
                    trade_id VARCHAR(20) PRIMARY KEY,
                    trade_identifier VARCHAR(20),
                    ticker_symbol VARCHAR(10),
                    quantity INT,
                    price DECIMAL(15, 2),
                    trade_date DATE
                );
                """;

        String createSecuritiesTable = """
                CREATE TABLE SecuritiesReference (
                    symbol VARCHAR(10) PRIMARY KEY,
                    description VARCHAR(100)
                );
                """;

        try {
            PreparedStatement psTradeTable = conn.prepareStatement(createTradesTable);
            PreparedStatement psSecuritiesTable = conn.prepareStatement(createSecuritiesTable);

            psTradeTable.executeUpdate();
            psSecuritiesTable.executeUpdate();

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void populateTable(Connection conn){

    }

    public void dropAllTables(Connection conn){
        String dropTrade = "drop table trades";
        String dropSecurities = "drop table SecuritiesReference";

        try{

            PreparedStatement psTrades = conn.prepareStatement(dropTrade);
            PreparedStatement psSecurities = conn.prepareStatement(dropSecurities);

            psTrades.executeUpdate();
            psSecurities.executeUpdate();

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testSize(){
        //Default size should be zero
        assertEquals(0, tradeParserRepo.getTradeTableSize(conn));

        //insert 2
        try {
            String query = "insert into Trades (trade_id, trade_identifier, ticker_symbol, quantity, price) values (1,1,1,1,1),(2,2,2,2,2)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.executeUpdate();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

        //now the size should be 2
        assertEquals(2, tradeParserRepo.getTradeTableSize(conn));
    }


}
