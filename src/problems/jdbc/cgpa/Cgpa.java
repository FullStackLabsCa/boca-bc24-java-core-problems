package problems.jdbc.cgpa;

import problems.jdbc.DatabaseHelper;
import problems.practice.HikariCP;

import java.sql.*;

public class Cgpa {
    public void getCGPA(int studentId) {
        double sum= 0;
        String query = "SELECT SUM(grade)/COUNT(DISTINCT course_id) FROM Grades WHERE (student_id)=?";
        try {
//                Connection conn = HikariCP.getConnection();
            Connection conn = DatabaseHelper.getConnection();
            PreparedStatement stm= conn.prepareStatement(query);
            stm.setInt(1, studentId);
            ResultSet rs= stm.executeQuery();

            while(rs.next()){
                sum= rs.getDouble(1);
            }
            System.out.println("CGPA: " + sum);
        } catch (SQLException e) {
            System.out.println("Error getting CGPA of student." + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
