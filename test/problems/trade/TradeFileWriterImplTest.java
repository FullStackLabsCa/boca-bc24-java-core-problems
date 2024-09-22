package problems.trade;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import problems.jdbc.trade.*;
import problems.jdbc.trade.Error;
import problems.jdbc.trade.exception.HitErrorsReadingThresholdException;
import problems.jdbc.trade.exception.HitErrorsWritingThresholdException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
                "trade_id BIGINT(20) AUTO_INCREMENT PRIMARY KEY,\n" +
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
        List<TradeTransaction> tradeTransactionList = generateTrades();
        Error error = new Error(0, 25, tradeTransactionList.size());
        tradeFileWriterImpl = new TradeFileWriterImpl(error);
        assertEquals(tradeFileWriterImpl.writeTradeToDatabase(tradeTransactionList), 4);
    }

    @Test
    public void validateTradesWithSecuritiesReference() throws Exception {
        List<TradeTransaction> tradeTransactionList = generateTrades();
        Error error = new Error(0, 25, tradeTransactionList.size());
        tradeFileWriterImpl = new TradeFileWriterImpl(error);
        assertEquals(tradeFileWriterImpl.writeTradeToDatabase(tradeTransactionList), 4);
    }

    @Test(expected = HitErrorsWritingThresholdException.class)
    public void shouldThrowHitErrorsWritingThresholdException() throws Exception {
        List<TradeTransaction> tradeTransactionList = generateTrades();
        Error error = new Error(8,  25,  tradeTransactionList.size());
        tradeFileWriterImpl = new TradeFileWriterImpl(error);
        tradeFileWriterImpl.writeTradeToDatabase(tradeTransactionList);
    }

    private List<TradeTransaction> generateTrades() throws ParseException {

        List<TradeTransaction> tradeTransactionList = new ArrayList<>();
        tradeTransactionList.add(
                TradeTransaction.TradeTransactionBuilder.aTradeTransaction()
                        .withTradeIdentifier("T0019")
                        .withTickerSymbol("AAPL")
                        .withQuantity(665)
                        .withPrice(Double.parseDouble(String.valueOf(305.48)))
                        .withTradeDate(new SimpleDateFormat("yyyy-MM-dd").parse("2023-11-13"))
                        .build()
        );


        tradeTransactionList.add(
                TradeTransaction.TradeTransactionBuilder.aTradeTransaction()
                        .withTradeIdentifier("T0020")
                        .withTickerSymbol("GOOGL")
                        .withQuantity(775)
                        .withPrice(Double.parseDouble(String.valueOf(315.48)))
                        .withTradeDate(new SimpleDateFormat("yyyy-MM-dd").parse("2022-10-21"))
                        .build()
        );


        tradeTransactionList.add(
                TradeTransaction.TradeTransactionBuilder.aTradeTransaction()
                        .withTradeIdentifier("T0021")
                        .withTickerSymbol("AMZN")
                        .withQuantity(885)
                        .withPrice(Double.parseDouble(String.valueOf(220.12)))
                        .withTradeDate(new SimpleDateFormat("yyyy-MM-dd").parse("2024-11-02"))
                        .build()
        );


        tradeTransactionList.add(
                TradeTransaction.TradeTransactionBuilder.aTradeTransaction()
                        .withTradeIdentifier("T0022")
                        .withTickerSymbol("VISA")
                        .withQuantity(1200)
                        .withPrice(Double.parseDouble(String.valueOf(330.13)))
                        .withTradeDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-12-05"))
                        .build()
        );

        tradeTransactionList.add(
                TradeTransaction.TradeTransactionBuilder.aTradeTransaction()
                        .withTradeIdentifier("T0023")
                        .withTickerSymbol("BABA")
                        .withQuantity(1200)
                        .withPrice(Double.parseDouble(String.valueOf(330.13)))
                        .withTradeDate(new SimpleDateFormat("yyyy-MM-dd").parse("2021-12-05"))
                        .build()
        );



        return tradeTransactionList;
    }


    @After
    public void cleanUp() throws SQLException {
        statement.execute("DROP TABLE IF EXISTS Trades");
        connection.rollback();
        statement.close();
        connection.close();
    }
}
