package schoolcli;

import schoolcli.course.Course;
import schoolcli.enroll.EnrolStudent;
import schoolcli.gradebook.GradeBook;
import schoolcli.student.Student;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        System.out.println("Welcome to the School: ->>>>>");
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;

        while (flag) {
            System.out.println("""
                    Which of the following you want to choose to do CURD operations: -
                    1. Course\
                    
                    2. Student\
                    
                    3. Assign Students to course. Type 3 to get info.\
                    
                    4.Assign Grades to students. Type 4 to get info.\
                    
                    5. Exit""");
            String userIntention = scanner.nextLine();

            switch (userIntention) {
                case "Course" -> System.out.println("""
                        You have four options to perform:-->\s
                        1-> Add course by typing 'add-course course name'\s
                        2-> Remove course by typing 'remove-course course name'\s
                        3-> View course by get-course and update by update-course.
                        """);
                case "Student" -> System.out.println("""
                        You have four options to perform:-->\s
                        1-> Add student by typing 'add-student student name'\s
                        2-> Remove student by typing 'remove-student student name'\s
                        3-> View students by get-student and update by update-student.
                        """);
                case "3" -> System.out.println("""
                        You have two options to perform:-->\s
                        1-> Enrol student to course\s
                        2-> Delete the whole enrolment\s
                        """);
                case "4" -> System.out.println("""
                        You have two options to perform:-->\s
                        1-> Assign Grade to student.\s
                        2-> Get students with grades.\s
                        """);
                case "exit" -> flag = false;
                default -> flag = false;

            }
            if(flag){
                processSchool();
            }
        }
    }
    public static void processSchool() throws SQLException {
        boolean flag = true;
        Course course = new Course();
        GradeBook gradeBook = new GradeBook();
        EnrolStudent enrolStudent = new EnrolStudent();
        Scanner scanner = new Scanner(System.in);
        Student student = new Student();
        while (flag) {
            System.out.println("Give the input\n");
            String input = scanner.nextLine();
            String[] inputPart = input.split(" ");
            try {

                switch (inputPart[0]) {
                    case "add-course" -> course.addCourse(inputPart[1]);
                    case "remove-course" -> course.deleteCourse(inputPart[1]);
                    case "update-course" -> course.updateCourse(inputPart[1], inputPart[2]);
                    case "get-course" -> course.getCourse();
                    case "add-student" -> student.addStudent(inputPart[1]);
                    case "delete-student" -> student.deleteStudent(inputPart[1]);
                    case "update-student" -> student.updateStudent(inputPart[1], inputPart[2]);
                    case "get-student" -> student.getStudent();
                    case "delete-enrol" -> enrolStudent.deleteStudentToCourse(inputPart[1]);
                    case "enrol-student" -> enrolStudent.enrolStudentToCourse(inputPart[1], inputPart[2]);
                    case "assign-grade" -> gradeBook.assignGrade(inputPart[1], inputPart[2], inputPart[3]);
                    case "get-studentsGrade" -> gradeBook.getStudentsWithGradesAndCourse();
                    case "get-average" -> gradeBook.getStudentAverage(inputPart[1]);
                    case "exit" -> flag = false;
                    default -> throw new IllegalStateException("Unexpected value: " + inputPart[0]);
                }
            } catch (SQLException | IllegalStateException e) {
                System.out.println("You have entered some wrong input. Try Again");
                processSchool();
            }

        }
    }
    }
