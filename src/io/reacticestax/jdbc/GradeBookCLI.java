package io.reacticestax.jdbc;

import java.util.Scanner;

public class GradeBookCLI {

        private static final GradeBook grades = new GradeBook();

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Welcome to the Gradebook");

            while (true) {
                System.out.print("> ");
                String input = scanner.nextLine();
                String[] command = input.split(" ");

                switch (command[0]) {
                    case "add_grade":
                        grades.addGrade(Double.valueOf(command[1]));
                        break;
//                    case "enroll_student":
//                        school.enrollStudent(Integer.parseInt(command[2]), command[1], command[3]);
//                        break;
//                    case "assign_grade":
//                        school.assignGrade(Integer.parseInt(command[2]), command[1], Double.parseDouble(command[3]));
//                        break;
//                    case "exit":
//                        System.out.println("Exiting...");
//                        return;
                    default:
                        System.out.println("Unknown command");
                }
            }
        }
    }

