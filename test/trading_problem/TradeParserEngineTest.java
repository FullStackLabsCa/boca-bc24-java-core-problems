package trading_problem;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import trading_parser.repo.TradeParserRepo;
import trading_parser.service.TradeParserEngine;
import trading_parser.utility.HitErrorsThresholdException;
import trading_parser.utility.InvalidThresholdValueException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static trading_parser.repo.TradeParserRepo.getTradeTableSize;
import static trading_parser.service.TradeParserEngine.*;
import static trading_parser.utility.TradeParseUtility.*;

public class TradeParserEngineTest {

    @Before
    public void setup() throws SQLException, IOException {
        configureLogger("test_error_logs.txt");
        configureHikariCP(3308);
        createTable();
        populateTableSecurities();
    }

    @After
    public void cleanUp(){
        dropAllTables();
        fileReadTries = 0; fileReadErrorCount = 0; fileReadTries = 0; fileReadSuccessCount = 0; failedInsertsCount = 0; successfullInsertsCount = 0;
    }

    public void createTable() {
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
                CREATE TABLE SecuritiesReference (                     symbol VARCHAR(10) PRIMARY KEY,                     description VARCHAR(100)                 );
                """;


        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement psTradeTable = conn.prepareStatement(createTradesTable);
            PreparedStatement psSecuritiesTable = conn.prepareStatement(createSecuritiesTable);

            psTradeTable.executeUpdate();
            psSecuritiesTable.executeUpdate();

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void populateTableSecurities() {
        //Populate securites
        String query = """
                insert into SecuritiesReference (symbol) values  ('SYM0'),('SYM1'),('SYM2'),('SYM3'),('SYM4'),('SYM5'),('SYM6'),('SYM7'),('SYM8'),('SYM9');
                """;
        try {
            PreparedStatement ps = dataSource.getConnection().prepareStatement(query);
            ps.executeUpdate();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void dropAllTables(){
        String dropTrade = "drop table Trades";
        String dropSecurities = "drop table SecuritiesReference";

        try (Connection conn = dataSource.getConnection()) {

            PreparedStatement psTrades = conn.prepareStatement(dropTrade);
            PreparedStatement psSecurities = conn.prepareStatement(dropSecurities);

            psTrades.executeUpdate();
            psSecurities.executeUpdate();

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void truncateSecurities(){
        String query = "truncate SecuritiesReference";

        try{
            PreparedStatement ps = dataSource.getConnection().prepareStatement(query);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    //Threshold Tests
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


    //Reading Tests
    @Test (expected = HitErrorsThresholdException.class)
    public void testReadingAllWrongValuesAndExceptionThrown(){
        try {
            TradeParserEngine.readTradesFileAndWriteToDatabase("/Users/Akshat.Singla/Downloads/code/practice-problems/student-codebase/boca-bc24-java-core-problems/test/trading_problem/data/trade_data_all_faulty.csv");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testReadingAllWrongValuesAndLogsChecking(){
        try {
            TradeParserEngine.readTradesFileAndWriteToDatabase("/Users/Akshat.Singla/Downloads/code/practice-problems/student-codebase/boca-bc24-java-core-problems/test/trading_problem/data/trade_data_all_faulty.csv");
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
            TradeParserEngine.readTradesFileAndWriteToDatabase("/Users/Akshat.Singla/Downloads/code/practice-problems/student-codebase/boca-bc24-java-core-problems/test/trading_problem/data/trade_data_all_correct.csv");
            assertEquals(fileReadErrorCount, 0);
            assertEquals(fileReadSuccessCount, 1000);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testReadingValuesWhenFailsEqualToThreshold(){
        try{
            TradeParserEngine.readTradesFileAndWriteToDatabase("/Users/Akshat.Singla/Downloads/code/practice-problems/student-codebase/boca-bc24-java-core-problems/test/trading_problem/data/trade_data_fails_equals_Threshold.csv");
            assertEquals(fileReadErrorCount, 246);
            assertEquals(fileReadSuccessCount, 738);
            assertEquals(successfullInsertsCount, 738);
            assertEquals(failedInsertsCount, 0);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testReadingGoodValuesMoreThanThreshold(){
        try{
            TradeParserEngine.readTradesFileAndWriteToDatabase("/Users/Akshat.Singla/Downloads/code/practice-problems/student-codebase/boca-bc24-java-core-problems/test/trading_problem/data/trade_data_fails_lessthan_Threshold.csv");
            assertEquals(fileReadErrorCount, 246);
            assertEquals(fileReadSuccessCount, 739);
            assertEquals(successfullInsertsCount, 739);
            assertEquals(failedInsertsCount, 0);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    //Writing Tests
    @Test (expected = HitErrorsThresholdException.class)
    public void testWritingAllNonInsertableException(){

        //Truncate the Securities and All will fail to insert
        truncateSecurities();

        try{
            TradeParserEngine.readTradesFileAndWriteToDatabase("/Users/Akshat.Singla/Downloads/code/practice-problems/student-codebase/boca-bc24-java-core-problems/test/trading_problem/data/trade_data_all_correct.csv");
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    @Test
    public void testWritingAllNonInsertableLogging() throws SQLException {

        try{
            //Insert all into database
            TradeParserEngine.readTradesFileAndWriteToDatabase("/Users/Akshat.Singla/Downloads/code/practice-problems/student-codebase/boca-bc24-java-core-problems/test/trading_problem/data/trade_data_all_correct_duplicates.csv");
        } catch (HitErrorsThresholdException e){


            assertEquals(fileReadErrorCount, 0);
            assertEquals(fileReadSuccessCount, 2000);
            assertEquals(successfullInsertsCount, 1000);
            assertEquals(failedInsertsCount, 1000);

            int tradesSize = getTradeTableSize(dataSource.getConnection());
            assertEquals(tradesSize, 0); // Exception Occured
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    @Test
    public void testWritingAllInsertable(){
        try{
            TradeParserEngine.readTradesFileAndWriteToDatabase("/Users/Akshat.Singla/Downloads/code/practice-problems/student-codebase/boca-bc24-java-core-problems/test/trading_problem/data/trade_data_all_correct.csv");
            assertEquals(fileReadErrorCount, 0);
            assertEquals(fileReadSuccessCount, 1000);
            assertEquals(successfullInsertsCount, 1000);
            assertEquals(failedInsertsCount, 0);

            //Get the count of the rows from the table
            int tradesSize = getTradeTableSize(dataSource.getConnection());
            //CheckSuccessFullInserts should be 0
            assertEquals(1000, tradesSize);

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testWritingWhenGoodValuesMoreThanThreshold(){

    }


}
