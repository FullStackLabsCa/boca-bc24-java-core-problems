package problems.generics.course;

import java.util.*;

public class Course <S, G extends Number>{
        Map<S, G> studentGrades;

    public Course(String courseName, Map<S, G> studentGrades) {
        this.studentGrades = studentGrades;
    }

    public Course() {
        this.studentGrades = new HashMap<>();
    }

    public void enrollStudent(S studentId) {
        studentGrades.put(studentId, null);
    }

    public boolean isStudentEnrolled(S studentId){
        return studentGrades.containsKey(studentId);
    }

    public Optional<G> assignGrade(S studentId, G grade) {
        if (!studentGrades.containsKey(studentId)) {
            return Optional.empty();
        }
        studentGrades.put(studentId, grade);
        return Optional.of(grade);
    }

    public Optional<G> getGrade(S studentId){
            return Optional.ofNullable(studentGrades.get(studentId));
    }

    public Map<S,G> getAllGrades(){
        return studentGrades;
    }

    public Map<S, G> listAllGrades(){
        return studentGrades;
    }

    @Override
    public String toString() {
        return
                "studentGrades=" + studentGrades;
    }

    public static void main(String[] args) {
        Course<Integer, Double> mathCourse = new Course<>();

        mathCourse.enrollStudent(1);
        mathCourse.assignGrade(1, 88.0);

        System.out.println("Grade for student 1: " + mathCourse.getGrade(1));
        System.out.println("All grades: " + mathCourse.getAllGrades());
    }
}
