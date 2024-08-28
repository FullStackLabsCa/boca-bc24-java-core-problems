package generic_problems;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Course<S, G extends Number> {
    private S student;
    private G grade;
    private Map<S, G> studentGradeMap;
//    private String courseName;

    public Course() {
//        this.courseName=courseName;
        this.studentGradeMap = new HashMap<>();
    }

    public void enrollStudent(S student) {
        if (studentGradeMap.containsKey(student)) {
            System.out.println(student + " is already enrolled");
        } else {
            studentGradeMap.put(student, grade);
            System.out.println(student + " is enrolled");
        }
    }

    public void assignGrade(S student,G grade) {
        if (studentGradeMap.containsKey(student)) {
            studentGradeMap.put(student, grade);
            System.out.println(student + "  grade is updated to "+ grade);
        }else System.out.println(student+ " doesn't exist");
    }

    public void retrieveGrade(S student) {
        if (studentGradeMap.containsKey(student)) {
            studentGradeMap.get(student);
        }else System.out.println(student+ " doesn't exist");
    }

    public void listAllStudents(){
        if (studentGradeMap.isEmpty()){
            System.out.println("No Student enrolled in this course");
        }else {Set<Map.Entry<S, G>> entries = studentGradeMap.entrySet();
        for (Map.Entry<S, G> set: entries) {
            System.out.println("List of all Students");
            System.out.println("Student Name: "+set.getKey() + " " + "\nGrade: "+set.getValue());
        }
        }
    }

    public static void main(String[] args) {
        Course<String,Double> java = new Course<>();
        java.enrollStudent("Gurpreet");
        java.assignGrade("Gurpreet",90.0);
        java.retrieveGrade("Gurpreet");
        java.listAllStudents();
    }
}