package problems.generics.problem6_school;

import java.util.Scanner;

public class SchoolLauncher {
    public static void main(String[] args) {
        Scanner userCommandLineScanner = new Scanner(System.in);
        System.out.println("Use the above mentioned command to do the any operations: ");
        String userInput;
             userInput = userCommandLineScanner.nextLine();
               School<String, Double> school = new School<>();
               school.processCommand(userInput);


//        School<String, Double> school = new School<>();
//        System.out.println("********************");
//        school.addCourse("Java");
//        System.out.println("+++++++++++++++++++");
//        school.enrollStudent("Java", "Karan");
//        System.out.println("================================");
//        school.assignGrade("Java", "Karan", 95.02d);
//        school.addCourse("Spring");
//        school.enrollStudent("Spring", "Karan");
//        school.addCourse("JAva");
//        school.enrollStudent("JAva", "Gurpreet");
//        school.listCourse();
//        System.out.println("=================================");
//        school.listGrade("Java");
//        System.out.println("_____________________");
//        school.listUniqueStudent();
    }
}
