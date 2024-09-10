package problems.generics;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHelper {

    public static Connection getConnection(String dbName) throws SQLException {
        String URL = "jdbc:mysql://localhost:3306/";
        String USER = "root";
        String PASS = "password123";
        return DriverManager.getConnection(URL + dbName, USER, PASS);
    }

}
