package trading_problem;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import trading_parser.service.TradeParserEngine;
import trading_parser.utility.HitErrorsThresholdException;
import trading_parser.utility.InvalidThresholdValueException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;
import static trading_parser.service.TradeParserEngine.*;
import static trading_parser.utility.TradeParseUtility.*;

public class TradeParserEngineTest {

    @Before
    public void setup() throws SQLException, IOException {
        configureLogger("test_error_logs.txt");
        configureHikariCP(3308);
        createTable();
        populateTable();
    }

    @After
    public void cleanUp(){
        dropAllTables();
    }

    public void createTable() throws SQLException {
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


        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement psTradeTable = conn.prepareStatement(createTradesTable);

            psTradeTable.executeUpdate();

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void populateTable(){

    }

    public void dropAllTables(){
        String dropTrade = "drop table Trades";

        try (Connection conn = dataSource.getConnection()) {

            PreparedStatement psTrades = conn.prepareStatement(dropTrade);

            psTrades.executeUpdate();

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Test (expected = InvalidThresholdValueException.class)
    public void testNullThreshold(){
        TradeParserEngine.validateThresholdInput(null);
    }

    @Test (expected = InvalidThresholdValueException.class)
    public void testNegativeThreshold(){
        TradeParserEngine.validateThresholdInput("-34.5");
    }

    @Test (expected = InvalidThresholdValueException.class)
    public void testNonNumericThreshold(){
        TradeParserEngine.validateThresholdInput("sdfas");
    }

    @Test (expected = InvalidThresholdValueException.class)
    public void testOverThreshold(){
        TradeParserEngine.validateThresholdInput("111");
    }

    @Test
    public void testCorrectThreshold(){
        assertEquals(34.5, TradeParserEngine.validateThresholdInput("34.5"), 0.001);
        assertEquals(100, TradeParserEngine.validateThresholdInput("100"), 0.001);
        assertEquals(1, TradeParserEngine.validateThresholdInput("1"),0.001);
    }

    @Test (expected = HitErrorsThresholdException.class)
    public void testReadingAllWrongValuesAndExceptionThrown(){
        try {
            TradeParserEngine.readTradesFileAndWriteToDatabase("/Users/Akshat.Singla/Downloads/code/practice-problems/student-codebase/boca-bc24-java-core-problems/test/trading_problem/trade_data_all_faulty.csv");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testReadingAllWrongValuesAndLogsChecking(){
        try {
            TradeParserEngine.readTradesFileAndWriteToDatabase("/Users/Akshat.Singla/Downloads/code/practice-problems/student-codebase/boca-bc24-java-core-problems/test/trading_problem/trade_data_all_faulty.csv");
        } catch (HitErrorsThresholdException e){
            assertEquals(fileReadErrorCount, 1000);
            assertEquals(fileReadSuccessCount, 0);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testReadingAllGoodValues(){
        try {
            TradeParserEngine.readTradesFileAndWriteToDatabase("/Users/Akshat.Singla/Downloads/code/practice-problems/student-codebase/boca-bc24-java-core-problems/test/trading_problem/trade_data_all_correct.csv");
            assertEquals(fileReadErrorCount, 0);
            assertEquals(fileReadSuccessCount, 1000);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testReadingValuesWhenFailsEqualToThreshold(){
        
    }

    @Test
    public void testReadingGoodValuesMoreThanThreshold(){

    }

    @Test
    public void testWritingAllNonInsertable(){

    }

    @Test
    public void testWritingAllInsertable(){

    }

    @Test
    public void testWritingValuesWhenFailedEqualToThreshold(){

    }

    @Test
    public void testWritingWhenGoodValuesMoreThanThreshold(){

    }


}
