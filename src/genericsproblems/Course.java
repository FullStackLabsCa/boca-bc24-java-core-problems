package genericsproblems;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Course<S, G extends Number> {

    private Map<S, G> studentToGrade = new LinkedHashMap<>();

    // Method to enroll a student with a unique identifier
    public void enrollStudent(S studentID) {
        studentToGrade.put(studentID, null);
    }

    // Method to check if a student is enrolled
    public boolean isStudentEnrolled(S studentID) {
        return studentToGrade.containsKey(studentID);
    }

    // Method to assign or update the grade for a specific student
    public void assignGrade(S studentID, G grade) {
        if (isStudentEnrolled(studentID)) {
            studentToGrade.put(studentID, grade);
        } else {
            System.out.println("Student with ID " + studentID + " is not enrolled.");
        }
    }

    // Method to retrieve the grade for a specific student
    public G getGrade(S studentID) {
        return studentToGrade.get(studentID);
    }

    // Method to retrieve all students and their grades
    public Map<S, G> getAllGrades() {
        return new LinkedHashMap<>(studentToGrade); // Return a copy to avoid external modification
    }


    public void listAllGrades() {
        getAllGrades();
    }

    public static void main(String[] args) {
        Course<String, Integer> studentCourse = new Course<>();

        // Enroll students
        studentCourse.enrollStudent("Gagan");
        studentCourse.enrollStudent("Nippa");

        // Assign grades to students
        studentCourse.assignGrade("Gagan", 56);
        studentCourse.assignGrade("Nippa", 79);

        // Retrieve and display a specific student's grade
        Integer studentGrade = studentCourse.getGrade("Gagan");
        System.out.println("Student Grade: " + studentGrade);

        // Retrieve and display all grades
        Map<String, Integer> allGrades = studentCourse.getAllGrades();
        System.out.println("All Grades: " + allGrades);

        // Check if a student is enrolled
        boolean isEnrolled = studentCourse.isStudentEnrolled("Gagan");
        System.out.println("Is Student Enrolled? " + isEnrolled);
    }

}

