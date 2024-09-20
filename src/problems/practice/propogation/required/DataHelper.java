package problems.practice.propogation.required;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataHelper {
    private static final String URL= "jdbc:mysql://localhost:3306/bootcamp";
    private static final String User= "root";
    private static final String Password= "password123";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, User, Password);
    }
}