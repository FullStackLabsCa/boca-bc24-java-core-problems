package problems.trade;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import problems.jdbc.trade.*;
import problems.jdbc.trade.Error;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    generateTrade() throws ParseException {

        List<TradeTransaction> tradeTransactionList =new ArrayList<>();
        tradeTransactionList.add(new TradeTransaction("T0019", "DIS", 665, 305.48, new Date(2023 - 10 - 21)));



        tradeTransactionList.add(new TradeTransaction("T0020", "FB", 65, 205.48, new Date(2024 - 11 - 22)));
        tradeTransactionList.add(new TradeTransaction("T0021", "DIS", 665, 105.48, new Date(2023 - 12 - 23)));
        tradeTransactionList.add(new TradeTransaction("T0022", "DIS", 665, 405.48, new Date(2023 - 01 - 24)));



tradeTransactionList.add(
                TradeTransaction.TradeTransactionBuilder.aTradeTransaction()
                        .withTradeIdentifier("T0019")
                        .withTickerSymbol("DIS")
                        .withQuantity(665)
                        .withPrice(Double.parseDouble(String.valueOf(305.48)))
                        .withTradeDate(   new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(2023 - 10 - 21)))
                        .build()
);


        tradeTransactionList.add(
                TradeTransaction.TradeTransactionBuilder.aTradeTransaction()
                        .withTradeIdentifier("T0020")
                        .withTickerSymbol("FB")
                        .withQuantity(775)
                        .withPrice(Double.parseDouble(String.valueOf(315.48)))
                        .withTradeDate(   new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(2024 - 10 - 21)))
                        .build()
        );


        tradeTransactionList.add(
                TradeTransaction.TradeTransactionBuilder.aTradeTransaction()
                        .withTradeIdentifier("T0021")
                        .withTickerSymbol("TESLA")
                        .withQuantity(885)
                        .withPrice(Double.parseDouble(String.valueOf(220.12)))
                        .withTradeDate(   new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(2024 - 11 - 02)))
                        .build()
        );


        tradeTransactionList.add(
                TradeTransaction.TradeTransactionBuilder.aTradeTransaction()
                        .withTradeIdentifier("T0021")
                        .withTickerSymbol("TESLA")
                        .withQuantity(885)
                        .withPrice(Double.parseDouble(String.valueOf(220.12)))
                        .withTradeDate(   new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(2024 - 11 - 02)))
                        .build()
        );
    }


    @After
    public void cleanUp() throws SQLException {
        statement.execute("DROP TABLE IF EXISTS Trades");
        connection.rollback();
        statement.close();
        connection.close();

    }
}
