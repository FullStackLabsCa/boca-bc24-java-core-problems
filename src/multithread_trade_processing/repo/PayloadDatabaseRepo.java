package multithread_trade_processing.repo;

import java.sql.*;

public class PayloadDatabaseRepo {
    String URL = "jdbc:mysql://localhost:3306/bootcamp";
    String USER = "root";
    String PASS = "password123";

    public void writeToDatabase(String tradeID, String status, String payload){
        String query = "Insert into trades_payload (trade_id, status, payload) values (?,?,?)";

        try(Connection connection = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement psQuery = connection.prepareStatement(query)){

            psQuery.setString(1,tradeID);
            psQuery.setString(2,status);
            psQuery.setString(3,payload);
            psQuery.executeUpdate();

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public String readPayloadFromDB(String tradeID){
        String query = "Select payload from trades_payload where trade_id=?";

        try(Connection connection = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement psQuery = connection.prepareStatement(query)){

            psQuery.setString(1, tradeID);
            ResultSet rsQuery = psQuery.executeQuery();

            return rsQuery.getString("payload");

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return null;
    }
}
