package schoolcli.student;

import config.DatabaseHelper;

import java.sql.*;

public class Student {
    private String studentName;
    public void addStudent(String studentName) {
        String query = "INSERT INTO Students (student_name) VALUES (?)";
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, studentName);
            stmt.executeUpdate();
            System.out.println("Student '" + studentName + "' added.");
        } catch (SQLException e) {
            System.out.println("Error adding student: " + e.getMessage());
        }
    }

    public void deleteStudent(String studentName){
        String query = "DELETE from Students WHERE student_name = (?)";
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, studentName);
            stmt.executeUpdate();
            System.out.println("Student '" + studentName + "' deleted.");
        } catch (SQLException e) {
            System.out.println("Error adding student: " + e.getMessage());
        }
    }
    public void updateStudent(String studentName,String updatedStudentName){
        String query = "UPDATE Students SET student_name = ? WHERE student_name = ?";
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, updatedStudentName);
            stmt.setString(2,studentName);
            stmt.executeUpdate();
            System.out.println("Student '" + studentName + "' Updated to "+updatedStudentName);
        } catch (SQLException e) {
            System.out.println("Error adding course: " + e.getMessage());
        }
    }
    public void getStudent (){
        String query = "SELECT * FROM Students";
        try (Connection conn = DatabaseHelper.getConnection();
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery(query)){
            while(rs.next()){
                System.out.println("Student :-> "+ rs.getString("student_name"));
            }
        }

        catch (SQLException e) {
            System.out.println("Error printing Students: " + e.getMessage());
        }

    }
}
