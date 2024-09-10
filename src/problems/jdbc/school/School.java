package problems.jdbc.school;

import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class School {

    public void addCourse(String courseName) {
        String query = " INSERT  INTO Courses (course_name) VALUES (?)";
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, courseName);
            stmt.executeUpdate();
            System.out.println("Course '" + courseName + "'added.");
        } catch (SQLException e) {
            throw new RuntimeException(e); // for checked exception  while user is interacting with the system.
        }
    }

    // Enroll student
    public void enrollStudent(int studentId, String courseName) {
        String courseQuery = "SELECT course_id FROM Courses WHERE course_name = ?";
        String enrollQuery = "INSERT INTO Enrollments (course_id, student_id) VALUES (?, ?)";

        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement courseStmt = conn.prepareStatement(courseQuery);
             PreparedStatement enrollStmt = conn.prepareStatement(enrollQuery)) {

            // Fetch course ID
            courseStmt.setString(1, courseName);
            ResultSet rs = courseStmt.executeQuery();
            if (rs.next()) {
                int courseId = rs.getInt("course_id");

                // Enroll student
                enrollStmt.setInt(1, courseId);
                enrollStmt.setInt(2, studentId);
                enrollStmt.executeUpdate();
                System.out.println("Student '" + studentId + "' enrolled in course '" + courseName + "'.");
            } else {
                System.out.println("Error: Course '" + courseName + "' does not exist.");
            }
        } catch (SQLException e) {
            System.out.println("Error enrolling student: " + e.getMessage());
        }
    }

    // Assign grade
    public void assignGrade(int studentId, String courseName, double grade) {
        String courseQuery = "SELECT course_id FROM Courses WHERE course_name = ?";
        String gradeQuery = "INSERT INTO Grades (course_id, student_id, grade) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement courseStmt = conn.prepareStatement(courseQuery);
             PreparedStatement gradeStmt = conn.prepareStatement(gradeQuery)) {

            // Fetch course ID
            courseStmt.setString(1, courseName);
            ResultSet rs = courseStmt.executeQuery();
            if (rs.next()) {
                int courseId = rs.getInt("course_id");

                // Assign grade
                gradeStmt.setInt(1, courseId);
                gradeStmt.setInt(2, studentId);
                gradeStmt.setDouble(3, grade);
                gradeStmt.executeUpdate();
                System.out.println("Grade '" + grade + "' assigned to student '" + studentId + "' in course '" + courseName + "'.");
            } else {
                System.out.println("Error: Course '" + courseName + "' does not exist.");
            }
        } catch (SQLException e) {
            System.out.println("Error assigning grade: " + e.getMessage());
        }
    }

    public void addStudent(String studentName) {
        String query = " INSERT  INTO Students (student_name) VALUES (?)";
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, studentName);
            stmt.executeUpdate();
            System.out.println("Student '" + studentName + "'is added.");
        } catch (SQLException e) {
            throw new RuntimeException(e); // for checked exception  while user is interacting with the system.
        }
    }

    public void listGrades() {
        ArrayList<String> grades = new ArrayList<>();
        String query = " SELECT grade FROM Grades";
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                String grade = resultSet.getString("grade");
                grades.add(grade);
            }
            System.out.println("List of grades are: " + grades.toString());
        } catch (SQLException e) {
            throw new RuntimeException(e); // for checked exception  while user is interacting with the system.
        }
    }

    public void listAllCourses() {
        ArrayList<String> courses = new ArrayList<>();
        String query = " SELECT course_name FROM Courses";
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                String course = resultSet.getString("course_name");
                courses.add(course);
            }
            System.out.println("List of courses are: " + courses.toString());
        } catch (SQLException e) {
            throw new RuntimeException(e); // for checked exception  while user is interacting with the system.
        }
    }

    public void listUniqueStudents() {
        ArrayList<String> courses = new ArrayList<>();
        String query = " SELECT DISTINCT student_name FROM Students";
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                String course = resultSet.getString("student_name");
                courses.add(course);
            }
            System.out.println("Unique Students are: " + courses.toString());
        } catch (SQLException e) {
            throw new RuntimeException(e); // for checked exception  while user is interacting with the system.
        }
    }

    public void reportAverageScoreOfStudent(String studentId) {

        String query = " SELECT AVG(grade) as average_grade FROM Grades where student_id = ?";

        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, studentId);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                double averageScore = resultSet.getDouble("average_grade");
                System.out.println("Average Score is: " + averageScore);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e); // for checked exception  while user is interacting with the system.
        }
    }

    public void reportAverageScoreOfCourse(String courseName) {

        String courseQuery = "SELECT course_id FROM Courses WHERE course_name = ?";
        String query = " SELECT AVG(grade) as average_grade FROM Grades where course_id = ?";

        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement courseStmt = conn.prepareStatement(courseQuery);
             PreparedStatement statement = conn.prepareStatement(query)) {

            // Fetch course ID
            courseStmt.setString(1, courseName);
            ResultSet rs = courseStmt.executeQuery();
            if (rs.next()) {
                int courseId = rs.getInt("course_id");
                // set the courseId and retrieve it's corresponding grade.
                statement.setInt(1, courseId);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    double averageGrade = resultSet.getDouble("average_grade");
                    System.out.println("Average grade is: " + averageGrade);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error Getting Average grade: " + e.getMessage());
        }
    }

    public void callStoreProcedure(String studentId) throws SQLException {
        String query = "{call getStudentGrade(?, ?)}";
        try (Connection conn = DatabaseHelper.getConnection();
             CallableStatement stmt = conn.prepareCall(query)) {
            stmt.setInt(1, Integer.parseInt(studentId));
            stmt.registerOutParameter(2, Types.DOUBLE);
            stmt.execute();
            double aDouble1 = stmt.getDouble("grade");
            System.out.println("aa" + aDouble1);
            double aDouble = stmt.getDouble(2);
            System.out.println("average is: " + aDouble);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


