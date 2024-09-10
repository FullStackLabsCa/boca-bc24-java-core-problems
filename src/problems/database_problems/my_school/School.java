package problems.database_problems.my_school;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class School {

    //Add Courses to School
    public static void addCourse(String courseName) {
        String query = "insert into Courses (course_name) values (?)";
//        String courseIDQuery = "select course_id from Courses "
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, courseName);
            statement.executeUpdate();
            System.out.println("Course " + courseName + " added to school.");
        } catch (SQLException exception) {
            System.out.println("Error adding Course: " + exception.getMessage());
        }
    }

    //Enroll the Student to Course

    public static void enrollStudent(int studentID, String studentName, String courseName) {
        String courseQuery = "select course_id from Courses where course_name = ? ";
        String studentQuery = "select student_id from Students where student_id = ? ";
        String addStudentQuery = "insert into Students(student_id, student_name) values (?, ?)";
        String enrollQuery = "insert  into Enrollments(course_id, student_id) values (?,?) ";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement courseStatement = connection.prepareStatement(courseQuery);
             PreparedStatement studentStatement = connection.prepareStatement(studentQuery);
             PreparedStatement addStudentStatement = connection.prepareStatement(addStudentQuery);
             PreparedStatement enrollStatement = connection.prepareStatement(enrollQuery)

        ) {
            //1) check if course exists or not? if not then add to the data base
            courseStatement.setString(1, courseName);
            ResultSet courseResultSet = courseStatement.executeQuery();
            if (!courseResultSet.next()) {
                System.out.println("Error: " + courseName + " doesn't exist...");
                return;
            }
            int courseId = courseResultSet.getInt("course_id");

            //2) check if student exist in the Student table
            studentStatement.setInt(1, studentID);
            ResultSet studentResultSet = studentStatement.executeQuery();
            if (!studentResultSet.next()) {
                addStudentStatement.setInt(1, studentID);
                addStudentStatement.setString(2, studentName);
                addStudentStatement.executeUpdate();

                System.out.println("Student " + studentName + "(ID: " + studentID + " ) added to the Students table.");
            }
            enrollStatement.setInt(1, courseId);
            enrollStatement.setInt(2, studentID);
            enrollStatement.executeUpdate();
            System.out.println("Student " + studentID + " enrolled in course " + courseName + " .");

        } catch (SQLException e) {
            System.out.println("Error Message: " + e.getMessage());
        }
    }

    //Assign Grade
    public static void assignGrade(int studentId, String courseName, double grade) {
        String courseQuery = "select course_id from Courses where course_name = ?";
        String gradeQuery = "insert into Grades(course_id, student_id, grade) values (?,?,?)";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement courseStatement = connection.prepareStatement(courseQuery);
             PreparedStatement gradeStatement = connection.prepareStatement(gradeQuery)
        ) {
            courseStatement.setString(1, courseName);
            ResultSet resultSet = courseStatement.executeQuery();

            if (resultSet.next()) {
                int courseId = resultSet.getInt("course_id");
                gradeStatement.setInt(1, courseId);
                gradeStatement.setInt(2, studentId);
                gradeStatement.setDouble(3, grade);
                gradeStatement.executeUpdate();
                System.out.println("Grade " + grade + "assigned to student " + studentId
                        + " in Course " + courseName + " .");
            } else {
                System.out.println("Error: Course " + courseName + " doesn't exist. ");
            }

        } catch (SQLException e) {
            System.out.println("Error message: " + e.getMessage());
        }
    }

    //find the average of the grades of specific courseID
    public static void averageGrades(int course_id) {
        String courseQuery = "select course_id from Grades where course_id = ?";
        String averageQuery = " select avg(grade) from Grades where course_id = ? ";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement courseStatement = connection.prepareStatement(courseQuery);
             PreparedStatement averageStatement = connection.prepareStatement(averageQuery)
        ) {
            //to check if course_id is exist or not ?
            courseStatement.setInt(1, course_id);
            ResultSet resultCourseSet = courseStatement.executeQuery();
            if (resultCourseSet.next()) {
                averageStatement.setInt(1, course_id);
                ResultSet resultAverageSet = averageStatement.executeQuery();
                while (resultAverageSet.next()) {
                    double aDouble = resultAverageSet.getDouble("avg(grade)");
                    System.out.println("Average Grade: " + aDouble + " for Course: " + course_id + " .");
                }
            } else {
                System.out.println("Error: Course ID " + course_id + " not found in the Grade Table. ");
            }

        } catch (SQLException sqlException) {
            System.out.println("Error: Message " + sqlException.getMessage());
        }
    }

    //CGPA for student
    public static void cgpa(int studentId) {
        String studentQuery = "select student_id from Grades where student_id = ?";
        String cgpaQuery = "select avg(grade) from Grades where student_id = ?";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement studentStatment = connection.prepareStatement(studentQuery);
             PreparedStatement cgpaStatement = connection.prepareStatement(cgpaQuery)
        ) {
            studentStatment.setInt(1, studentId);
            ResultSet resultStudentSet = studentStatment.executeQuery();
            if (resultStudentSet.next()) {
                cgpaStatement.setInt(1, studentId);
                ResultSet resultCgpaSet = cgpaStatement.executeQuery();
                while (resultCgpaSet.next()) {
                    double aDouble = resultCgpaSet.getDouble("avg(grade)");
                    System.out.println("CGPA of student_id " + studentId + " is : " + aDouble ) ;
                }

            } else {
                System.out.println("Error: Student ID " + studentId + " not found in the Grade Table.");
            }


        } catch (SQLException sqlException) {
            System.out.println("Error: Message " + sqlException.getMessage());
        }
    }
}


