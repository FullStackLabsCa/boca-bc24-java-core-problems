package problems.practice.propogation.required;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MethodB {
private Connection conn;

    public MethodB(Connection conn) throws SQLException {
        this.conn =conn;
    }

    public  void performBTransaction(){
        String query= "INSERT INTO accounts (name, balance) VALUES (?, ?)";
        try(PreparedStatement preparedStatement = conn.prepareStatement(query)){
            preparedStatement.setString(1, "Desai");
//            preparedStatement.setDouble(2, 10000.00);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
