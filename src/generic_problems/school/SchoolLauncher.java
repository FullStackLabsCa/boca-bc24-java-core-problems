package generic_problems.school;

import java.util.Scanner;

public class SchoolLauncher {
    public static void main(String[] args) {
        School<Object, Number> objectNumberSchool = new School<>();
        Scanner scanner =new Scanner(System.in);
        final String BOLD = "\033[1m";
        final String RESET = "\033[0m";
        System.out.println("Welcome to School enrollment System\nUse following command to perform task");
        System.out.println(BOLD+"add_course<course_name>"+RESET+"\t-->\tAdds a new course to the school.\n" +BOLD+
                "enroll_student <course_name> <id>"+RESET+"\t-->\tEnrolls a student in the specified course.\n" +BOLD+
                "assign_grade <course_name> <id> <g>"+RESET+"\t-->\tAssigns a grade to the student in the specified course.\n" +BOLD+
                "list_grades <course_name>"+RESET+"\t-->\tLists all students and their grades for the specified course.\n" +BOLD+
                "report_unique_courses"+RESET+"\t-->\tLists all unique courses offered by the school.\n" +BOLD+
                "report_unique_students"+RESET+"\t-->\tLists all unique students enrolled in the school.\n" +BOLD+
                "report_average_score <course_name>"+RESET+"\t-->\tReports the average score of the specified course.\n" +BOLD+
                "report_cumulative_average <id>"+RESET+"\t-->\tReports the cumulative average score for the specified student.\n" +BOLD+
                "unknown_command"+RESET+"\t-->\tHandles unknown commands and suggests valid options.\nPlease Enter the command");
        String keyboardInput = scanner.nextLine();
        objectNumberSchool.processCommand(keyboardInput);
    }
}