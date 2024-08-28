package problems.generic;

import java.util.*;

@SuppressWarnings("java:S106")
public class School<S, G extends Number> {
    Map<S, Course<S, G>> mapOfCourses = new HashMap<>();

    public Map<S, Course<S, G>> addingCourse(S courseName) {
        mapOfCourses.put(courseName, new Course<>());
        System.out.println("Course '" + courseName + "' added.");
        return mapOfCourses;
    }

    public void enrollStudentToCourse(S courseName, S studentName) {
        if (!mapOfCourses.containsKey(courseName)) {
            System.out.println("Error: Cannot enroll student. Course '" + courseName + "' does not exist.");
            return;
        }
        mapOfCourses.get(courseName).enrollStudent(studentName);
        System.out.println("Student '" + studentName + "' enrolled in course '" + courseName + "'.");
    }

    public void assignGradesToStudent(String courseName, S studentName, G grade) {
        if (!mapOfCourses.get(courseName).isStudentEnrolled(studentName)) {
            System.out.println("Error: Cannot assign grade. Student '" + studentName + "' is not enrolled in course '" + courseName + "'.");
            return;
        }
        mapOfCourses.get(courseName).assignGrade(studentName, grade);

        System.out.println("Grade '" + grade + "' assigned to student '" + studentName + "' in course '" + courseName + "'.");
    }

    public void listGrades(S courseName) {
        mapOfCourses.get(courseName).listAllGrades();
    }


    public void listAllCourses() {
        Set<S> key = mapOfCourses.keySet();
        System.out.println("Courses offered:");
        key.forEach(k -> System.out.println(k));
    }

    public void processCommand(String operation) {
        String[] array = operation.split(" ");
//        System.out.println("array = " + Arrays.toString(array));
        switch (array[0]) {
            case "add_course":
                addingCourse((S) array[1]);
                break;
            case "list_courses":
                listAllCourses();
                break;
            case "enroll_student":
                enrollStudentToCourse((S) array[1], (S) array[2]);
                break;
            case "assign_grade":
                assignGradesToStudent(array[1], (S) array[2], (G) Double.valueOf(array[3]));
                break;
            case "list_grades":
                listGrades((S) array[1]);
                break;
            case "report_unique_courses":
                listAllCourses();
                break;
            case "Unique students enrolled:":
//                co
                break;
            default:
                System.out.println("Error: Unknown command 'unknown_command'. Please use a valid command.");

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
        stringIntegerCourses.assignGradesToStudent("Math101", "Gaurav", 97.5);

        //list Grades
        stringIntegerCourses.listGrades("Math101");

        //List courses
        stringIntegerCourses.listAllCourses();

    }
}


