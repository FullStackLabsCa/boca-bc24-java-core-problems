package tradeTest;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.Before;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.*;

public class DatabaseTestSetup {

    public static HikariDataSource dataSource;
    private Connection connection;

    @BeforeClass
    public static void setUpBeforeClass() throws SQLException {
        // Initialize HikariCP configuration
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3308/bootcamp");
        config.setUsername("root");
        config.setPassword("password123");

        // Create HikariCP DataSource
        dataSource = new HikariDataSource(config);

        // Set up the schema (schema.sql should be placed in src/test/resources)
       /* try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute("RUNSCRIPT FROM 'classpath:schema.sql'");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Failed to initialize the database schema.", e);
        }*/
    }

    @Before
    public void setUp() throws SQLException {
        // Get a new connection for each test
        connection = dataSource.getConnection();
    }

    @Test
    public void testConnection() throws SQLException {
        assertNotNull("Connection should not be null", connection);
        try (Statement stmt = connection.createStatement()) {
            // Example test: Check if a table exists (assuming a "trades" table)
            stmt.execute("SELECT 1 FROM trades");
        } catch (SQLException e) {
            fail("Database setup failed: " + e.getMessage());
        }
    }

    @After
    public void tearDown() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    @AfterClass
    public static void tearDownAfterClass() {
        if (dataSource != null) {
            dataSource.close();
        }
    }
}
