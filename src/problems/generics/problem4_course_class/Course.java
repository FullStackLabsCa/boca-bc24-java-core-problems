package problems.generics.problem4_course_class;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Course<S, G extends Number> {
    S student;
    G grade;
    Map<S, G> studentGradeCourseMap;

    public Course() {
        studentGradeCourseMap = new HashMap<>();
    }

    public void enrollStudent(S student) {
        studentGradeCourseMap.put(student, null);
    }

    public void assignGrade(S student, G grade) {
        if (studentGradeCourseMap.containsKey(student) && !studentGradeCourseMap.containsKey(null)) {
           studentGradeCourseMap.put(student, grade);
        }else {
            System.out.println("Student Doesn't exist");
        }
    }

    public boolean isStudentEnrolled(S student) {
        return studentGradeCourseMap.containsKey(student);
    }

    public Optional<G> getGrade(S student) {
        if (studentGradeCourseMap.containsKey(student)) {
            return Optional.of(studentGradeCourseMap.get(student));
        } else {
            return Optional.empty();
        }
    }

    public Map<S, G> getAllGrades() {
        return new HashMap<>(studentGradeCourseMap);
    }

    public void listAllGrades() {
        System.out.println(studentGradeCourseMap.values());
    }

//    public static void main(String[] args) {
//        Course course = new Course();
//        course.enrollStudent(12345);
//        course.assignGrade(12345, 95.0);
//        course.enrollStudent(67890);
//        course.assignGrade(67890, 96.2);
//        course.enrollStudent("Karan");
//        course.assignGrade("Karan", 95);
//
//
//        course.listAllGrades();
//    }
}
