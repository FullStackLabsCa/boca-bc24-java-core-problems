//package problems.jdbc.students;
//
//import java.util.Scanner;
//
//public class StudentCLI {
//    private static final Student student = new Student();
//
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Welcome to the Student Management System");
//
//        System.out.print("> ");
//        String input = scanner.nextLine();
//        String[] command = input.split(" ");
//
//        switch (command[0]) {
//            case "add_student":
//                student.addStudent(command[1]);
//                break;
//            case "remove_student":
//                student.removeStudent(Integer.parseInt(command[1]));
//                break;
//            case "update_student":
//                student.updateStudent(Integer.parseInt(command[1]), command[2]);
//                break;
//            case "get_student":
//                student.getStudents();
//                break;
//            default:
//                System.out.println("Unknown command");
//        }
//    }
//}
