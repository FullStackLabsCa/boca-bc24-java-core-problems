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
            System.out.println("2. Add Student");
            System.out.println("3. Assign grade");
            System.out.println("4. List grades");
            System.out.println("5. List courses");
            System.out.println("6. Exit");
            System.out.println("Please choose operation to perform");
            int inputOption = input.nextInt();
            input.nextLine();

            switch(inputOption) {
                case 1:
                    System.out.println("How many courses do you have");
                    int courseSetSize = input.nextInt();
                    input.nextLine();

                    for (int i = 0; i < courseSetSize; i++) {
                        System.out.print("Enter course " + (i + 1) + ":");
                        String courseName = input.nextLine();
                        school.addCourse(courseName);
                    }

                    System.out.println("Course Added Successfully");
                    System.out.println();
                    break;
                case 2:
                    String studentName = "Rushi";

                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    System.out.println("List of courses : ");
                    for (String course : school.getCourses()) {
                        System.out.println("- "+course);
                    }
                    System.out.println();
                    break;
                case 6:
                    System.out.println("Exiting program.");
                    input.close();
                    return;

                default:
                    System.out.println("Please choose option from 1 to 5 only");
                    break;

            }
        }
    }
}
