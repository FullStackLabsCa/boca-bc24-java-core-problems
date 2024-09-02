//package problems.generics.schoolproblem;
//
//import java.util.HashSet;
//import java.util.Scanner;
//import java.util.Set;
//
//public class SchoolMainRunner {
//    public static void main(String[] args) {
//        Scanner input = new Scanner(System.in);
//        School<String, String, Integer> school = new School<>();
//
//        while (true) {
//            System.out.println("1. add_course");
//            System.out.println("2. enroll_student");
//            System.out.println("3. assign_grade");
//            System.out.println("4. list_grades");
//            System.out.println("5. list_courses");
//            System.out.println("6. report_unique_courses");
//            System.out.println("7. report_unique_students");
//            System.out.println("8. report_average_score");
//            System.out.println("9. report_cumulative_average");
//            System.out.println("10.exit");
//            System.out.println("Please choose operation to perform");
//            String inputCommand = input.nextLine();
//            String[] splitCommand = inputCommand.split(" ");
//
//            input.nextLine();
//
//            switch(splitCommand[0]) {
//                case "add_course":
//                    System.out.print("Enter course name to add: ");
//                    school.processCommand(inputCommand);
//                    String courseName = input.nextLine();
//                    school.addCourse(courseName);
//                    break;
//
//                case "enroll_student":
//                    System.out.print("Enter course name: ");
//                    courseName = input.nextLine();
//                    System.out.print("Enter student ID to enroll: ");
//                    String studentId = input.nextLine();
//                    school.enrollStudent(courseName, studentId);
//                    break;
//
//                case "assign_grade":
//                    System.out.print("Enter course name: ");
//                    courseName = input.nextLine();
//                    System.out.print("Enter student ID: ");
//                    studentId = input.nextLine();
//                    System.out.print("Enter grade to assign: ");
//                    int grade = input.nextInt();
//                    input.nextLine();
//                    school.assignGrade(courseName, studentId, grade);
//                    break;
//                case "list_grades":
//                    System.out.print("Enter course name to find the grades: ");
//                    courseName = input.nextLine();
//                    school.listGrades(courseName);
//                case "list_courses":
//                    school.listCourses();
//                    break;
//                case "report_unique_courses":
//                    school.reportUniqueCourses();
//                    break;
//                case "report_unique_students":
//                    school.reportUniqueStudents();
//                    break;
//                case "report_average_score":
//                    System.out.print("Enter course name to report average score: ");
//                    courseName = input.nextLine();
//                    school.reportAverageScore(courseName);
//                    break;
//                case "report_cumulative_average":
//                    System.out.print("Enter student ID to report cumulative average: ");
//                    studentId = input.nextLine();
//                    school.reportCumulativeAverage(studentId);
//                    break;
//                case "exit":
//                    System.out.println("Exiting program.");
//                    input.close();
//                    return;
//                default:
//                    System.out.println("Error: Unknown command. Please use one of the available commands.");
//                    break;
//            }
//        }
//    }
//}
