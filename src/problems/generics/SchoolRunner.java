package problems.generics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SchoolRunner {
    public static void main(String[] args) {

//        school.add_course("Math_fall_2024");
//        school.enroll_student("Math_fall_2024", "Samy");
//        school.enroll_student("Math_fall_2024", "Muruga");
//        school.assign_grade("Math_fall_2024", "Samy", 9);
//        school.assign_grade("Math_fall_2024", "Muruga", 10);
//        school.add_course("Science_fall_2024");
//        school.enroll_student("Science_fall_2024", "Iyyapa");
//        school.enroll_student("Science_fall_2024", "Muruga");

//        school.report_unique_courses();
//        school.report_unique_students();
//        school.report_average_score("Math_fall_2024");
//        school.report_average_score("Science_fall_2024");
//        System.out.println("=======");
//        school.report_cumulative_average("Muruga");

        List<String> allowedCommands = new ArrayList<>();
        allowedCommands.add("add_course");
        allowedCommands.add("list_courses");
        allowedCommands.add("enroll_student");
        allowedCommands.add("assign_grade");
        allowedCommands.add("list_grades");
        allowedCommands.add("report_unique_courses");
        allowedCommands.add("report_unique_students");
        allowedCommands.add("report_average_score");
        allowedCommands.add("report_cumulative_average");
        School<String, Double> school = new School<>();
        Scanner scanner = new Scanner(System.in);
        String input;
        //school.assign_grade("Science_fall_2024", "Muruga", 8);
        while (true) {
            System.out.println("Allowed commands: " + allowedCommands);
            System.out.println("Please enter a command from above list  (x/X to exit):");
            input = scanner.nextLine();
            input = input.toLowerCase().trim();
            if(input.equals("x")){
                return;
            }
            String[] inputArray = input.split(" ");
            if (inputArray.length > 4) {
                System.out.println("Entered invalid number of arguments");
                continue;
            } else if (!allowedCommands.contains(inputArray[0])) {
                System.out.println("Error: Unknown command 'unknown_command'. Please use a valid command.");
                continue;
            }
            switch (inputArray[0]) {
                case "add_course":
                    if (inputArray.length != 2) {
                        System.out.println("Entered invalid number of arguments, please enter valid command");
                    } else {
                        System.out.println(inputArray[1]);
                        school.add_course(inputArray[1]);
                    }
                    break;
                case "list_courses":
                    if (inputArray.length != 1) {
                        System.out.println("Entered invalid number of arguments, please enter valid command");
                    } else {
                        school.list_courses();
                    }
                    break;
                case "enroll_student":
                    if (inputArray.length != 3) {
                        System.out.println("Entered invalid number of arguments, please enter valid command");
                    } else {
                        school.enroll_student(inputArray[1], inputArray[2]);
                    }
                    break;
                case "assign_grade":
                    if (inputArray.length != 4) {
                        System.out.println("Entered invalid number of arguments, please enter valid command");
                    } else {
                       school.assign_grade(inputArray[1], inputArray[2],Double.parseDouble(inputArray[3]));
                    }
                    break;
                case "list_grades":
                    if (inputArray.length != 2) {
                        System.out.println("Entered invalid number of arguments, please enter valid command");
                    } else {
                        school.list_grades(inputArray[1]);
                    }
                    break;
                case "report_unique_courses":
                    if (inputArray.length != 1) {
                        System.out.println("Entered invalid number of arguments, please enter valid command");
                    } else {
                        school.report_unique_courses();
                    }
                    break;
                case "report_unique_students":
                    if (inputArray.length != 1) {
                        System.out.println("Entered invalid number of arguments, please enter valid command");
                    } else {
                        school.report_unique_students();
                    }
                    break;
                case "report_average_score":
                    if (inputArray.length != 2) {
                        System.out.println("Entered invalid number of arguments, please enter valid command");
                    } else {
                        school.report_average_score(inputArray[1]);
                    }
                    break;
                case "report_cumulative_average":
                    if (inputArray.length != 2) {
                        System.out.println("Entered invalid number of arguments, please enter valid command");
                    } else {
                        school.report_cumulative_average(inputArray[1]);
                    }
                    break;
            }
        }
    }
}
