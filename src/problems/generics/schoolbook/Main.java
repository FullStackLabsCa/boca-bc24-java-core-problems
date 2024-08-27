package problems.generics.schoolbook;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Imaginary School.");

        final String ADD_COURSE = "add_course";
        final String ENROLL_STUDENT = "enroll_student";
        final String ASSIGN_GRADE = "assign_grade";
        final String LIST_GRADES = "list_grades";
        final String LIST_COURSES = "list_courses";
        final String REPORT_UNIQUE_COURSES = "report_unique_courses";
        final String REPORT_UNIQUE_STUDENTS = "report_unique_students";
        final String REPORT_AVERAGE_SCORE = "report_average_score";
        final String REPORT_CUMULATIVE_AVERAGE = "report_cumulative_average";
        final String EXIT = "exit";
        final List<String> listOfOperations = Arrays.asList("add_course", "enroll_student", "assign_grade", "list_grades",
                "list_courses", "report_unique_courses", "report_unique_students", "report_average_score",
                "report_cumulative_average", "exit");
        String[] command;
        School<Integer, Double> school = new School<>();
        Course<Integer, Double> course;

        do {
            System.out.println("Please enter a command from the below list:\n");
            for (int i = 1; i <= listOfOperations.size(); i++) {
                System.out.println(i + ". " + listOfOperations.get(i - 1));
            }
            command = scanner.nextLine().trim().split(" ");
            String courseIdentifier = "";
            int studentIdentifier = 0;
            double grade = 0.0;
            if (listOfOperations.contains(command[0])) {
                switch (command[0]) {
                    case ADD_COURSE:
                        courseIdentifier = command[1];
                        school.addCourse(courseIdentifier, new Course<>());
                        break;
                    case ENROLL_STUDENT:
                        courseIdentifier = command[1];
                        studentIdentifier = Integer.parseInt(command[2]);
                        course = school.getCourse(courseIdentifier);
                        course.enrollStudent(studentIdentifier);
                        break;
                    case ASSIGN_GRADE:
                        courseIdentifier = command[1];
                        studentIdentifier = Integer.parseInt(command[2]);
                        grade = Double.parseDouble(command[3]);
                        course = school.getCourse(courseIdentifier);
                        course.assignGrade(studentIdentifier, grade);
                        System.out.println(school.getSchoolToCourses());
                        break;
                    case LIST_GRADES:
                        courseIdentifier = command[1];
                        course = school.listGrades(courseIdentifier);
                        System.out.println(course.getStudentToGrade());
                        break;
                    case LIST_COURSES:
                        System.out.println(school.getSchoolToCourses().keySet());
                        break;
                    case REPORT_UNIQUE_COURSES:
                        break;
                    case REPORT_UNIQUE_STUDENTS:
                        break;
                    case REPORT_AVERAGE_SCORE:
                        break;
                    case REPORT_CUMULATIVE_AVERAGE:
                        break;
                }
            } else
                System.out.println("Error: Unknown command "+ command[0] +" . Please use one of the following commands: " +
                        "add_course, enroll_student, assign_grade, list_grades, list_courses, report_unique_courses, " +
                        "report_unique_students, report_average_score, report_cumulative_average.");
        } while (!command[0].equals(listOfOperations.get(listOfOperations.size() - 1)));
    }
}
