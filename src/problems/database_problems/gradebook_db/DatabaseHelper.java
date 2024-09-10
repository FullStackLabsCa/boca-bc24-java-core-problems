package problems.database_problems.gradebook_db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHelper {
    private static final String URL = "jdbc:mysql://localhost:3306/gradebook";
    private static final String USER = "root";
    private static final String PASSWORD = "password123";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}