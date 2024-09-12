package schoolcli.student;



import schoolcli.config.DatabaseHelper;

import java.sql.*;

public class Student {
    Connection conn;

    public Student() throws SQLException {
        this.conn = DatabaseHelper.getConnection();
    }

    public void addStudent(String studentName) {
        String checkQuery = "SELECT COUNT(*) FROM Students WHERE student_name = ?";
        String insertQuery = "INSERT INTO Students (student_name) VALUES (?)";
        try (
             PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {
            checkStmt.setString(1, studentName);
            ResultSet rs = checkStmt.executeQuery();
            rs.next();
            if (rs.getInt(1) > 0) {
                System.out.println("Student '" + studentName + "' already exists.");
            } else {
                try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
                    insertStmt.setString(1, studentName);
                    insertStmt.executeUpdate();
                    System.out.println("Student '" + studentName + "' added.");
                }
            }

        } catch (SQLException e) {
            System.out.println("Error adding student: " + e.getMessage());
        }
    }

    public void deleteStudent(String studentName){
        String checkQuery = "SELECT COUNT(*) FROM Students WHERE student_name = ?";
        String query = "DELETE from Students WHERE student_name = (?)";
        try (
             PreparedStatement stmt = conn.prepareStatement(checkQuery)) {
            stmt.setString(1, studentName);
            ResultSet resultSet = stmt.executeQuery();
            if(resultSet.getInt(1)==0){
                System.out.println("The student you want to delete is not present");
            }else {
                try(PreparedStatement insertStmt = conn.prepareStatement(query)){
                    insertStmt.setString(1,studentName);
                    insertStmt.executeUpdate();
                    System.out.println("Student '" + studentName + "' deleted.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error deleting student: " + e.getMessage());
        }
    }
    public void updateStudent(String studentName,String updatedStudentName) {
        String query = "UPDATE Students SET student_name = ? WHERE student_name = ?";
        String checkQuery = "SELECT COUNT(*) FROM Students WHERE student_name = ?";
        try (PreparedStatement statement = conn.prepareStatement(checkQuery)) {
            statement.setString(1,studentName);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.getInt(1)>0) {
                try (
                        PreparedStatement stmt = conn.prepareStatement(query)) {
                    stmt.setString(1, updatedStudentName);
                    stmt.setString(2, studentName);
                    stmt.executeUpdate();
                    System.out.println("Student '" + studentName + "' Updated to " + updatedStudentName);
                }
            }else{
                System.out.println("Student you trying to update is not present");
            }
        }catch (SQLException e) {
                System.out.println("Error adding course: " + e.getMessage());
            }

    }
    public void getStudent (){
        String query = "SELECT * FROM Students";
        try (
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
