package problems.jdbc.school;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@SuppressWarnings("java:S106")
public class School {
    public void addingCourse(String courseName) {
        String query = "INSERT INTO Courses (course_name) VALUES (?)";
        try (Connection connection = Database.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, (String) courseName);
            preparedStatement.executeUpdate();
            System.out.println("Course '" + courseName + "' added.");
        } catch (SQLException e) {
            System.out.println("Error adding course: " + e.getMessage());
        }
    }

    public void enrollStudentToCourse(String studentId, String studentName, String courseName) {
        String courseQuery = "SELECT course_id FROM Courses WHERE course_name = ?";
        String studentQuery = "SELECT student_id FROM Students WHERE student_id = ?";
        String addStudentQuery = "INSERT INTO Students (student_id, student_name) VALUES (?,?)";
        String enrollStudentQuery = "INSERT INTO Enrollments (course_id, student_id) VALUES (?,?)";

        try (Connection connection = Database.getConnection()) {
            PreparedStatement courseStatement = connection.prepareStatement(courseQuery);
            PreparedStatement studentStatement = connection.prepareStatement(studentQuery);
            PreparedStatement addStudentStatement = connection.prepareStatement(addStudentQuery);
            PreparedStatement enrollStudentStatement = connection.prepareStatement(enrollStudentQuery);

            //Step 1: check if the course exits
            courseStatement.setString(1, String.valueOf(courseName));
            ResultSet courseResultSet = courseStatement.executeQuery();

            if (!courseResultSet.next()) {
                System.out.println("Error: Course " + courseName + " does not exist.");
            }

            int courseId = courseResultSet.getInt("course_id");

            //Step 2: Check if student exists
            studentStatement.setInt(1, Integer.parseInt(studentId));
            ResultSet studentResultSet = studentStatement.executeQuery();

            if (!studentResultSet.next()) {
                //step 3: if student does not exist then add the student
                addStudentStatement.setInt(1, Integer.parseInt(studentId));
                addStudentStatement.setString(2, (String) studentName);
                addStudentStatement.executeUpdate();
                System.out.println("Student '" + studentName + "' ID: " + studentId + " added in the student table '");
            }

            //Step 4: enroll the student in the course
            enrollStudentStatement.setInt(1, courseId);
            enrollStudentStatement.setInt(2, Integer.parseInt(studentId));
            enrollStudentStatement.executeUpdate();

            System.out.println("Student '" + studentId + "' enrolled in course '" + courseName + "'.");
        } catch (SQLException e) {
            System.out.println("Error in enrolling student: " + e.getMessage());
        }
    }

    public void assignGradesToStudent(String courseName, String studentId, double grade) {
        String courseQuery = "SELECT course_id FROM Courses WHERE course_name=?";
        String gradeQuery = "INSERT INTO Grades(course_id, student_id, grade) VALUES(?,?,?)";

        try {
            Connection connection = Database.getConnection();
            PreparedStatement courseStatement = connection.prepareStatement(courseQuery);
            PreparedStatement gradeStatement = connection.prepareStatement(gradeQuery);

            //Fetch course id
            courseStatement.setString(1, courseName);
            ResultSet resultSet = courseStatement.executeQuery();
            if (resultSet.next()) {
                int courseId = resultSet.getInt("course_id");

                //assign grade
                gradeStatement.setInt(1, courseId);
                gradeStatement.setInt(2, Integer.parseInt(String.valueOf(studentId)));
                gradeStatement.setDouble(3, Double.parseDouble(String.valueOf(grade)));
                gradeStatement.executeUpdate();

                System.out.println("Grade '" + grade + "' assigned to student '" + studentId + "' in course '" + courseName + "'.");
            } else {
                System.out.println("Error course  '" + courseName + "' does not exist'");
            }
        } catch (SQLException e) {
            System.out.println();
        }
    }

    public void listGrades(String courseName) {
        String gradesQuery = "SELECT * FROM Grades WHERE course_id = ?";
        String courseQuery = "SELECT * FROM Courses WHERE course_name=?";
        String studentQuery = "SELECT student_name FROM Students WHERE student_id=?";
        try (Connection connection = Database.getConnection()) {
            PreparedStatement gradesStatement = connection.prepareStatement(gradesQuery);
            PreparedStatement courseStatement = connection.prepareStatement(courseQuery);
            PreparedStatement studentStatement = connection.prepareStatement(studentQuery);

            //check if the course exits
            courseStatement.setString(1, String.valueOf(courseName));
            ResultSet courseResultSet = courseStatement.executeQuery();

            if (!courseResultSet.next()) {
                System.out.println("Error: Course " + courseName + " does not exist.");
            }

            int courseId = courseResultSet.getInt("course_id");

            //fetching grades
            gradesStatement.setInt(1, courseId);
            ResultSet gradeResultSet = gradesStatement.executeQuery();
            int count = 0;

            while (gradeResultSet.next()) {
                count++;
                int studentID = gradeResultSet.getInt("student_id");
                double grade = gradeResultSet.getDouble("grade");
                studentStatement.setInt(1, studentID);
                ResultSet studentResultSet = studentStatement.executeQuery();
                if (studentResultSet.next()) {
                    String name = studentResultSet.getString("student_name");
                    System.out.println("Student Name: " + name + " course name: " + courseName + " grade: " + grade);
                }
            }
            if (count == 0) {
                System.out.println("No grades in the given course");
            }
        } catch (SQLException e) {
            System.out.println("Error in the fetching grades: " + e.getMessage());
        }
    }

    public void listStudents() {
        String query = "SELECT * FROM Students";

        try (Connection connection = Database.getConnection()) {
            PreparedStatement studentStatement = connection.prepareStatement(query);
            ResultSet resultSet = studentStatement.executeQuery();

            while (resultSet.next()) {
                int studentID = resultSet.getInt("student_id");
                String name = resultSet.getString("student_name");
                System.out.println("Student ID: " + studentID + " and name: " + name);
            }
        } catch (SQLException e) {
            System.out.println("Error: in fetching students: " + e.getMessage());
        }
    }

    public void getReportCumulativeAverage(String sID) {
        int count = 0;
        double average = 0;
        double value = 0;
        String gradesQuery = "SELECT * FROM Grades WHERE student_id = ?";
        try (Connection connection = Database.getConnection()) {
            PreparedStatement gradesStatement = connection.prepareStatement(gradesQuery);

            //fetching grades
            gradesStatement.setInt(1, Integer.parseInt((String) sID));
            ResultSet gradeResultSet = gradesStatement.executeQuery();
            while (gradeResultSet.next()) {
                double grade = gradeResultSet.getDouble("grade");
                value = value + grade;
                count++;
            }
            if (count == 0) {
                System.out.println("No student in the given student id");
            }
            System.out.println("Cumulative average score for student '" + sID + "': " + value / count);
        } catch (SQLException e) {
            System.out.println("Error in the fetching average score: " + e.getMessage());
        }
    }

    public void getReportAverageScore(String courseName) {
        int count = 0;
        double average = 0;
        double value = 0;
        String gradesQuery = "SELECT * FROM Grades WHERE course_id = ?";
        String courseQuery = "SELECT * FROM Courses WHERE course_name=?";
        try (Connection connection = Database.getConnection()) {
            PreparedStatement gradesStatement = connection.prepareStatement(gradesQuery);
            PreparedStatement courseStatement = connection.prepareStatement(courseQuery);

            //check if the course exits
            courseStatement.setString(1, String.valueOf(courseName));
            ResultSet courseResultSet = courseStatement.executeQuery();

            if (!courseResultSet.next()) {
                System.out.println("Error: Course " + courseName + " does not exist.");
            }

            int courseId = courseResultSet.getInt("course_id");

            //fetching grades
            gradesStatement.setInt(1, courseId);
            ResultSet gradeResultSet = gradesStatement.executeQuery();

            while (gradeResultSet.next()) {
                double grade = gradeResultSet.getDouble("grade");
                value = value + grade;
                count++;
            }
            if (count == 0) {
                System.out.println("No grades in the given course");
            }
            average = value / count;
            System.out.println("Average score for course '" + courseName + "': " + average);
        } catch (SQLException e) {
            System.out.println("Error in the fetching average score: " + e.getMessage());
        }
    }

    public void listAllCourses() {
        String query = "select * from Courses";
//        String query = "SELECT * FROM Courses";

        try (Connection connection = Database.getConnection()) {
            PreparedStatement coursesStatement = connection.prepareStatement(query);
            ResultSet resultSet = coursesStatement.executeQuery();

            while (resultSet.next()) {
                int courseId = resultSet.getInt("course_id");
                String courseName = resultSet.getString("course_name");
                System.out.println("Courses Enrolled: id: " + courseId + " course name: " + courseName);

            }
        } catch (SQLException e) {
            System.out.println("Error: in fetching Courses: " + e.getMessage());
        }
    }

    public void processCommand(String operation) {
        String[] array = operation.split(" ");
        switch (array[0]) {
            case "add_course":
                addingCourse(array[1]);
                break;
            case "list_courses":
                listAllCourses();
                break;
            case "enroll_student":
                enrollStudentToCourse(array[1], array[2], array[3]);
                break;
            case "assign_grade":
                assignGradesToStudent(array[1], array[2], Double.valueOf(array[3]));
                break;
            case "list_grades":
                listGrades(array[1]);
                break;
            case "report_unique_courses":
                listAllCourses();
                break;
            case "report_unique_students":
                listStudents();
                break;
            case "report_cumulative_average":
                getReportCumulativeAverage(array[1]);
                break;
            case "report_average_score":
                getReportAverageScore(array[1]);
                break;
            default:
                System.out.println("Error: Unknown command 'unknown_command'. Please use a valid command.");
        }
    }

    public void printOperationCommands() {
        System.out.println("Enter the command to execute the operation: " +
                "\nadd_course <course_name>" +
                "\nenroll_student <student_id student_name course_name>" +
                "\nassign_grade <course_name student_name_or_id grade>" +
                "\nlist_courses" +
                "\nlist_grades <course_name>" +
                "\nreport_unique_courses" +
                "\nreport_unique_students" +
                "\nreport_cumulative_average <student_name_or_id>" +
                "\nreport_average_score <course_name>" +
                "\nenter quit to exit the command line");
    }

    public void startProcess() {
        Scanner scanner = new Scanner(System.in);
        printOperationCommands();
        while (true) {
            System.out.print(">");
            String inputValue = scanner.nextLine().trim();
            if (inputValue.equals("quit")) {
                System.out.println("Exiting the School System");
                break;
            }
            processCommand(inputValue);
        }
    }

    public static void main(String[] args) {
        School school = new School();
        school.startProcess();
    }
}


