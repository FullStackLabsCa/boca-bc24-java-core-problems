package problems.jdbc.grades;

import problems.jdbc.DatabaseHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Grades {
    public void addGrade(int courseId, int studentId, double grade){
        String query= "SELECT 1 FROM Enrollments WHERE course_id= (?) AND student_id= (?)";
        String gradeQuery= "INSERT INTO Grades (course_id,student_id,grade) VALUES (?, ?, ?)";
        try{
            Connection conn= DatabaseHelper.getConnection();
            PreparedStatement st= conn.prepareStatement(query);
            st.setInt(1, courseId);
            st.setInt(2, studentId);
            ResultSet rs= st.executeQuery();
            if (rs.next()){
                PreparedStatement sta = conn.prepareStatement(gradeQuery);
                sta.setInt(1, courseId);
                sta.setInt(2, studentId);
                sta.setDouble(3, grade);

                sta.executeUpdate();
                System.out.println("Grade added successfully for student ID " + studentId + " in course ID " + courseId);
            }
            else {
            System.out.println("Student with ID " + studentId + " is not enrolled in course ID " + courseId);
            }
        }
        catch (SQLException e){
            System.out.println("Error adding grades." + e.getMessage());
        }
    }

    public void removeGrade(int gradeID){
        String query= "DELETE FROM Enrollments WHERE grade_id= ?";
        try{
            Connection conn= DatabaseHelper.getConnection();
            PreparedStatement stm= conn.prepareStatement(query);
            stm.setInt(1, gradeID);
            stm.executeUpdate();

            System.out.println("Grade has been deleted.");
        }
        catch (SQLException e){
            System.out.println("Error deleting grade." + e.getMessage());
        }
    }

    public void updateGrade(int courseId, int studentId, double grade){
        String gradeQuery= "UPDATE Grades SET grade= ? WHERE course_id= ? AND student_id= ?";
        try{
            Connection conn= DatabaseHelper.getConnection();
            PreparedStatement stm= conn.prepareStatement(gradeQuery);
            stm.setInt(2, courseId);
            stm.setInt(3, studentId);
            stm.setDouble(1, grade);
            stm.executeUpdate();
            System.out.println("Grade has been updated.");
        }
        catch (SQLException e){
            System.out.println("Error updating grade." + e.getMessage());
        }
    }
}
