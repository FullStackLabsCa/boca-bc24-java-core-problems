package problems.jdbc.courses;

import problems.jdbc.DatabaseHelper;

import java.sql.*;

public class Course {

    public void addCourse(String courseName){
        String query = "INSERT INTO Courses (course_name) VALUES (?)";
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, courseName);
            stmt.executeUpdate();
            System.out.println("Course '" + courseName + "' added.");
        } catch (SQLException e) {
            System.out.println("Error adding course: " + e.getMessage());
        }
    }

    public void updateCourse(int courseId,String course_name){
        String query = "UPDATE Courses SET course_name = (?) WHERE course_id = (?)";
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, course_name);
            stmt.setString(2, String.valueOf(courseId));
            stmt.executeUpdate();
            System.out.println("Course having ID '" + courseId + "' has been updated.");
        } catch (SQLException e) {
            System.out.println("Error updating course: " + e.getMessage());
        }
    }

    public void removeCourse(int courseId){
        String query = "DELETE FROM Courses WHERE course_id = (?)";
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, String.valueOf(courseId));
            stmt.executeUpdate();
            System.out.println("Course having ID '" + courseId + "' has been removed.");
        } catch (SQLException e) {
            System.out.println("Error removing course: " + e.getMessage());
        }
    }

    public void getCourses(){
        String query = "SELECT * " + "FROM Courses";
        try {
            Connection conn = DatabaseHelper.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                System.out.println(rs.getString(2)); //or rs.getString("column name");
            }
        }
        catch (SQLException e){
            System.out.println("Error getting all courses: " + e.getMessage());
        }
    }
}
