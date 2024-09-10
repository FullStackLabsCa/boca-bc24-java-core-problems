package problems.generics.databaseCourse;

import java.sql.*;

public class DatabaseHelper {

    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String USER = "root";
    private static final String PASSWORD = "password123";
    private static final String DATABASE_NAME = "course";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL + DATABASE_NAME, USER, PASSWORD);
    }

    public static void initializeDatabase() throws SQLException{
        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)){
            if(!doesDatabaseExist(connection, DATABASE_NAME)){
                createDatabase(connection);
                System.out.println("Database '"+ DATABASE_NAME + "' created successfully.");
            }else {
                System.out.println("Database already exists.");
            }
        }
    }


    public static boolean doesDatabaseExist(Connection connection, String dbName) throws SQLException{
        DatabaseMetaData databaseMetaData = connection.getMetaData();
        try(var resultSet = databaseMetaData.getCatalogs()){
            while (resultSet.next()){
                if(resultSet.getString(1).equals(dbName)){
                    return true;
                }
            }
        }
        return false;
    }

    private static void createDatabase(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute("CREATE DATABASE " + DATABASE_NAME);
            createTables(connection);
        }
    }

    private static void createTables(Connection connection) throws SQLException {
        connection.setCatalog(DATABASE_NAME); // Switch to the new database
        try (Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE students (" +
                    "name VARCHAR(255) PRIMARY KEY, " +
                    "grade INT)");
        }
    }


}
