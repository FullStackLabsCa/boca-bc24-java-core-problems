package problems.selfPractice.connectionPooling;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    // Add a new order
    public static void addOrder(int userId, String orderDate, double amount) {
        String sql = "INSERT INTO orders (user_id, order_date, amount) VALUES (?, ?, ?)";

        try (Connection conn = DataSourceFactory.getDataSource().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setString(2, orderDate);
            pstmt.setDouble(3, amount);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // List all orders
    public static List<Order> listOrders() {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders";

        try (Connection conn = DataSourceFactory.getDataSource().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Order order = new Order(
                        rs.getInt("order_id"),
                        rs.getInt("user_id"),
                        rs.getString("order_date"),
                        rs.getDouble("amount")
                );
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }


}
