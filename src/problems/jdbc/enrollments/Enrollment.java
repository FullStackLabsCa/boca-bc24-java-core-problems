package problems.jdbc.enrollments;

import problems.jdbc.DatabaseHelper;

import java.sql.*;

public class Enrollment {
        public void addEnrollment(int course_id, int student_id){
        String query= "INSERT INTO Enrollments (course_id, student_id) "
                + "SELECT course_id, student_id "
                + "FROM Courses, Students "
                + "WHERE course_id = ? AND student_id= ?";
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement stmt= conn.prepareStatement(query)) {
            stmt.setInt(1, course_id);
            stmt.setInt(2, student_id);
            stmt.executeUpdate();
            System.out.println("Student with student id '" + student_id + "' has been enrolled to course id '" + course_id + "'");
        } catch (SQLException e) {
            System.out.println("Error adding enrollment" +  e.getMessage());
        }
    }

    public void removeEnrollment(int enrollmentId){
        String query= "DELETE FROM Enrollments WHERE (enrollment_id) = ?";
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement stmt= conn.prepareStatement(query)) {
            stmt.setInt(1, enrollmentId);
            stmt.executeUpdate();
            System.out.println("Enrollment with enrollment id '" + enrollmentId + "' has been removed");
        } catch (SQLException e) {
            System.out.println("Error removing enrollment" +  e.getMessage());
        }
    }

    public void getEnrollment(){
        String query= "SELECT * FROM Enrollments";
        try {Connection conn = DatabaseHelper.getConnection();
            Statement st= conn.createStatement();
            ResultSet rs= st.executeQuery(query);
            while (rs.next()){
                System.out.print("Enrollment Id:" + rs.getString(1) + " ");
                System.out.print("Course Id:" + rs.getInt(2) + " ");
                System.out.print("Student Id:" + rs.getInt(3) + " ");
                System.out.println();
            }
        }
        catch (SQLException e){
            System.out.println("Error getting all enrollments" +  e.getMessage());
        }
    }
}
