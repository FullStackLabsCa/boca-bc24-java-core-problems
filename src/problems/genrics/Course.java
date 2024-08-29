package problems.genrics;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Course<S, G extends Number> {

    Map<S, G> studentGrade;

    public Course() {
        studentGrade = new HashMap<>();
    }

    public void enrollStudent(S studentId) {
        if (!studentGrade.containsKey(studentId)) {
            studentGrade.put(studentId, null);
        }
        else{
            System.out.println("Student is already there");
        }
    }

    public boolean assignGrade(S studentId, G grade) {
        if (studentGrade.containsKey(studentId)) {
            studentGrade.put(studentId, grade);
            return true;
        } else {

            System.out.println("Student is not  enrolled.");
            return false;
        }
    }


    public Optional<G> getGrade(S studentId) {
        if(!studentGrade.containsKey(studentId)){
            return Optional.empty();
        }
        else {
            G grade = studentGrade.get(studentId);
            return Optional.of(studentGrade.get(studentId));
        }
    }

    public Map<S, G> getAllGrades() {

        return studentGrade;
    }

    public boolean isStudentEnrolled(S studentID) {

        if (studentID!=null) {
            if (studentGrade.containsKey(studentID)) {
                return true;
            }
            else {
                return false;
            }
        }
        return false;
    }

    public void listAllGrades() {
        System.out.println(studentGrade.values());
    }

//    public static void main(String[] args) {
//
//        Course<String,Double> courseGrade = new Course<>();
//
//        courseGrade.enrollStudent("john");
//        courseGrade.enrollStudent("jacob");
//
//
//        courseGrade.assignGrades("john",12.00);
//        courseGrade.assignGrades("jacob",38.00);
//
//
//
//        System.out.println("Grade :"+ courseGrade.retrieveGrade("john"));
//
//        courseGrade.listAllGrades();
//    }
//
//    public boolean isStudentEnrolled(S i) {
//    }
}