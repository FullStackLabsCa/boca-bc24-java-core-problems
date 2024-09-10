package problems.generics.databaseCourse;

import problems.generics.databaseCourse.Course;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Generic_DB_Course_Class {
    public static void main(String[] args) {

        Course<String, Integer> course = new Course<>();

        try {
            DatabaseHelper.initializeDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
//
//        // Enroll Students
//        course.enrollStudent("Krishna");
//        course.enrollStudent("Govardhan");
//        course.enrollStudent("Dwarkadhish");
//
//        // Assign grade
//        course.assignGrade("Krishna", 100);
//        course.assignGrade("Dwarkadhish", 95);
//
//        // Assign grade to not enrolled student
//        course.assignGrade("TempName", 50);
//
//        System.out.println("***** call getAllGrades() *****");
//        System.out.println("Krishna " + course.getAllGrades("Krishna"));
//        System.out.println("Govardhan " + course.getAllGrades("Govardhan"));
//        System.out.println("Dwarkadhish " + course.getAllGrades("Dwarkadhish"));
//        System.out.println("TempName " + course.getAllGrades("TempName"));
//
//        // List all students and their grades
//        System.out.println("***** Call listAllGrade() *****");
//        course.listAllGrades();


        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while(!exit){

            // Display menu
            System.out.println("******Course Managemet System");
            System.out.println();
            System.out.println("1. Enroll Student");
            System.out.println("2. Assign Grade");
            System.out.println("3. Get Student Grade");
            System.out.println("4. List All Grades");
            System.out.println("x. Exit");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    enrollStudent(course, scanner);
                    break;
                case "2":
                    assignGrade(course, scanner);
                    break;
                case "3":
                    getStudentGrade(course, scanner);
                    break;
                case "4":
                    listAllGrades(course);
                    break;
                case "x":
                    exit = true;
                    System.out.println("Exiting the application.");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 4 or 'x' to exit.");
            }

        }
        scanner.close();

    }

    private static void enrollStudent(Course<String, Integer> course, Scanner scanner) {
        System.out.print("Enter student name to enroll: ");
        String name = scanner.nextLine().trim();
        if (!name.isEmpty()) {
            course.enrollStudent(name);
            System.out.println("Student " + name + " enrolled successfully.");
        } else {
            System.out.println("Invalid student name.");
        }
    }

    private static void assignGrade(Course<String, Integer> course, Scanner scanner) {
        System.out.print("Enter student name to assign grade: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter grade: ");
        Integer grade;
        try {
            grade = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over
        } catch (InputMismatchException e) {
            System.out.println("Invalid grade input. Please enter a valid integer.");
            scanner.nextLine(); // Consume invalid input
            return;
        }

        if (!name.isEmpty() && grade != null) {
            course.assignGrade(name, grade);
            System.out.println("Grade " + grade + " assigned to student " + name + ".");
        } else {
            System.out.println("Invalid input.");
        }
    }

    private static void getStudentGrade(Course<String, Integer> course, Scanner scanner) {
        System.out.print("Enter student name to get grade: ");
        String name = scanner.nextLine().trim();
        if (!name.isEmpty()) {
            Integer grade = course.getAllGrades(name);
            if (grade != null) {
                System.out.println("Grade for student " + name + ": " + grade);
            } else {
                System.out.println("Student " + name + " is not enrolled or has no grade assigned.");
            }
        } else {
            System.out.println("Invalid student name.");
        }
    }

    private static void listAllGrades(Course<String, Integer> course) {
        System.out.println("Listing all students and their grades:");
        course.listAllGrades();
    }
}