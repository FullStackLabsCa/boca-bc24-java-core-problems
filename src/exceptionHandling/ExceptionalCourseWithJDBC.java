package exceptionHandling;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ExceptionalCourseWithJDBC<S, G extends Number> {

    private final Map<S, G> students = new HashMap<>();
//    S studentIdentifier ;
//    G grade;

    public static class StudentNotEnrolledException extends RuntimeException {
        public StudentNotEnrolledException(String message) {
            super(message);
        }
    }


    //methods for Course class

    public void enrollStudent(S studentIdentifier) {
        if (studentIdentifier == null) {
            throw new NullPointerException("studentIdentifier cannot be null");
        }
        this.students.put(studentIdentifier, null);
    }

    public boolean isStudentEnrolled(S studentIdentifier) {
        if (studentIdentifier == null) {
            return false;
        }
        return students.containsKey(studentIdentifier);
    }


    public void assignGrade(S studentIdentifier, G grade) {
        if (studentIdentifier == null) {
            throw new NullPointerException("Student identifier cannot be null");
        }
        if (grade == null) {
            throw new IllegalArgumentException("Grade cannot be null");
        }
        if (!this.students.containsKey(studentIdentifier)) {
            throw new StudentNotEnrolledException("Student is not enrolled in the course");
        }
        if (this.students.containsKey(studentIdentifier)) {
            this.students.put(studentIdentifier, grade);
        }
    }

    public Optional<G> getGrade(S studentIdentifier) {
        return Optional.ofNullable(students.get(studentIdentifier));
    }

    public Map<S, G> getAllGrades() {
        return students;
    }


    public void listAllGrades() {
        System.out.println(this.students);
        ;
    }


    public static void main(String[] args) {

        ExceptionalCourseWithJDBC<Integer, Double> course = new ExceptionalCourseWithJDBC<>();
        try {
            course.enrollStudent(12345);  // Valid enrollment
            course.enrollStudent(null);   // This will throw a NullPointerException
        } catch (NullPointerException e) {
            System.err.println("Failed to enroll student: " + e.getMessage());
        }

        // Test cases
        course.enrollStudent(12345);
        course.enrollStudent(23456);

        course.assignGrade(12345, 8.0);
        course.assignGrade(23456, 9.8);

        System.out.println(course.getGrade(12345));
        System.out.println(course.getGrade(23456));

        System.out.println("All grades: " + course.getAllGrades());
    }


}

