package problems.generics.p4_Generic_Course_Class;

import java.util.HashMap;
import java.util.Map;

public class Course<S, G> {

    private Map<S, G> studentToGradeMap = new HashMap<>();

    public void enrollStudent(S identifier) {

        // check if student is already enrolled
        if (!studentToGradeMap.containsKey(identifier)) {
            studentToGradeMap.put(identifier, null); // adding the student into map with null grade
        } else {
            System.out.println("Student is alreday enrolled");
        }
    }

    public void assignGrade(S identifier, G grade) {

        // check if student is already enrolled
        if (studentToGradeMap.containsKey(identifier)) {
            studentToGradeMap.put(identifier, grade); // Assign or update student grade
        } else {
            System.out.println("Student is not enrolled");
        }
    }

    public G getGrade(S identifier) {

        // check if student is already enrolled
        if (studentToGradeMap.containsKey(identifier)) {
            return studentToGradeMap.get(identifier); // return the student's grade
        } else {
            System.out.println("Student is not enrolled");
            return null;
        }
    }

    public void listAllGrade() {

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

}
