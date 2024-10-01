package school_jdbc.enrollment;

import dbconnection.DatabaseHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Enrollment {

    Connection conn;
    public Enrollment() throws SQLException{
        this.conn = DatabaseHelper.getConnection();
    }

    public void enrollStudent(String course_name, String student_name) {
        String query = "INSERT INTO Enrollments (course_id,student_id) "
                +"SELECT course_id, student_id "
                +"FROM Courses,Students "
                +"WHERE course_name = ? AND student_name = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, course_name);
            stmt.setString(2,student_name);
            stmt.executeUpdate();
            System.out.println(student_name + " assigned to "+course_name);
        } catch (SQLException e) {
            System.out.println("Error adding Student: " + e.getMessage());
        }
    }

    public void  removeEnrollmentOfStudent(String enrollId) {
        String query = "DELETE FROM Enrollments WHERE enrollment_id =?";
        try(PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setString(1,enrollId);
            stmt.executeUpdate();
            System.out.println("EnrollmentID: "+ enrollId + " removed.");
        }catch(SQLException e){
            System.out.println("Error Removing Enrollment: "+ e.getMessage());
        }
    }

}
