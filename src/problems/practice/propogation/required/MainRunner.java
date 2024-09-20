package problems.practice.propogation.required;

import java.sql.Connection;
import java.sql.SQLException;

public class MainRunner {

    public static void main(String[] args) throws SQLException {

        Connection connectionA = null;
        Connection connectionB = null;
        try {
            connectionA = DataHelper.getConnection();
            connectionB = DataHelper.getConnection();
            MethodA methodA = new MethodA(connectionA);
            MethodB methodB = new MethodB(connectionB);

            try {
                connectionA.setAutoCommit(false);
                methodA.performATransaction();
                System.out.println("A is committed");
            } catch (SQLException e) {
                connectionA.rollback();
                System.out.println(e.getMessage());
            } finally {
                connectionA.close();
            }

            try {
                connectionB.setAutoCommit(false);
                methodB.performBTransaction();
                connectionB.commit();
                System.out.println("B is committed");
            } catch (Exception e) {
                connectionB.rollback();
                throw new RuntimeException(e);
            } finally {
                connectionA.close();
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
