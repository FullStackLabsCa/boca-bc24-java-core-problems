package problems.generics.p4_Generic_Course_Class;

import java.util.*;

public class Course<S, G> {

    private Map<S, G> studentToGradeMap = new LinkedHashMap<>();

    // method: for checking if student is enrolled or not
    public boolean isStudentEnrolled(S i) {
        if (studentToGradeMap.containsKey(i)){
            return true;
        }else {
            return false;
        }
    }

    public void enrollStudent(S identifier) {

        if (!isStudentEnrolled(identifier)) {
            studentToGradeMap.put(identifier, null); // adding the student into map with null grade
        } else {
            System.out.println("Student is alreday enrolled");
        }
    }

    public void assignGrade(S identifier, G grade) {

        // check if student is already enrolled
        if (isStudentEnrolled(identifier)) {
            studentToGradeMap.put(identifier, grade); // Assign or update student grade
        } else {
            System.out.println("Student is not enrolled");
        }

    }

    public List<Map.Entry<S, G>> getAllGrades() {
        return new ArrayList<>(studentToGradeMap.entrySet());
    }

    public G getAllGrades(S identifier) {

        // check if student is already enrolled
        if (isStudentEnrolled(identifier)) {
            return studentToGradeMap.get(identifier); // return the student's grade
        } else {
            System.out.println("Student is not enrolled");
            return null;
        }
    }

    public void listAllGrades() {

        // loop through the map and print each student's identifier and geade
        for (Map.Entry<S, G> entry : studentToGradeMap.entrySet()) {
            System.out.println("Student ID: " + entry.getKey() + " Grade: " + entry.getValue());
        }
    }




    /*
    Summary of Implementation:
    enrollStudent(S identifier): Enrolls a student in the course.
    assignGrade(S identifier, G grade): Assigns or updates the grade for a student.
    getGrade(S identifier): Retrieves the grade for a student.
    listAllGrades(): Lists all students and their grades.
     */


//    public static void main(String[] args) {
//        // Create a Course instance with String as student identifier and Integer as grade
//        Course<String, Integer> course = new Course<>();
//
//        // Enroll some students
//        course.enrollStudent("Alice");
//        course.enrollStudent("Bob");
//        course.enrollStudent("Charlie");
//
//        // Assign grades to the students
//        course.assignGrade("Alice", 85);
//        course.assignGrade("Bob", 90);
//        course.assignGrade("Charlie", 78);
//
//        // Attempt to enroll a student who is already enrolled
//        course.enrollStudent("Alice");
//
//        // Retrieve and print grades
//        System.out.println("Grade for Alice: " + course.getAllGrades("Alice"));
//        System.out.println("Grade for Bob: " + course.getAllGrades("Bob"));
//
//        // List all students and their grades
//        System.out.println("Listing all grades:");
//        course.listAllGrades();
//    }
}
