package problems.jdbc.courses;

import java.util.Scanner;

public class CourseCLI {
    private static final Course course = new Course();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Course Management System");

            System.out.print("> ");
            String input = scanner.nextLine();
            String[] command = input.split(" ");

            switch (command[0]) {
                case "add_course":
                    course.addCourse(command[1]);
                    break;
                case "remove_course":
                    course.removeCourse(Integer.parseInt(command[1]));
                    break;
                case "update_course":
                    course.updateCourse(Integer.parseInt(command[1]), command[2]);
                    break;
                case "get_course":
                    course.getCourses();
                    break;
                default:
                    System.out.println("Unknown command");
            }
        }
    }
