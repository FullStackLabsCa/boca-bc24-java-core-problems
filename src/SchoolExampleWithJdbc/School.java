package SchoolExampleWithJdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class School {
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

    // Enroll student
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

    public void listCourses() {
        String courseQuery = "SELECT * from Courses";
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement courseStmt = conn.prepareStatement(courseQuery)) {
            // Execute the query
            ResultSet courseRs = courseStmt.executeQuery();
            // Process the result set
            System.out.println("Courses Offered : ");
            while (courseRs.next()) {
                String courseName = courseRs.getString("course_name");
                System.out.println(courseName);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void listGrades() {
        String courseQuery = "select students.student_name, grades.grade from grades Join Students On students.student_id= grades.student_id;";
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement courseStmt = conn.prepareStatement(courseQuery)) {
            // Execute the query
            ResultSet gradeRs = courseStmt.executeQuery();
            // Process the result set
            System.out.println("Student with Grades : ");
            while (gradeRs.next()) {
                String studentName = gradeRs.getString("student_name");
                double grade = gradeRs.getDouble("grade");
                System.out.println(studentName + " " + grade);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void uniqueCourse() {
        String uniqueCourse = "select Courses.course_name, enrollments.student_id ,students.student_name from Courses Inner Join enrollments On enrollments.course_id = courses.course_id\n" +
                "inner join  students on students.student_id = enrollments.student_id ";
        try (Connection connection = DatabaseHelper.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(uniqueCourse)) {
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println("Students with unique Courses");
            System.out.println("Courses :" + " " + " Students");
            while ((rs.next())) {
                String courseName = rs.getString("course_name");
                String studentName = rs.getString("student_name");
                System.out.println("Course Name : " + courseName + ",  Student Name : " + studentName + ".");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void uniqueStudents() {
        String query = "select students.student_name from students" +
                " join enrollments on students.student_id = enrollments.student_id ;";
        try (Connection connection = DatabaseHelper.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println("Students with unique courses are :");
            while (rs.next()) {
                String studentName = rs.getString("student_name");
                System.out.println(studentName);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void averageGrades(String CourseName) {
        String avgQuery = " SELECT courses.course_name, AVG(grades.grade) AS average_grade FROM grades  INNER JOIN courses ON courses.course_id = grades.course_id INNER JOIN students ON students.student_id = grades.student_id where courses.course_name = ?";
        try (Connection connection = DatabaseHelper.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(avgQuery)) {

            preparedStatement.setString(1, CourseName);
            ResultSet avgRs = preparedStatement.executeQuery();

            while (avgRs.next()) {
                String courseName = avgRs.getString("course_name");
                double grade = avgRs.getDouble("average_grade");
                System.out.println("Course Name : " + courseName);
                System.out.println("Average grade :" + grade);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
