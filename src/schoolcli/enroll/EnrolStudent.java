package schoolcli.enroll;

import schoolcli.config.DatabaseHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EnrolStudent {

    public void enrolStudentToCourse(String student_name, String course_name){
        String assignStudentToCourse = "INSERT INTO Enrollments (course_id, student_id) "
                + "SELECT course_id, student_id "
                + "FROM Courses, Students "
                + "WHERE course_name = ? AND student_name = ?";
        try(Connection con = DatabaseHelper.getConnection();
            PreparedStatement preparedStatement = con.prepareStatement(assignStudentToCourse)) {
            preparedStatement.setString(1,course_name);
            preparedStatement.setString(2, student_name);
            preparedStatement.executeUpdate();
            System.out.println("Student Assigned to course");
        } catch (SQLException e) {
            System.out.println("Error adding StudentToCourse: " + e.getMessage());
        }
    }

    public void deleteStudentToCourse(String enrolId){
        String query = "DELETE from Enrollments WHERE enrollment_id = (?)";
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, enrolId);
            stmt.executeUpdate();
            System.out.println("Enrollment  '" + enrolId + "' deleted.");
        } catch (SQLException e) {
            System.out.println("Error adding student: " + e.getMessage());
        }
    }
}
