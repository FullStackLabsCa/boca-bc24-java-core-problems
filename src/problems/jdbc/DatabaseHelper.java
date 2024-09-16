package problems.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHelper {


    public static final String URL = "jdbc:mysql://localhost:3306/bootcamp";
    public static final String USER = "root";
    public static final String PASSWORD = "password123";


    public static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(URL, USER, PASSWORD);
}

}
