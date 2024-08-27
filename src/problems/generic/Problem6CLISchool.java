package problems.generic;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

@SuppressWarnings("java:S106")
public class Problem6CLISchool {
    public static class School<S, G extends Number> {
        Map<S, Problem4Course.Course<S, G>> mapOfCourses = new HashMap<>();
        HashMap<S, G> mapOfStudents = new HashMap<>();
        Problem4Course.Course<S, G> addedStudent = new Problem4Course.Course<>();


        public Map<S, Problem4Course.Course<S, G>> addingCourse(S courseName) {
            mapOfCourses.put(courseName, null);
            return mapOfCourses;
        }

        public void enrollStudentToCourse(S courseName, S studentName) {
            mapOfStudents.put(studentName, null);
            this.mapOfCourses.put(courseName, addedStudent);
        }

        public void assignGradesToStudent(S courseName, S studentName, G grade) {
            mapOfStudents.put(studentName, grade);
            this.mapOfCourses.put(courseName, addedStudent);
        }

        public void listGrades(S courseName) {
            Problem4Course.Course<S, G> sgCourse = mapOfCourses.get(courseName);
            System.out.println("listGrades = " + sgCourse.toString());
        }

        public void listAllCourses() {
            Set<S> key = mapOfCourses.keySet();
            System.out.println("listAllCourses = " + key);
        }
    }

    public static void main(String[] args) {
        School<String, Integer> stringIntegerCourses = new School<>();
        Scanner scanner = new Scanner(System.in);
        //Add Course
//        System.out.println("Please enter a value for course");
//        String inputValue = scanner.nextLine().trim();
//        stringIntegerCourses.addingCourse(inputValue);

        //Enroll Student
//        System.out.println("Please enter a course name and Student name");
//        String[] splitCourseNameStudentName = scanner.nextLine().split(" ");
//        stringIntegerCourses.enrollStudentToCourse(splitCourseNameStudentName[0], splitCourseNameStudentName[1]);

        //Assign Grade to Student
//        System.out.println("Please enter a course name ,student name and grade");
//        String[] splitCourseNameStudentNameGrade = scanner.nextLine().split(" ");
//        stringIntegerCourses.assignGradesToStudent(splitCourseNameStudentNameGrade[0], splitCourseNameStudentNameGrade[1], Integer.valueOf(splitCourseNameStudentNameGrade[2]));
        stringIntegerCourses.assignGradesToStudent("Math101", "Gaurav", 97);

        //list Grades
        stringIntegerCourses.listGrades("Math101");

        //List courses
        stringIntegerCourses.listAllCourses();

    }
}
