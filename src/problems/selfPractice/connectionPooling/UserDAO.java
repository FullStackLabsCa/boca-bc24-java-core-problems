package problems.selfPractice.connectionPooling;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    // Add a new user
    public void addUser(String name, String email){

        String sql = "INSERT INTO users (name, email) VALUES (?,?)";

        try (Connection conn = DataSourceFactory.getDataSource().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql) ){
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    // List all users
    public List<User> userLists() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try (Connection conn = DataSourceFactory.getDataSource().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()){
            while (rs.next()) {
                User user = new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email")
                );
                users.add(user);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return users;
    }
}
