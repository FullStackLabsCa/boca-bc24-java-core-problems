package jdbc.school;


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

    // Remove course
    public void removeCourse(String courseName) {
        String query = "SELECT course_id FROM Courses WHERE course_name = ?";
        String enrollmentQuery = "SELECT enrollment_id FROM Enrollments WHERE course_id = ?";
        String deleteQuery = "DELETE FROM Courses WHERE course_name = ?";
        try (Connection conn = DatabaseHelper.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);
        PreparedStatement fetchStmt = conn.prepareStatement(enrollmentQuery);
        PreparedStatement deleteStmt = conn.prepareStatement(deleteQuery)) {
            stmt.setString(1, courseName);
            ResultSet courseRs = stmt.executeQuery();

            if (!courseRs.next()) {
                System.out.println("Error: Course '" + courseName + "' does not exist.");
                return;
            }

            fetchStmt.setInt(1, courseRs.getInt("course_id"));
            ResultSet enrollmentRs = fetchStmt.executeQuery();

            if (enrollmentRs.next()) {
                System.out.println("Error: Students enrolled in this course.");
                return;
            }

            deleteStmt.setString(1, courseName);
            int result = deleteStmt.executeUpdate();
            if (result > 0) {
                System.out.println("Course '" + courseName + "' removed.");
            }

        } catch (SQLException e) {
            System.out.println("Error removing course: " + e.getMessage());
        }
    }

    // Get list of unique courses
    public void getCourseList() {
            String query = "SELECT course_name FROM Courses;";
            try (Connection conn = DatabaseHelper.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
                 ResultSet courseSet = stmt.executeQuery();
                 if (!courseSet.isBeforeFirst()) {
                     System.out.println("Error: No courses found in db.");
                     return;
                 }
                StringBuilder sb = new StringBuilder();
                System.out.println("Course list::");
                while (courseSet.next()) {
                    sb.append(courseSet.getString("course_name")).append("\n");
                }
                System.out.println(sb.toString());
            } catch (SQLException e) {
                System.out.println("Error fetching course list: " + e.getMessage());
            }
    }

    // List grades of student for given course
    public void listGrades(String courseName) {
        String gradeQuery = "SELECT s.student_name, g.grade FROM Grades g INNER JOIN Students s ON s.student_id = g.student_id WHERE g.course_id = (SELECT course_id from Courses where course_name = ?)";

        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement gradeStmt = conn.prepareStatement(gradeQuery)) {
             gradeStmt.setString(1, courseName);
             ResultSet studentSet = gradeStmt.executeQuery();
            if (!studentSet.isBeforeFirst()) {
                System.out.println("Error: No students enrolled in this course.");
                return;
            }
            StringBuilder sb = new StringBuilder();
            System.out.println("Student grade list::");
            while (studentSet.next()) {
                sb.append(studentSet.getString("student_name")).append(", ")
                        .append(studentSet.getDouble("grade")).append("\n");
            }
            System.out.println(sb.toString());

        } catch (SQLException e) {
            System.out.println("Error while listing course grades.");
        }
    }

    // List unique students across all courses
    public void reportUniqueStudents() {
        String studentQuery = "SELECT DISTINCT s.student_name FROM Enrollments e INNER JOIN Students s ON s.student_id = e.student_id";

        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement studentStmt = conn.prepareStatement(studentQuery)) {
            ResultSet studentSet = studentStmt.executeQuery();
            if (!studentSet.isBeforeFirst()) {
                System.out.println("Error: No students enrolled in any course.");
                return;
            }
            StringBuilder sb = new StringBuilder();
            System.out.println("Unique student list::");
            while (studentSet.next()) {
                sb.append(studentSet.getString("student_name")).append("\n");
            }
            System.out.println(sb.toString());

        } catch (SQLException e) {
            System.out.println("Error while listing unique students.");
        }
    }

    // Calculate average score of given course
    public void reportAverageScore(String courseName) {
        String courseQuery = "SELECT AVG(g.grade) as average_grade FROM Grades g INNER JOIN Courses c ON c.course_id = g.course_id WHERE c.course_name = ?";

        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement courseStmt = conn.prepareStatement(courseQuery)) {
            courseStmt.setString(1, courseName);
            ResultSet courseSet = courseStmt.executeQuery();
            if (!courseSet.isBeforeFirst()) {
                System.out.println("Error: No students enrolled in this course.");
                return;
            }
            if (courseSet.next()) {
                String averageGrade = courseSet.getString("average_grade");
                System.out.println("Course average score: " + averageGrade);
            }

        } catch (SQLException e) {
            System.out.println("Error while listing course average scores.");
        }
    }

    // Calculate CGPA of given student across all course
    public void reportCumulativeAverage(String studentName) {

        String courseQuery = "SELECT AVG(g.grade) as average_grade FROM Grades g INNER JOIN Students s ON s.student_id = g.student_id WHERE s.student_name = ?";

        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement courseStmt = conn.prepareStatement(courseQuery)) {
            courseStmt.setString(1, studentName);
            ResultSet courseSet = courseStmt.executeQuery();
            if (!courseSet.isBeforeFirst()) {
                System.out.println("Error: No students enrolled in this course.");
                return;
            }
            if (courseSet.next()) {
                String averageGrade = courseSet.getString("average_grade");
                System.out.println("Student average score: " + averageGrade);
            }

        } catch (SQLException e) {
            System.out.println("Error while listing student average scores.");
        }
    }
}