package problems.generics.oldp6_Generic_School_CommandLineDriven;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Course<S, G> {

    private String courseName;
    private GradeBook<S, G> gradeBook;
    private Set<S> enrolledStudents;

    // Constructor
    public Course(String courseName, GradeBook<S, G> gradeBook) {
        this.courseName = courseName;
        this.gradeBook = gradeBook;  // Use provided GradeBook instance
        this.enrolledStudents = new HashSet<>();
    }

    // Getter for courseName
    public String getCourseName() {
        return courseName;
    }

    // Enrolls a student in the course
    public void enrollStudent(S studentID) {
        enrolledStudents.add(studentID);
        gradeBook.addStudent(studentID);  // Add student to the gradebook
    }

    // Assigns a grade to a student
    public void assignGrade(S studentID, G grade) {
        if (!enrolledStudents.contains(studentID)) {
            throw new IllegalArgumentException("Student '" + studentID + "' is not enrolled in course '" + courseName + "'.");
        }
        gradeBook.setGrade(studentID, grade);
    }

    // Lists all students and their grades
    public Map<S, G> listGrades() {
        return gradeBook.getGrades();
    }
}
