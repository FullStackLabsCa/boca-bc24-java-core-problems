package school_jdbc.student;

import school_jdbc.dbconnection.DatabaseHelper;

import java.sql.*;

public class Student {

    Connection conn;
    public Student() throws SQLException{
        this.conn = DatabaseHelper.getConnection();
    }

    public void addStudent(String studentName) {
        String query = "INSERT INTO Students (student_name) VALUES (?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, studentName);
            stmt.executeUpdate();
            System.out.println("Student: " + studentName + "' added.");
        } catch (SQLException e) {
            System.out.println("Error adding Student: " + e.getMessage());
        }
    }

    public void  removeStudent(String studentName) {
        String query = "DELETE FROM Students WHERE student_name =?";
        try(PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setString(1,studentName);
            stmt.executeUpdate();
            System.out.println("Student: "+ studentName + " removed.");
        }catch(SQLException e){
            System.out.println("Error Removing Student: "+ e.getMessage());
        }
    }

    public void  updateStudent(String newStudentName, String studentName) {
        String query = "UPDATE Students SET student_name = ? WHERE student_name =?";
        try(PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setString(1,newStudentName);
            stmt.setString(2,studentName);
            stmt.executeUpdate();
            System.out.println("Student: "+ studentName + " updated to "+newStudentName+".");
        }catch(SQLException e){
            System.out.println("Error Updating Student: "+ e.getMessage());
        }
    }

    public void  displayStudent() {
        String query = "SELECT * From Students";
        System.out.println("Students: ");
        try(Statement stmt = conn.createStatement()){
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()) {
                int studentId = rs.getInt("student_id");
                String studentName = rs.getString("student_name");
                System.out.println("StudentId => "+studentId+", StudentName => "+studentName);
            }
        }catch(SQLException e){
            System.out.println("Error Displaying Students: "+ e.getMessage());
        }
    }
}
