package io.reacticestax.jdbc;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class School {

//    public static class CourseNotFoundException extends RuntimeException {
//        public CourseNotFoundException(String message) {
//            super(message);
//        }

    // Add course
    public void addCourse(String courseName) {
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


    // Enroll student and ensure the student exists in the Students table
    public void enrollStudent(int studentId, String studentName, String courseName) {
        String courseQuery = "SELECT course_id FROM Courses WHERE course_name = ?";
        String studentQuery = "SELECT student_id FROM Students WHERE student_id = ?";
        String addStudentQuery = "INSERT INTO Students (student_id, student_name) VALUES (?, ?)";
        String enrollQuery = "INSERT INTO Enrollments (course_id, student_id) VALUES (?, ?)";

        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement courseStmt = conn.prepareStatement(courseQuery);
             PreparedStatement studentStmt = conn.prepareStatement(studentQuery);
             PreparedStatement addStudentStmt = conn.prepareStatement(addStudentQuery);
             PreparedStatement enrollStmt = conn.prepareStatement(enrollQuery)) {

            // Step 1: Check if the course exists
            courseStmt.setString(1, courseName);
            ResultSet courseRs = courseStmt.executeQuery();

            if (!courseRs.next()) {
                System.out.println("Error: Course '" + courseName + "' does not exist.");
                return;
            }
            int courseId = courseRs.getInt("course_id");

            // Step 2: Check if the student exists in the Students table
            studentStmt.setInt(1, studentId);
            ResultSet studentRs = studentStmt.executeQuery();

            if (!studentRs.next()) {
                // Step 3: If the student does not exist, add the student
                addStudentStmt.setInt(1, studentId);
                addStudentStmt.setString(2, studentName);
                addStudentStmt.executeUpdate();
                System.out.println("Student '" + studentName + "' (ID: " + studentId + ") added to the Students table.");
            }

            // Step 4: Enroll the student in the course
            enrollStmt.setInt(1, courseId);
            enrollStmt.setInt(2, studentId);
            enrollStmt.executeUpdate();

            System.out.println("Student '" + studentId + "' enrolled in course '" + courseName + "'.");

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


    public void listGrades(int course_id) {
        String listGradeQuery = "SELECT grade FROM Grades WHERE course_id = ?";

        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement listGradeStmt = conn.prepareStatement(listGradeQuery)) {
            listGradeStmt.setInt(1, course_id);
            ResultSet resultSet = listGradeStmt.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getDouble("grade"));
            }

        } catch (SQLException e) {
            System.out.println("Error listing grade: " + e.getMessage());
        }

    }


    public void listCourses() {
        String listCourseQuery = "SELECT course_name FROM Courses";

        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement listCourseStmt = conn.prepareStatement(listCourseQuery)) {

            ResultSet resultSet = listCourseStmt.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString("course_name"));
            }
        } catch (SQLException e) {
            System.out.println("Error listing courses: " + e.getMessage());
        }

    }


    public void reportUniqueCourses() {
        String listUniqueCourseQuery = "SELECT DISTINCT course_name FROM Courses";

        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement listUniqueCourseStmt = conn.prepareStatement(listUniqueCourseQuery)) {

            ResultSet resultSet = listUniqueCourseStmt.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString("course_name"));
            }
        } catch (SQLException e) {
            System.out.println("Error listing unique courses: " + e.getMessage());
        }

    }

    public void reportUniqueStudents() {
        String listUniqueStudentQuery = "SELECT Distinct student_id,student_name FROM Students";

        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement listUniqueStudentStmt = conn.prepareStatement(listUniqueStudentQuery)) {

            ResultSet resultSet = listUniqueStudentStmt.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString("student_name"));
            }
        } catch (SQLException e) {
            System.out.println("Error listing unique student: " + e.getMessage());
        }

    }

    public void reportAverageScore(int course_id) {
        String reportAverageQuery = "SELECT AVG(grade) as average FROM Grades WHERE course_id = ?" ;
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement reportAverageStmt = conn.prepareStatement(reportAverageQuery)) {

            ResultSet resultSet = reportAverageStmt.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("course_id"));
            }
        } catch (SQLException e) {
            System.out.println("Error reporting average score: " + e.getMessage());
        }

    }

    public void reportCumulativeAverage() {

    }


}
