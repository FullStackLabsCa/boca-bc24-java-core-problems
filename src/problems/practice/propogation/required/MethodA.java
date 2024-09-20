package problems.practice.propogation.required;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MethodA {

  private Connection conn;
    public MethodA(Connection conn) throws SQLException {
       this.conn = conn;
    }

    public void performATransaction(){
        String query= "INSERT INTO accounts (name, balance) VALUES (?, ?)";
        try(PreparedStatement preparedStatement = conn.prepareStatement(query)){
            preparedStatement.setString(1, "Dhruv");
            preparedStatement.setDouble(2, 5000.00);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
