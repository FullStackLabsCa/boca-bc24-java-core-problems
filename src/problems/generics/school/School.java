package generics.school;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class School {
    // Check whether string is valid or not
    public static boolean isValidString(String inputStr) {
        List<String> commandList = Arrays.asList("add_course", "remove_course", "list_courses", "enroll_student", "assign_grade", "list_grades",
                "report_unique_courses", "report_unique_students", "report_average_score", "report_cumulative_average");

        if (inputStr.equalsIgnoreCase("unknown_command")) {
            System.out.println("Error: Unknown command 'unknown_command'. Please use one of the following commands: " + commandList);
            return false;
        }
        String [] inputs = inputStr.trim().split(" ");

        if (inputs.length < 2 || (inputs.length > 1  && !commandList.contains(inputs[0]))) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String userInputString;
        Course<String, Integer> course = new Course<>();
        do {
            System.out.println("Enter an input string: ");
            userInputString = scanner.nextLine();

            Boolean isValidInput = isValidString(userInputString);

            String [] inputs = userInputString.trim().split(" ");
            if (!isValidInput) {
                return;
            }
            switch (inputs[0]) {
                case "add_course":
                    course.addCourse(inputs[1]);
                    break;
                case "remove_course":
                    course.removeCourse(inputs[1]);
                    break;
                case "list_courses":
                    course.getCourseList();
                    break;
                case "enroll_student":
                    course.enrollStudent(inputs[1], inputs[2]);
                    break;
                case "assign_grade":
                    course.assignGrade(inputs[1], inputs[2], Integer.valueOf(inputs[3]));
                    break;
                case "list_grades":
                    course.listGrades(inputs[1]);
                    break;
                case "report_unique_courses":
                    course.getCourseList();
//                    course.printMap();
                    break;
                case "report_unique_students":
                    course.reportUniqueStudents();
                    break;
                case "report_average_score":
                    course.reportAverageScore();
                    break;
                case "report_cumulative_average":
                    course.reportCumulativeAverage();
                    break;
                default:
                    break;
            }
        } while (!userInputString.equalsIgnoreCase("x"));


//        course1.addCourse("test");
//        course1.addCourse("test1");
//
//        System.out.println("Before removing course=== " + course1.getCourseList());
//        course1.removeCourse("test3");
//        System.out.println("After removing course=== " + course1.getCourseList());



    }
}
