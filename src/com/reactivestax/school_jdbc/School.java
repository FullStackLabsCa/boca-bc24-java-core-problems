package com.reactivestax.school_jdbc;
import java.sql.*;

import static com.reactivestax.school_jdbc.DatabaseConnectionPool.dataSource;

public class School {

     //add course
    public void addCourse(String courseName) {
        String query = "INSERT INTO Courses (course_name) VALUES (?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
             stmt.setString(1, courseName);
             stmt.executeUpdate();
            System.out.println("Course '" + courseName + "' added.");
        } catch (SQLException e) {
            System.out.println("Error adding course: " + e.getMessage());
        }
    }

    //enroll student
    public void enrollStudent(int studentId, String courseName,String studentName) {
        // SQL queries for checking course existence, student existence, and enrolling students
        String courseQuery = "SELECT course_id FROM Courses WHERE course_name = ?";
        String studentQuery = "SELECT student_id FROM Students WHERE student_id = ?";
        String addStudentQuery = "INSERT INTO Students (student_id, student_name) VALUES (?, ?)";
        String enrollQuery = "INSERT INTO Enrollments (course_id, student_id) VALUES (?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement courseStmt = conn.prepareStatement(courseQuery);
             PreparedStatement studentStmt = conn.prepareStatement(studentQuery);
             PreparedStatement addStudentStmt = conn.prepareStatement(addStudentQuery);
             PreparedStatement enrollStmt = conn.prepareStatement(enrollQuery)) {

            // Fetch course ID based on course name
            courseStmt.setString(1, courseName);
            ResultSet rsCourse = courseStmt.executeQuery();

            if (rsCourse.next()) {
                int courseId = rsCourse.getInt("course_id");

                // Check if student exists
                studentStmt.setInt(1, studentId);
                ResultSet rsStudent = studentStmt.executeQuery();

                if (!rsStudent.next()) {
                    // If student does not exist, add the student
                    addStudentStmt.setInt(1, studentId);
                    addStudentStmt.setString(2, studentName);
                    addStudentStmt.executeUpdate();
                    System.out.println("Student '" + studentName + "' with ID '" + studentId + "' added.");
                }

                // Enroll the student in the course
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
    //assign grade
    public void assignGrade(int studentId, String courseName, double grade) {
        // SQL queries for checking enrollment and assigning grades
        String courseQuery = "SELECT course_id FROM Courses WHERE course_name = ?";
        String enrollmentQuery = "SELECT 1 FROM Enrollments WHERE course_id = ? AND student_id = ?";
        String gradeQuery = "INSERT INTO Grades (course_id, student_id, grade) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE grade = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement courseStmt = conn.prepareStatement(courseQuery);
             PreparedStatement enrollmentStmt = conn.prepareStatement(enrollmentQuery);
             PreparedStatement gradeStmt = conn.prepareStatement(gradeQuery)) {

            // Fetch course ID based on course name
            courseStmt.setString(1, courseName);
            ResultSet rs = courseStmt.executeQuery();

            if (rs.next()) {
                int courseId = rs.getInt("course_id");

                // Check if the student is enrolled in the course
                enrollmentStmt.setInt(1, courseId);
                enrollmentStmt.setInt(2, studentId);
                try (ResultSet enrollmentRs = enrollmentStmt.executeQuery()) {
                    if (enrollmentRs.next()) {
                        // Assign the grade
                        gradeStmt.setInt(1, courseId);
                        gradeStmt.setInt(2, studentId);
                        gradeStmt.setDouble(3, grade);
                        gradeStmt.setDouble(4, grade); // for update
                        gradeStmt.executeUpdate();
                        System.out.println("Grade '" + grade + "' assigned to student '" + studentId + "' in course '" + courseName + "'.");
                    } else {
                        System.out.println("Error: Cannot assign grade. Student '" + studentId + "' is not enrolled in course '" + courseName + "'.");
                    }
                }
            } else {
                System.out.println("Error: Course '" + courseName + "' does not exist.");
            }
        } catch (SQLException e) {
            System.out.println("Error assigning grade: " + e.getMessage());
        }
    }


    //list Courses
    public void listCourses() {
        // SQL query to retrieve all course IDs and names
        String sql = "SELECT course_id, course_name FROM Courses";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("Courses offered:");
            int i = 1;
            while (rs.next()) {
                // Fetching course ID and course name
                int courseId = rs.getInt("course_id");
                String courseName = rs.getString("course_name");
                System.out.println(i + ". " + courseId + " - " + courseName);
                i++;
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving courses: " + e.getMessage());
        }
    }

    //list grades
    public void listGrades() {
        String courseQuery = "select Students.student_name,Grades.grade from Grades Join Students on Students.student_id=Grades.student_id;";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement courseStmt = conn.prepareStatement(courseQuery)) {
            // Execute the query
            ResultSet gradeRs = courseStmt.executeQuery();
            // Process the result set
            System.out.println("Grades : ");
            while (gradeRs.next()) {
                String studentName = gradeRs.getString("student_name");
                double grade = gradeRs.getDouble("grade");
                System.out.println("Student: "+studentName + "  with grades : " + grade);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving courses: " + e.getMessage());
        }
    }


    //report average
    public void reportAverageScore(String courseName) {
        String courseQuery = "SELECT course_id FROM Courses WHERE course_name = ?";
        String gradesQuery = "SELECT grade FROM Grades WHERE course_id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement courseStmt = conn.prepareStatement(courseQuery);
             PreparedStatement gradesStmt = conn.prepareStatement(gradesQuery)) {

            // Fetch course ID
            courseStmt.setString(1, courseName);
            try (ResultSet courseRs = courseStmt.executeQuery()) {
                if (!courseRs.next()) {
                    System.out.println("Course '" + courseName + "' does not exist.");
                    return;
                }

                int courseId = courseRs.getInt("course_id");

                // Fetch grades and compute average
                gradesStmt.setInt(1, courseId);
                try (ResultSet gradesRs = gradesStmt.executeQuery()) {
                    double sum = 0;
                    int count = 0;

                    while (gradesRs.next()) {
                        sum += gradesRs.getDouble("grade");
                        count++;
                    }
                    if (count > 0) {
                        double averageScore = sum / count;
                        System.out.printf("Average score for course: ", courseName, averageScore);
                    } else {
                        System.out.println("No grades found for course '" + courseName + "'.");
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving average score: " + e.getMessage());
        }
    }

}


