package problems.generics.course;

import java.util.Scanner;

public class CourseMainRunner {
    public static void main(String[] args) {
        Course< String, Double> course = new Course<>();
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("\nWelcome to our JAVA Course");
            System.out.println("Select the following option:");
            System.out.println("1. Enroll Student");
            System.out.println("2. Assign grade to the student");
            System.out.println("3. Retrieve specific grades");
            System.out.println("4. Show all grades");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int option = input.nextInt();
            input.nextLine();

            switch (option) {
                case 1:
                    System.out.print("Please enter Student ID: ");
                    String studentID = input.nextLine();
                    System.out.print("Please enter CGPA: ");
                    Double grade = input.nextDouble();
//                    course.enrollStudent(studentID, grade);
                    System.out.println("Student enrolled successfully.");
                    input.nextLine();
                    break;

                case 2:
                    System.out.print("Enter student ID to add or update the grade: ");
                    String case2StudentID = input.nextLine();
                    System.out.print("Enter the new grade: ");
                    Double case2Grade = input.nextDouble();
                    course.assignGrade(case2StudentID, case2Grade);
                    System.out.println("Grade assigned/updated successfully.");
                    input.nextLine();
                    break;

                case 3:
                    System.out.print("Enter student ID to retrieve the grade: ");
                    String case3StudentID = input.nextLine();
                    Double retrievedGrade = course.retrieveGrade(case3StudentID);
                    if (retrievedGrade != null) {
                        System.out.println("Grade for student " + case3StudentID + " is: " + retrievedGrade);
                    } else {
                        System.out.println("No grade found for student " + case3StudentID);
                    }
                    break;

                case 4:
                    System.out.println("All Student Grades:");
                    course.listAllGrades();
                    break;

                case 5:
                    System.out.println("Exiting program.");
                    input.close();
                    return;

                default:
                    System.out.println("Please enter an option from 1 to 5 only.");
                    break;
            }
        }
    }
}
