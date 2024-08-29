package problems.generics;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Course<S, G extends Number> {
    private final Map<S, G> students;

    public Course() {
        this.students = new HashMap<>();
    }

    public void enrollStudent(S studentIdentifier) {
        this.students.put(studentIdentifier, null);
    }

    public void assignGrade(S studentIdentifier, G grade) {
        if (this.students.containsKey(studentIdentifier)) this.students.replace(studentIdentifier, grade);
        else System.out.println("Student does not exist.");
    }

    public Optional<G> getGrade(S studentIdentifier) {
        return Optional.ofNullable(this.students.get(studentIdentifier));
    }

    public Map<S, G> getAllGrades() {
        return this.students;
    }

    public void listAllGrades() {
        System.out.println(this.students);
        ;
    }

    public boolean isStudentEnrolled(S student) {
        return this.students.containsKey(student);
    }

    public static void main(String[] args) {
        Course<String, Double> course = new Course<>();

        course.enrollStudent("John");
        course.enrollStudent("Jason");
        course.enrollStudent("Jack");

        course.assignGrade("John", 4.0);
        course.assignGrade("Jason", 3.8);
        course.assignGrade("Jack", 3.9);

        System.out.println("Grade of John: " + course.getGrade("John"));
        System.out.println("Grade of Jason: " + course.getGrade("Jason"));
        System.out.println("Grade of Jack: " + course.getGrade("Jack"));

        System.out.println("All grades: " + course.getAllGrades());
    }
}
