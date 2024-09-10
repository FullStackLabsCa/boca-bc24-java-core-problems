package problems.database_problems.my_school;

import java.util.*;

public class Course<S, G extends Number> {
    //    S studentId;
    G grade;
    Map<S, G> studentGradeCourseMap;

    public Course() {
        studentGradeCourseMap = new HashMap<>();
    }

    public void enrollStudent(S student) {
        studentGradeCourseMap.put(student, grade);
    }

    public boolean assignGrade(S student, G grade) {
        if (studentGradeCourseMap.containsKey(student) && !studentGradeCourseMap.containsKey(null)) {
            studentGradeCourseMap.put(student, grade);
            return true;
        } else {
            return false;
        }
    }

    public boolean isStudentEnrolled(S student) {
        if (!studentGradeCourseMap.containsKey(null)) {
            return studentGradeCourseMap.containsKey(student);
        } return false;
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
        if (!studentGradeCourseMap.isEmpty()) {
            for (S student : studentGradeCourseMap.keySet()) {
                System.out.println("Student: " + student.toString() + ", Grade: "
                        + studentGradeCourseMap.get(student));
            }
        } else
            System.out.println("Error ");
    }

    public double averageGrades() {
        Collection<G> grades = studentGradeCourseMap.values();
        double sum = 0;
        for (G element : grades) {
            sum += element.doubleValue();
        }
        return (sum/grades.size());
    }

    public void listAllStudent() {
        Set<S> keySet = studentGradeCourseMap.keySet();
        System.out.println("Unique students enrolled: ");
        for (S element : keySet) {
            System.out.println(element);
        }
    }

    public Collection<S> listUniqueAllStudent() {
        return studentGradeCourseMap.keySet();
    }


    public static void main(String[] args) {
        Course course = new Course();
        course.enrollStudent(12345);
        course.assignGrade(12345, 95.0);
        course.enrollStudent(67890);
        course.assignGrade(67890, 96.2);
        course.enrollStudent("Karan");
        course.assignGrade("Karan", 95);
        course.enrollStudent("karan");

        course.listAllGrades();
        course.listAllStudent();
        course.listUniqueAllStudent();
    }
}
