package school_jdbc.course;

import school_jdbc.dbconnection.DatabaseHelper;

import java.sql.*;

public class Course{

    Connection conn;
    public Course() throws SQLException{
        this.conn = DatabaseHelper.getConnection();
    }
    public void addCourse(String courseName) {
        String query = "INSERT INTO Courses (course_name) VALUES (?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, courseName);
            stmt.executeUpdate();
            System.out.println("Course '" + courseName + "' added.");
        } catch (SQLException e) {
            System.out.println("Error adding school_jdbc.course: " + e.getMessage());
        }
    }

    public void  removeCourse(String courseName) {
        String query = "DELETE FROM Courses WHERE course_name =?";
        try(PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setString(1,courseName);
            stmt.executeUpdate();
            System.out.println("Course: "+ courseName + " removed.");
        }catch(SQLException e){
            System.out.println("Error Removing Course: "+ e.getMessage());
        }
    }

    public void  updateCourse(String newCourseName, String courseName) {
        String query = "UPDATE Courses SET course_name = ? WHERE course_name =?";
        try(PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setString(1,newCourseName);
            stmt.setString(2,courseName);
            stmt.executeUpdate();
            System.out.println("Course: "+ courseName + " updated.");
        }catch(SQLException e){
            System.out.println("Error Updating Course: "+ e.getMessage());
        }
    }

    public void  displayCourse() {
        String query = "SELECT * From Courses";
        System.out.println("Courses: ");
        try(Statement stmt = conn.createStatement()){
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()) {
                int courseId = rs.getInt("course_id");
                String courseName = rs.getString("course_name");
                System.out.println("CourseId => "+courseId+", CourseName => "+courseName);
            }
        }catch(SQLException e){
            System.out.println("Error Updating Course: "+ e.getMessage());
        }
    }
}
