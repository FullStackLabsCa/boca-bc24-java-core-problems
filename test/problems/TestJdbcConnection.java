package problems;

import org.junit.Before;
import org.junit.Test;
import problems.tradingPlatform.databasehelpers.DatabaseConnection;
import problems.tradingPlatform.databasehelpers.DatabaseOperations;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TestJdbcConnection {

    private static final String TEST_FILE_PATH = "/Users/Parth.Shah/Documents/Projects/boca-bc24-java-core-problems/src/problems/tradingPlatform/trades_sample_1000.csv";
    private static final String CORRUPT_TEST_FILE_PATH = "/Users/Parth.Shah/Documents/Projects/boca-bc24-java-core-problems/src/problems/tradingPlatform/trades_corrupt_1000_.csv";

    // Test method to check the addition functionality
    @Test
    public void testConnection() throws SQLException {
        Connection conn = DatabaseConnection.getConnection(true);
        assertNotNull(conn);
        assertTrue(conn.isValid(0));
        conn.close();
    }

    @Test
    public void testFileExistence() {
        assertTrue("File DOES NOT Exists", Files.exists(Paths.get(CORRUPT_TEST_FILE_PATH)));
    }

}
