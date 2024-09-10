package jdbcbootcamp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Library {
    //add books
    public void addBooks(String title, String author){
        String query = "INSERT INTO Books (title, author) VALUES (?,?)";
        try(Connection conn = DatabaseHelper.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, title);
            stmt.setString(2, author);
        } catch (SQLException e) {
            System.out.println("Error adding book: " + e.getMessage());
        }
    }

    public void registerParton(Integer parton_id, String name){

    }
}
