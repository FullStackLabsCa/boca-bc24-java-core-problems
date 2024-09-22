import com.zaxxer.hikari.HikariDataSource;
import creditcardTransactions.databaseConnection.DatabaseConnectivity;
import org.junit.jupiter.api.Test;
import trading.PresentationLayer.TradingRunner;
import trading.RepositoryLayer.TradingRep;
import trading.Utility.FileNotExists;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class TradingTest {
    private static final String errorFile = "/Users/Manpreet.Kaur/Source/fullstacklabs/student-codebase/boca-bc24-java-core-problems/src/trading/Utility/error_log.txt";
    private static final String csvFile = "trades_sample_1000.csv";

    @Test
    void testConfigureHikariCP() {
        HikariDataSource dataSource = DatabaseConnectivity.configureHikariCP();
        assertEquals("jdbc:mysql://localhost:3306/bootcamp", dataSource.getJdbcUrl());
        assertEquals("root", dataSource.getUsername());
        assertEquals(10, dataSource.getMaximumPoolSize());
        assertEquals(5, dataSource.getMinimumIdle());
        assertEquals(30000, dataSource.getConnectionTimeout());
        assertEquals(600000, dataSource.getIdleTimeout());
        dataSource.close();
    }

    @Test// for non existed path
    void testCase1_FileNotFound() {
        String input = "file.txt\n";
        try (Scanner scanner = new Scanner(input)) {
            FileNotExists exception = assertThrows(FileNotExists.class, () -> {
                TradingRunner.case1(scanner);
            });
            assertEquals("file not found.....", exception.getMessage());
        }
    }

    @Test
    void testValidThresholdValue() {

        String validFilePath = csvFile;
        Scanner scanner = new Scanner(validFilePath + "\n75\n");

        assertDoesNotThrow(() -> {
            TradingRunner.case1(scanner);
        });

        assertEquals(50.0, TradingRunner.thresholdValue);

        scanner.close();
    }

    @Test
    void testErrorLog() throws IOException {
        String errorMessage = "Test error message";
        TradingRep.errorLog(errorMessage);
        List<String> loggedLines = Files.readAllLines(Paths.get(errorFile));
        assertEquals(errorMessage, loggedLines.get(loggedLines.size() - 1));
    }

}
