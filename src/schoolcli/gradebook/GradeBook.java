package schoolcli.gradebook;

import config.DatabaseHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GradeBook {
    Connection conn;

    public GradeBook() throws SQLException{
        this.conn = DatabaseHelper.getConnection();
    }

    public void assignGrade(String studentId, String courseId, String grade) {
        String query = "SELECT 1 FROM Enrollments WHERE course_id = (?) AND student_id = (?)";
        String gradeQuery = "INSERT INTO Grades (course_id, student_id, grade) VALUES (?, ?, ?)";
        try (
             PreparedStatement stmt = conn.prepareStatement(query))
        {
            stmt.setString(1, courseId);
            stmt.setString(2, studentId);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                PreparedStatement statement = conn.prepareStatement(gradeQuery);
                statement.setString(1,courseId);
                statement.setString(2,studentId);
                statement.setString(3, grade);
                statement.executeUpdate();

                System.out.println("Grades has been assigned to the student");
            }else{
                System.out.println("Student and course are not assigned to each other");
            }

        } catch (SQLException e) {
            System.out.println("Error adding Grades: " + e.getMessage());
        }
    }
    public void getStudentsWithGradesAndCourse(){
        String studentCourseGradeQuery = "SELECT * FROM Grades";
        try(
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(studentCourseGradeQuery);
        ) {
            while (resultSet.next()){
                int studentId = resultSet.getInt(3);
                int courseId = resultSet.getInt(2);
                double grade = resultSet.getDouble(4);
                String studentQuery = "SELECT student_name FROM Students WHERE student_id=" + studentId;
                try (Statement statement1 = conn.createStatement();
                        ResultSet studentResultSet = statement1.executeQuery(studentQuery)) {
                    if (studentResultSet.next()) {
                        String studentName = studentResultSet.getString("student_name");

                        // Query to get the course name
                        String courseQuery = "SELECT course_name FROM Courses WHERE course_id=" + courseId;
                        try (Statement statement2 = conn.createStatement();
                                ResultSet courseResultSet = statement2.executeQuery(courseQuery)) {
                            if (courseResultSet.next()) {
                                String courseName = courseResultSet.getString("course_name");

                                // Output the student's grade in the course
                                System.out.println("Student: " + studentName + " has a grade of " + grade + " in " + courseName);
                            }else{
                                courseResultSet.close();
                            }
                        }
                    }else {
                        studentResultSet.close();
                    }
                }

            }
        } catch (SQLException e) {
            System.out.println("Error "+e.getMessage());
        }
    }
    public void getStudentAverage(String studentName) throws SQLException {
        List<Double> gradeList = new ArrayList<>();
        double sum = 0;

        String query = """
                SELECT g.grade
                FROM Grades g
                JOIN Students s ON g.student_id = s.student_id
                WHERE s.student_name = ?;
                """;
        try(PreparedStatement preparedStatement = conn.prepareStatement(query)){
            preparedStatement.setString(1,studentName);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                gradeList.add(resultSet.getDouble("grade"));
            }
            for (double grade:gradeList){
                sum+=grade;
            }
            System.out.println(studentName +" has percentage of "+ sum/gradeList.size());
        }
    }

}
