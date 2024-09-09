package DummySchoolJdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHelper {
    static String URL = "jdbc:mysql://localhost:3306/school_mgt";
    static String USER = "root";
    static String PASSWORD = "newpassword";;

    public static Connection getConnection() throws SQLException {

        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        }catch (Exception e ){
            System.out.println("nothing to connect");
        }
        return  DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
