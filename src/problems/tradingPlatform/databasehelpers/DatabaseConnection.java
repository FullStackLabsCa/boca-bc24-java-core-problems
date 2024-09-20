package problems.tradingPlatform.databasehelpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/bootcamp";
    private static final String USER = "root";
    private static final String PASSWORD = "password123";

    private static final String TESTURL = "jdbc:mysql://localhost:3308/bootcamptest";


     public static Connection getConnection(boolean forTest) throws SQLException{
         if(forTest) {
             return   DriverManager.getConnection(TESTURL, USER, PASSWORD);
         }else{
             return   DriverManager.getConnection(URL, USER, PASSWORD);
         }
    }

}
