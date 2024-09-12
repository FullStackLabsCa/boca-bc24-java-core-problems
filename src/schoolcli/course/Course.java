package schoolcli.course;

import schoolcli.config.DatabaseHelper;

import java.sql.*;


public class Course {
    private String courseName;

    Connection conn;
    public Course() throws SQLException {
        this.conn  = DatabaseHelper.getConnection();
    }

    public void addCourse(String courseName) {
        String query = "INSERT INTO Courses (course_name) VALUES (?)";
        try (
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, courseName);
            stmt.executeUpdate();
            System.out.println("Course '" + courseName + "' added.");
        } catch (SQLException e) {
            System.out.println("Error adding course: " + e.getMessage());
        }
    }

    public void deleteCourse(String courseName){
        String query = "DELETE from Courses WHERE course_name = (?)";
        try (
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, courseName);
            stmt.executeUpdate();
            System.out.println("Course '" + courseName + "' deleted.");
        } catch (SQLException e) {
            System.out.println("Error adding course: " + e.getMessage());
        }
    }
    public void updateCourse(String courseName,String updatedCourse){
        String query = "UPDATE Courses SET course_name = ? WHERE course_name = ?";
        try (
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, updatedCourse);
            stmt.setString(2,courseName);
            stmt.executeUpdate();
            System.out.println("Course '" + courseName + "' Updated to "+updatedCourse);
        } catch (SQLException e) {
            System.out.println("Error adding course: " + e.getMessage());
        }
    }
    public void getCourse (){
        String query = "SELECT * FROM Courses";
        try (
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query)){
            while(rs.next()){
                System.out.println("Course :-> "+ rs.getString("course_name"));
            }
        }

      catch (SQLException e) {
            System.out.println("Error adding course: " + e.getMessage());
        }

    }



}
