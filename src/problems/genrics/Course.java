package problems.genrics;

import java.util.HashMap;
import java.util.Map;

public class Course<S, G extends Number> {

    private Map<S, G> studentGrade;

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

    public void assignGrades(S studentId, G grade) {
        if (studentGrade.containsKey(studentId)) {
            studentGrade.put(studentId, grade);
        } else {
            System.out.println("Student is not enrolled.");
        }
    }


    public G retrieveGrade(S studentId) {
        return studentGrade.get(studentId);
    }

    public void listAllGrades() {
        System.out.println("studentGrade = " + studentGrade);
    }

    public static void main(String[] args) {

        Course<String,Double> courseGrade = new Course<>();

        courseGrade.enrollStudent("john");
        courseGrade.enrollStudent("jacob");


        courseGrade.assignGrades("john",12.00);
        courseGrade.assignGrades("jacob",38.00);



        System.out.println("Grade :"+ courseGrade.retrieveGrade("john"));

        courseGrade.listAllGrades();
    }
}