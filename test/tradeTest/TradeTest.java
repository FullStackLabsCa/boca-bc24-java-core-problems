package tradeTest;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import problems.trade.TradeRunner;
import problems.trade.Util.TradeFileReader;
import problems.trade.exceptions.HitErrorsThresholdException;
import problems.trade.exceptions.HitInsertErrorsThresholdException;
import problems.trade.exceptions.InvalidThresholdRuntimeException;
import problems.trade.model.Trade;
import problems.trade.service.TradeProccesorService;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TradeTest {

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    String filepath = "/Users/AnilKumar.Mummadisetti/source/boca-bc24-java-core-problems/testResources/TradeSheet.csv";
    String error_Filepath = "/Users/AnilKumar.Mummadisetti/source/boca-bc24-java-core-problems/testResources/ErrorTradeSheet.csv";

    private TradeProccesorService tradeProcessor;
    private TradeFileReader tradeFileReader;
    HikariDataSource dataSource;

    @BeforeClass
    public static void globalSetUp() throws SQLException, InvalidThresholdRuntimeException {
        DatabaseTestSetup.setUpBeforeClass();
        TradeRunner.loadFromConfigProperties();
    }

    @Before
    public void setup() throws SQLException {
        dataSource = DatabaseTestSetup.dataSource;
    }



    @Test
    public void testReadTradeDataFromCSV() throws HitErrorsThresholdException {
        TradeFileReader tradeFileReader = new TradeFileReader();
        List<Trade> tradeList = tradeFileReader.readTradeDataFromCSV(filepath);
        assertEquals(10,tradeList.size());
    }

    @Test(expected = HitErrorsThresholdException.class)
    public void testReadTradeDataFromCSVHitErrorException() throws HitErrorsThresholdException, InvalidThresholdRuntimeException {
        TradeFileReader tradeFileReader = new TradeFileReader();

        List<Trade> tradeList = tradeFileReader.readTradeDataFromCSV(error_Filepath);
    }


    @Test(expected = HitErrorsThresholdException.class)
    public void testtradeDBWriterHitThresholdErrorException() throws SQLException, HitErrorsThresholdException, HitInsertErrorsThresholdException {

        TradeFileReader tradeFileReader = new TradeFileReader();
        TradeProccesorService tradeProccesorService = new TradeProccesorService();
        List<Trade> tradeList = tradeFileReader.readTradeDataFromCSV(error_Filepath);
        int insertCount = tradeProccesorService.tradeDBWriter(dataSource,tradeList);

        assertEquals(10, insertCount);
    }

    @Test(expected = HitInsertErrorsThresholdException.class)
    public void testtradeDBWriterInsertErrorException() throws SQLException, HitErrorsThresholdException, HitInsertErrorsThresholdException {

        TradeFileReader tradeFileReader = new TradeFileReader();
        TradeProccesorService tradeProccesorService = new TradeProccesorService();
        List<Trade> tradeList = tradeFileReader.readTradeDataFromCSV(filepath);
        int insertCount = tradeProccesorService.tradeDBWriter(dataSource,tradeList);

        assertEquals(10, insertCount);
    }

    @Test
    public void testtradeDBWriter() throws SQLException, HitErrorsThresholdException, HitInsertErrorsThresholdException {

        TradeFileReader tradeFileReader = new TradeFileReader();
        TradeProccesorService tradeProccesorService = new TradeProccesorService();
        List<Trade> tradeList = tradeFileReader.readTradeDataFromCSV(filepath);
        int insertCount = tradeProccesorService.tradeDBWriter(dataSource,tradeList);
        assertEquals(10, insertCount);
    }

    @Test
    public void testtradeReadFileLogOutput() throws  HitErrorsThresholdException {

        TradeFileReader tradeFileReader = new TradeFileReader();
        List<Trade> tradeList = tradeFileReader.readTradeDataFromCSV(filepath);

        assertTrue(systemOutRule.getLog().contains("Trade List size :"+tradeList.size()));

    }




}
