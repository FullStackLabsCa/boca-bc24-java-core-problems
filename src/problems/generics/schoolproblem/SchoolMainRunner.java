package problems.generics.schoolproblem;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class SchoolMainRunner {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        School<String, String, Integer> school = new School<>();

        while (true) {
            System.out.println("1. Add Course");
            System.out.println("2. Enroll Student");
            System.out.println("3. Assign grade");
            System.out.println("4. List grades");
            System.out.println("5. List courses");
            System.out.println("6. Unique courses");
            System.out.println("7. Unique Students");
            System.out.println("8. Average Score by Course");
            System.out.println("9. Cumulative Average Score by Student");
            System.out.println("10.Exit");
            System.out.println("10.Exit");
            System.out.println("Please choose operation to perform");
            int inputOption = input.nextInt();
            input.nextLine();

            switch(inputOption) {
                case 1:
                    System.out.print("Enter course name to add: ");
                    String courseName = input.nextLine();
                    school.addCourse(courseName);
                    break;

                case 2:
                    System.out.print("Enter course name: ");
                    courseName = input.nextLine();
                    System.out.print("Enter student ID to enroll: ");
                    String studentId = input.nextLine();
                    school.enrollStudent(courseName, studentId);
                    break;

                case 3:
                    System.out.print("Enter course name: ");
                    courseName = input.nextLine();
                    System.out.print("Enter student ID: ");
                    studentId = input.nextLine();
                    System.out.print("Enter grade to assign: ");
                    int grade = input.nextInt();
                    input.nextLine();
                    school.assignGrade(courseName, studentId, grade);
                    break;
                case 4:
                    System.out.print("Enter course name to find the grades: ");
                    courseName = input.nextLine();
                    school.listGrades(courseName);
                case 5:
                    school.listCourses();
                    break;
                case 6:
                    school.reportUniqueCourses();
                    break;
                case 7:
                    school.reportUniqueStudents();
                    break;
                case 8:
                    System.out.print("Enter course name to report average score: ");
                    courseName = input.nextLine();
                    school.reportAverageScore(courseName);
                    break;
                case 9:
                    System.out.print("Enter student ID to report cumulative average: ");
                    studentId = input.nextLine();
                    school.reportCumulativeAverage(studentId);
                    break;
                case 10:
                    System.out.println("Exiting program.");
                    input.close();
                    return;
                default:
                    System.out.println("Error: Unknown command. Please use one of the available commands.");
                    break;
            }
        }
    }
}
