package problems.jdbc.students;

import problems.jdbc.DatabaseHelper;

import java.sql.*;

public class Student {

    public void addStudent(String studentName){
        String query = "INSERT INTO Students (student_name) VALUES (?)";
        String queryCheck= "SELECT student_name FROM Students WHERE student_name= ?";
        String name= null;
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, studentName);
            PreparedStatement st= conn.prepareStatement(queryCheck);
            st.setString(1, studentName);
            ResultSet rs= st.executeQuery();
            rs.next();
            name= rs.getString(1);
            if(name.equals(studentName)){
                System.out.println("Student with same name is already present");
            }
            else {
                stmt.executeUpdate();
                System.out.println("Student '" + studentName + "' added.");
            }
        } catch (SQLException e) {
            System.out.println("Error adding student: " + e.getMessage());
        }
    }

    public void updateStudent(int studentId,String student_name){
        String query = "UPDATE Students SET student_name = (?) WHERE student_id = (?)";
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, student_name);
            stmt.setString(2, String.valueOf(studentId));
            stmt.executeUpdate();
            System.out.println("Student having ID '" + studentId + "' has been updated.");
        } catch (SQLException e) {
            System.out.println("Error updating student: " + e.getMessage());
        }
    }

    public void removeStudent(int studentId){
        String query = "DELETE FROM Students WHERE student_id = (?)";
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, String.valueOf(studentId));
            stmt.executeUpdate();
            System.out.println("Student having ID '" + studentId + "' has been removed.");
        } catch (SQLException e) {
            System.out.println("Error removing student: " + e.getMessage());
        }
    }

    public void getStudents(){
        String query = "SELECT * " + "FROM Students";
        try {
            Connection conn = DatabaseHelper.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                System.out.println(rs.getString(2));
            }
        }
        catch (SQLException e){
            System.out.println("Error getting all students: " + e.getMessage());
        }
    }
}
