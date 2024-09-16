package propogationExamples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Savepoint;
import java.sql.SQLException;

public class NestedTransactionSimulation {
    static Savepoint savepoint = null;


    public static void methodA(Connection connection) throws SQLException {

        try{// Perform some operations
        PreparedStatement stmt1 = connection.prepareStatement("UPDATE account SET balance = balance - 100 WHERE id = 1 and balance > 0");
        stmt1.executeUpdate();

        // Create a savepoint
      //  savepoint = connection.setSavepoint("Savepoint1");

        methodB(connection);

//        savepoint = connection.setSavepoint("Savepoint3");


        stmt1 = connection.prepareStatement("UPDATE accounts SET balance = balance - 100 WHERE id = 1 and balance > 0");
        stmt1.executeUpdate();}
        catch (SQLException e) {
            System.out.println(e.getMessage());
            if(savepoint!=null) {
                connection.rollback(savepoint);
            } else throw new SQLException();
        }
    }

    private static void methodB(Connection connection) throws SQLException {
        try{
            PreparedStatement stmt1;
            // Perform another operation
            stmt1 = connection.prepareStatement("UPDATE accounts SET balance = balance - 100 WHERE id = 1 and balance > 0");
            stmt1.executeUpdate();

            // Create another savepoint
            savepoint = connection.setSavepoint("Savepoint2");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            connection.rollback(savepoint);
        }
    }

    public static void main(String[] args) throws SQLException {
        String jdbcUrl = "jdbc:mysql://localhost:3306/bank";
        String username = "root";
        String password = "newpassword";

        Connection connection = null;

        try {
            // Establish connection
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            connection.setAutoCommit(false); // Start transaction

            methodA(connection);

//            if(savepoint.getSavepointName().equals("Savepoint2")){
//                connection.rollback();
//            }
            // Commit the transaction
            connection.commit();
            
        } catch (SQLException e) {
            if (savepoint != null) {
                connection.rollback(savepoint);
                System.out.println(savepoint.getSavepointName());
            } else {
                connection.rollback();
            }
                System.out.println(e.getMessage());
            }
    }
}
