package problems.trade;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import problems.jdbc.trade.*;
import problems.jdbc.trade.Error;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class TradeFileWriterImplTest {


    Connection connection;
    Statement statement;
    TradeFileWriterImpl tradeFileWriterImpl;


    public TradeFileWriterImplTest() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        connection = DataSourceForTesting.getConnection();
        connection.setAutoCommit(false);
        statement = connection.createStatement();

        statement.execute("DROP TABLE IF EXISTS Trades");

        // Create table and insert test data
        String createTableSQL = "CREATE TABLE Trades (" +
                "trade_id VARCHAR(20) PRIMARY KEY,\n" +
                "    trade_identifier VARCHAR(20),\n" +
                "    ticker_symbol VARCHAR(10),\n" +
                "    quantity INT,\n" +
                "    price DECIMAL(15, 2),\n" +
                "    trade_date DATE)";
        statement.execute(createTableSQL);

//        String insertDataSQL = "INSERT INTO Trades (trade_identifier, ticker_symbol, quantity, price, trade_date) VALUES (?, ?, ?, ?, ?)";;
//        statement.execute(insertDataSQL);

        System.out.println("Setup done.");
    }

    @Test
    public void returnNumberOfRowsInsertedInDatabase() throws Exception {
//        TradeFileWriterImpl.errorCounter = 0;
        Error error = new Error(0, null, 1, null, 5, 4);
        tradeFileWriterImpl = new TradeFileWriterImpl(error);
        List<TradeTransaction> tradeTransactionList =new ArrayList<>();
        tradeTransactionList.add(new TradeTransaction("T0019", "DIS", 665, 305.48, new Date(2023 - 10 - 21)));
        tradeTransactionList.add(new TradeTransaction("T0020", "FB", 65, 205.48, new Date(2024 - 11 - 22)));
        tradeTransactionList.add(new TradeTransaction("T0021", "DIS", 665, 105.48, new Date(2023 - 12 - 23)));
        tradeTransactionList.add(new TradeTransaction("T0022", "DIS", 665, 405.48, new Date(2023 - 01 - 24)));
        assertEquals(tradeFileWriterImpl.writeTradeToDatabase(tradeTransactionList),  4);
    }

    @Test
    public void shouldThrowThresholdHitException() throws Exception {
        List<TradeTransaction> tradeTransactionList =new ArrayList<>();
        tradeTransactionList.add(new TradeTransaction("T0019", "DIS", 665, 305.48, new Date(2023 - 10 - 21)));
        tradeTransactionList.add(new TradeTransaction("T0020", "FB", 65, 205.48, new Date(2024 - 11 - 22)));
        tradeTransactionList.add(new TradeTransaction("T0021", "DIS", 665, 105.48, new Date(2023 - 12 - 23)));
        tradeTransactionList.add(new TradeTransaction("T0022", "DIS", 665, 405.48, new Date(2023 - 01 - 24)));
        Error error = new Error(5, null, 1, null, 4, tradeTransactionList.size());
        tradeFileWriterImpl = new TradeFileWriterImpl(error);
        tradeFileWriterImpl.writeTradeToDatabase(tradeTransactionList);
        HitErrorsThresholdException thrown = assertThrows(HitErrorsThresholdException.class, () -> {
            tradeFileWriterImpl.writeTradeToDatabase(tradeTransactionList);
        });

        assertEquals("Threshold Error exception...", thrown.getMessage());
    }


    @After
    public void cleanUp() throws SQLException {
        statement.execute("DROP TABLE IF EXISTS Trades");
        connection.rollback();
        statement.close();
        connection.close();

    }
}
