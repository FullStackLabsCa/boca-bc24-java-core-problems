package jdbc.hikari;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

public class IsolationLevels {
    public static void readCommit() {
        //creating a SQL query
        String addStudentQuery = "INSERT INTO Students (student_id, student_name) VALUES (?,?)";

        try (Connection connection = DatabaseConnectionPool.getConnection()) {
            //setting up the transaction isolation level -TRANSACTION_READ_COMMITTED----
//            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            //setting up the transaction isolation level -TRANSACTION_READ_UNCOMMITTED----
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            //setting up the transaction isolation level -TRANSACTION_REPEATABLE_READ----
//            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);

            //begin the transaction
            connection.setAutoCommit(false);

            PreparedStatement addStudentStatement = connection.prepareStatement(addStudentQuery);

//            addStudentStatement.setInt(1, 1);
//            addStudentStatement.setString(2, "Garry");
//            addStudentStatement.addBatch();
//
//            addStudentStatement.setInt(1, 2);
//            addStudentStatement.setString(2, "Harjinder");
//            addStudentStatement.addBatch();
//
//            addStudentStatement.setInt(1, 3);
//            addStudentStatement.setString(2, "Suraj");
//            addStudentStatement.addBatch();

//            addStudentStatement.setInt(1, 4);
//            addStudentStatement.setString(2, "Parry");
//            addStudentStatement.addBatch();
//
//            addStudentStatement.setInt(1, 5);
//            addStudentStatement.setString(2, "Karan");
//            addStudentStatement.addBatch();

            addStudentStatement.setInt(1, 6);
            addStudentStatement.setString(2, "Gagan");
            addStudentStatement.addBatch();

            //Execute Batch
            int[] batchResult = addStudentStatement.executeBatch();


            //commit the transaction if all goes well
//            try {
//                connection.commit();
//            } catch (SQLException e) {
//                connection.rollback();
//                e.getMessage();
//            }
            System.out.println("batchResult = " + Arrays.toString(batchResult));
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        readCommit();
    }
}
