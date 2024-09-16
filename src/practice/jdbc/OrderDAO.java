//package practice.jdbc;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//import javax.sql.DataSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public class OrderDAO {
//
//    @Autowired
//    private DataSource dataSource;  // HikariCP provides the DataSource
//
//    // Method to place a new order
//    public void placeOrder(int userId, int productId, int quantity) throws SQLException {
//        String query = "INSERT INTO orders (user_id, product_id, quantity) VALUES (?, ?, ?)";
//
//        // Try-with-resources to ensure the connection is closed properly
//        try (Connection connection = dataSource.getConnection();
//             PreparedStatement stmt = connection.prepareStatement(query)) {
//
//            // Set parameters and execute
//            stmt.setInt(1, userId);
//            stmt.setInt(2, productId);
//            stmt.setInt(3, quantity);
//            stmt.executeUpdate();
//        }
//        // No need to close the connection manually – it is returned to the pool automatically
//    }
//
//    // Method to fetch order history for a user
//    public List<Order> getOrderHistory(int userId) throws SQLException {
//        List<Order> orders = new ArrayList<>();
//        String query = "SELECT * FROM orders WHERE user_id = ?";
//
//        // Try-with-resources ensures the connection and statement are properly closed
//        try (Connection connection = dataSource.getConnection();
//             PreparedStatement stmt = connection.prepareStatement(query)) {
//
//            // Set parameters and execute
//            stmt.setInt(1, userId);
//            try (ResultSet rs = stmt.executeQuery()) {
//                while (rs.next()) {
//                    Order order = new Order();
//                    order.setOrderId(rs.getInt("order_id"));
//                    order.setProductId(rs.getInt("product_id"));
//                    order.setQuantity(rs.getInt("quantity"));
//                    orders.add(order);
//                }
//            }
//        }
//        return orders;
//    }
//}