package school;

import course.Course;

import java.util.Scanner;

public class SchoolStart {
    public static void main(String[] args) {

        Course<String,Number> math = new Course<>();
        Course<String,Number> phy = new Course<>();


        math.enrollStudent("Abhay");
        phy.enrollStudent("Mann");

        math.printStudentGrade("Abhay");
        phy.printStudentGrade("Mann");
        School<String,Number> school = new School<>();


        System.out.println(school.add_Course("math-fall-2025"));

        System.out.println(school.enroll_Student("math-fall-2025", math));
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the course name:- ");
        String courseName = scanner.nextLine();

        school.processCommand(courseName);



    }
}
