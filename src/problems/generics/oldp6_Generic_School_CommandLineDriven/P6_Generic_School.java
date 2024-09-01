package problems.generics.oldp6_Generic_School_CommandLineDriven;

import java.util.Scanner;

public class P6_Generic_School {

    public static void main(String[] args) {

        // Create an instance of the school
        School<String, Double> school = new School<>();

        // Create a Scanner to read user input from the command line
        Scanner scanner = new Scanner(System.in);

        // Command loop
        while (true){
            showCommands();
            System.out.println("> ");
            String command = scanner.nextLine().trim();

            // Split the command into parts
            String[] parts = command.split("\\s+");
            String action = parts[0];

            // Handle command
            switch (action) {
                case "1":
                    if (parts.length != 2) {
                        System.out.println("Error: Invalid number of arguments for 'add_course'.");
                    } else {
                        school.addCourse(parts[1]);
                    }
                    break;

                case "enroll_student":
                    if (parts.length != 3) {
                        System.out.println("Error: Invalid number of arguments for 'enroll_student'.");
                    } else {
                        String courseName = parts[1];
                        String studentId = parts[2];
                        school.enrollStudent(courseName, studentId);
                    }
                    break;

                case "assign_grade":
                    if (parts.length != 4) {
                        System.out.println("Error: Invalid number of arguments for 'assign_grade'.");
                    } else {
                        String courseName = parts[1];
                        String studentId = parts[2];
                        try {
                            Double grade = Double.parseDouble(parts[3]);
                            school.assignGrade(courseName, studentId, grade);
                        } catch (NumberFormatException e) {
                            System.out.println("Error: Invalid grade format.");
                        }
                    }
                    break;

                case "list_grades":
                    if (parts.length != 2) {
                        System.out.println("Error: Invalid number of arguments for 'list_grades'.");
                    } else {
                        school.listGrades(parts[1]);
                    }
                    break;

                case "list_courses":
                    school.listCourses();
                    break;

                case "report_unique_courses":
                    school.reportUniqueCourses();
                    break;

                case "report_unique_students":
                    school.reportUniqueStudents();
                    break;

                case "report_average_score":
                    if (parts.length != 2) {
                        System.out.println("Error: Invalid number of arguments for 'report_average_score'.");
                    } else {
                        school.reportAverageScore(parts[1]);
                    }
                    break;

                case "report_cumulative_average":
                    if (parts.length != 2) {
                        System.out.println("Error: Invalid number of arguments for 'report_cumulative_average'.");
                    } else {
                        String studentId = parts[1];
                        school.reportCumulativeAverage(studentId);
                    }
                    break;

                case "exit":
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Error: Unknown command '" + action + "'.");
                    System.out.println("Available commands: add_course, enroll_student, assign_grade, list_grades, list_courses, report_unique_courses, report_unique_students, report_average_score, report_cumulative_average, exit.");
            }


        }
    }

    // Method to display all available commands to the user
    private static void showCommands() {
        System.out.println("Available Commands:");
        System.out.println("1. add_course <course_name> - Adds a new course");
        System.out.println("2. enroll_student <course_name> <student_id> - Enrolls a student in a specified course");
        System.out.println("3. assign_grade <course_name> <student_id> <grade> - Assigns a grade to a student in a course");
        System.out.println("4. list_courses - Lists all courses in the school");
        System.out.println("5. list_grades <course_name> - Lists all students and their grades for a specified course");
        System.out.println("6. report_unique_courses - Reports all unique courses offered by the school");
        System.out.println("7. report_unique_students - Reports all unique students enrolled in the school");
        System.out.println("8. report_average_score <course_name> - Reports the average score of a specified course");
        System.out.println("9. report_cumulative_average <student_id> - Reports the cumulative average score of a student");
        System.out.println("10. exit - Exit the program");
    }


}
