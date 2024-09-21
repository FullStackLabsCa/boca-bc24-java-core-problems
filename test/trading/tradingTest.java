package trading;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import trading.DAO.SQlTradeDAO;
import trading.exceptions.HitErrorsThresholdException;
import trading.exceptions.InvalidThresholdValueException;
import trading.model.Trade;
import trading.utility.CSVTradeFileReader;
import trading.utility.UserInteraction;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class tradingTest {

    static HikariConfig hikariConfig = new HikariConfig();
    static HikariDataSource dataSource = new HikariDataSource();

    public static void configureHikariCP() {
        hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3308/");
        hikariConfig.setUsername("root");
        hikariConfig.setPassword("password123");
        hikariConfig.setIdleTimeout(6000000);
        hikariConfig.setMaximumPoolSize(5);
        hikariConfig.setMinimumIdle(2);
        hikariConfig.setConnectionTimeout(300000);

        dataSource = new HikariDataSource(hikariConfig);
    }

    private boolean createDatabase() {
        boolean iscreated = false;
        String createDatabase = "create Database if not exists testTrade";
        try (Connection con = dataSource.getConnection();
             PreparedStatement createDatStat = con.prepareStatement(createDatabase);) {
            createDatStat.executeUpdate();
            iscreated = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return iscreated;
    }

    private void createTable() {
        if (createDatabase()) {
            String useQuery = "use testTrade";
            String createTableQuery = "create table if not exists Trades (" +
                    "trade_id VARCHAR(20) PRIMARY KEY," +
                    "trade_identifier VARCHAR(20)," +
                    "ticker_symbol VARCHAR(10)," +
                    "quantity INT," +
                    "price DECIMAL(15, 2)," +
                    "trade_date DATE )";
            String createSecuritiesReferenceTable = "CREATE TABLE IF NOT EXISTS SecuritiesReference (" +
                    "symbol VARCHAR(10) PRIMARY KEY," +
                    "description VARCHAR(100))";
            try (Connection con = dataSource.getConnection();
                 PreparedStatement useDatStat = con.prepareStatement(useQuery);
                 PreparedStatement createDatStat = con.prepareStatement(createTableQuery);
                 PreparedStatement createSecStat = con.prepareStatement(createSecuritiesReferenceTable);) {
                useDatStat.executeUpdate();
                createDatStat.executeUpdate();
                createSecStat.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    //method to clear up the database
    private void cleanupDatabase() {
        String useQuery = "use testTrade";
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement useDatStat = connection.prepareStatement(useQuery);
                 PreparedStatement delStat = connection.prepareStatement("Delete from Trades")) {
                useDatStat.executeUpdate();
                delStat.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void copyFile() throws IOException {
        Path sourcePath = Paths.get("/Users/Gurpreet.Singh/source/code/student-codebase/boca-bc24-java-core-problems/trade_data.csv");
        Path targetPath = Paths.get("/Users/Gurpreet.Singh/source/code/student-codebase/boca-bc24-java-core-problems/new_trade_data.csv");
        Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
    }

    @Before
    public void setup() throws IOException {
        configureHikariCP();
        createDatabase();
        createTable();
    }

    @After
    public void teardown() {
        cleanupDatabase();
        CSVTradeFileReader.validTradeQue.clear();
        SQlTradeDAO.passsedBusinessCheck=0;
    }

    // To check is file exist
    @Test(expected = FileNotFoundException.class)

    public void testFileNotFoundException() throws FileNotFoundException {
        UserInteraction.fileFromCommandLine("/Users/Gurpreet.Singh/source/code/student-codebase/boca-bc24-java-core-problems/trades_sample_10.csv");
    }

    // To check the parsing is working out
    @Test
    public void testParsingError() {
        // 3 parsing error intentionally added to file
        String filepath = "/Users/Gurpreet.Singh/source/code/student-codebase/boca-bc24-java-core-problems/new_trade_data.csv";
        File file = new File(filepath);
        CSVTradeFileReader csvTradeFileReader = new CSVTradeFileReader();
        UserInteraction.userThreshold = 100.0;
        List<Trade> trades = csvTradeFileReader.tradeFileReader(file);
        int size = trades.size();
        assertEquals(92, size);
    }

    // To check it read the file and have count of all the files
    @Test
    public void testFilereader() {
        String filepath = "/Users/Gurpreet.Singh/source/code/student-codebase/boca-bc24-java-core-problems/trade_data.csv";
        File file = new File(filepath);
        UserInteraction.userThreshold = 100.0;
        int entries = CSVTradeFileReader.countEntries(file);
        assertEquals(95, entries);
    }

    // Checking reader log file exist
    @Test
    public void testIsReaderLogCreated() {
        String Logfilepath = "/Users/Gurpreet.Singh/source/code/student-codebase/boca-bc24-java-core-problems/Reader_error_log.txt";
        File LogFile = new File(Logfilepath);
        assertTrue(LogFile.exists());
    }


    @Test
    public void testIsWriterLogCreated() {
        String InsertFilepath = "/Users/Gurpreet.Singh/source/code/student-codebase/boca-bc24-java-core-problems/Insert_error_log.txt";
        File insertFile = new File(InsertFilepath);
        assertTrue(insertFile.exists());
    }

    @Test
    public void testToCheckRowInsertedIntoDatabase() throws SQLException {
        String filepath = "/Users/Gurpreet.Singh/source/code/student-codebase/boca-bc24-java-core-problems/trade_data.csv";
        File file = new File(filepath);
        CSVTradeFileReader.userThreshold=100;
        CSVTradeFileReader csvTradeFileReader = new CSVTradeFileReader();
        List<Trade> trades = csvTradeFileReader.tradeFileReader(file);
        SQlTradeDAO sQlTradeDAO = new SQlTradeDAO();
        try {
            sQlTradeDAO.con = dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        int entriesIntoDatabase = sQlTradeDAO.tradeFilewriter(trades);
        assertEquals(43, entriesIntoDatabase);
    }


    @Test
    public void testBusinessLogicSuccessCount() {
        String filepath = "/Users/Gurpreet.Singh/source/code/student-codebase/boca-bc24-java-core-problems/trade_data.csv";
        File file = new File(filepath);
        CSVTradeFileReader.userThreshold = 100.0;
        CSVTradeFileReader csvTradeFileReader = new CSVTradeFileReader();
        List<Trade> trades = csvTradeFileReader.tradeFileReader(file);
        SQlTradeDAO sQlTradeDAO = new SQlTradeDAO();
        try {
            sQlTradeDAO.con = dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        sQlTradeDAO.tradeFilewriter(trades);
        int passsedBusinessCheck = SQlTradeDAO.passsedBusinessCheck;
        assertEquals(44, passsedBusinessCheck);
    }

    @Test(expected = InvalidThresholdValueException.class)
    public void testScannerInput() {
        UserInteraction.isValidThreshold("gur");
    }

    @Test(expected = HitErrorsThresholdException.class)
    public void testReaderThreholdException() {
        String filepath = "/Users/Gurpreet.Singh/source/code/student-codebase/boca-bc24-java-core-problems/trade_data _reader_threshold.csv";
        File file = new File(filepath);
        CSVTradeFileReader csvTradeFileReader = new CSVTradeFileReader();
        UserInteraction.userThreshold = 25.0;
        csvTradeFileReader.tradeFileReader(file);
    }

    @Test(expected = HitErrorsThresholdException.class)
    public void testWriterThreholdException() {
        String filepath = "/Users/Gurpreet.Singh/source/code/student-codebase/boca-bc24-java-core-problems/trade_data.csv";
        File file = new File(filepath);
        CSVTradeFileReader csvTradeFileReader = new CSVTradeFileReader();
        UserInteraction.userThreshold = 25.0;
        List<Trade> trades = csvTradeFileReader.tradeFileReader(file);
        SQlTradeDAO sQlTradeDAO = new SQlTradeDAO();
        sQlTradeDAO.tradeFilewriter(trades);
    }
}

