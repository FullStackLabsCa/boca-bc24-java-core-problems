import chunkproblem.DatabaseConnectivity.DatabaseConnection;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Test;
import trading.presentation.TradingRunner;
import trading.utility.FileNotExists;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class TradingTest {
    private static final String errorFile = "/Users/Manpreet.Kaur/Source/fullstacklabs/student-codebase/boca-bc24-java-core-problems/src/trading/Utility/error_log.txt";

    @Test
    void testConfigureHikariCP() {
        HikariDataSource dataSource = DatabaseConnection.configureHikariCP();
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
        String input = "non_existent_file.txt\n";
        try (Scanner scanner = new Scanner(input)) {
            assertThrows(FileNotExists.class, () -> {
                TradingRunner.case1(scanner, new File(input.trim()));
            });
        }
    }


    @Test
    void testValidThresholdValue() {
        String validFilePath = "path/to/your/valid.csv"; // Make sure this file exists in your test environment
        String input = validFilePath + "\n75\n"; // Simulating user input for file path and threshold value

        try (Scanner scanner = new Scanner(input)) {
            // Call case1 with the scanner and the file
            TradingRunner.case1(scanner, new File(validFilePath));

            // Assert that the THRESHOLD_VALUE was set correctly
            assertEquals(70.0, TradingRunner.THRESHOLD_VALUE, 0.013); // Using a delta for floating-point comparison
        } catch (Exception e) {
            fail("Should not throw any exception: " + e.getMessage());
        }
    }


    @Test
    void testErrorLog() throws IOException {
        String errorMessage = "Test error message";
     //   TradingRep.errorLog(errorMessage, );
        List<String> loggedLines = Files.readAllLines(Paths.get(errorFile));
        assertEquals(errorMessage, loggedLines.get(loggedLines.size() - 1));
    }

}
