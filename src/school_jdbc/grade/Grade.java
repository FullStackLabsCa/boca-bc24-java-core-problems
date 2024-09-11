package school_jdbc.grade;

import school_jdbc.dbconnection.DatabaseHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Grade {

    Connection conn;
    public Grade() throws SQLException{
        this.conn = DatabaseHelper.getConnection();
    }

    public void assignGradeToStudent(String course_name, String student_name, int grade) {
        String checkEnrollment = "SELECT 1 FROM Enrollments " +
                "WHERE course_id = (SELECT course_id FROM Courses WHERE course_name = ?) " +
                "AND student_id = (SELECT student_id FROM Students WHERE student_name = ?)";

        String assignGrade = "INSERT INTO Grades (course_id, student_id, school_jdbc.grade) " +
                "SELECT " +
                "(SELECT course_id FROM Courses WHERE course_name = ?), " +
                "(SELECT student_id FROM Students WHERE student_name = ?), " +
                "? " +
                "ON DUPLICATE KEY UPDATE school_jdbc.grade = VALUES(school_jdbc.grade)";

        try{
            try (PreparedStatement checkEnrollmentStmt = conn.prepareStatement(checkEnrollment)) {
                checkEnrollmentStmt.setString(1, course_name);
                checkEnrollmentStmt.setString(2, student_name);

                try (ResultSet rs = checkEnrollmentStmt.executeQuery()) {
                    if (rs.next()) {
                        try (PreparedStatement assignGradeStmt = conn.prepareStatement(assignGrade)) {
                            assignGradeStmt.setString(1, course_name);
                            assignGradeStmt.setString(2, student_name);
                            assignGradeStmt.setInt(3, grade);

                            assignGradeStmt.executeUpdate();
                            System.out.println(student_name + "'s school_jdbc.grade in " + course_name + " => " + grade);
                        }
                    } else {
                        System.out.println(student_name + " is not enrolled in " + course_name);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error assigning school_jdbc.grade to Student: " + e.getMessage());
        }
    }


    public void  removeGrade(String gradeId) {
        String query = "DELETE FROM Grades WHERE grade_id =?";
        try(PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setString(1,gradeId);
            stmt.executeUpdate();
            System.out.println("GradeID: "+ gradeId + " removed.");
        }catch(SQLException e){
            System.out.println("Error Removing Grade: "+ e.getMessage());
        }
    }

}
