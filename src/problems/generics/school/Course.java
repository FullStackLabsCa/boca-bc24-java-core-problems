package problems.generics.school;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Course<S, G extends Number> {
  Map<S, G> courseMap;
    S studentId;
    G grade;

    //constructor for course
    public Course() {
        courseMap = new HashMap<>();
    }

    public void enrollStudent(S studentId) {

       courseMap.put(studentId, grade);
       // System.out.println("New studentID: " + studentId);
    }

    public void assignGrade(S studentId, G grade) {

        courseMap.put(studentId, grade);
    }

    public G getGrade(S studentId) {
        System.out.println(courseMap.get(studentId));
        return courseMap.get(studentId);
    }

    public boolean isStudentEnrolled(S studentId) {
        if (courseMap.containsKey(studentId)) {
            return true;
        } else {
            return false;
        }
    }

    public Map<S, G> getAllGrades() {
        return new HashMap<>(courseMap);
    }

    public void listAllGrades() {
        for (Map.Entry<S, G> entry : courseMap.entrySet()) {
            System.out.println("Student: " + entry.getKey() + ", Grade: " + entry.getValue());
        }
    }

    public void listAllStudents() {
        System.out.println("Unique students enrolled:");
        for (Map.Entry<S, G> entry : courseMap.entrySet()) {
            System.out.println(entry.getKey());
        }
    }
    public Set<S> getStudent(S studentId){
        return courseMap.keySet();
    }




    public static void main(String[] args) {
        Course<Integer, Double> course = new Course<>();
        course.assignGrade(101, 3.5);
        course.assignGrade(102, 4.0);
        course.assignGrade(103, 3.1);
        course.assignGrade(104, 2.9);
        course.getGrade(1);
        course.getGrade(2);
        course.getGrade(3);
        course.getGrade(4);

        System.out.println(course.isStudentEnrolled(101));
        System.out.println(course.isStudentEnrolled(102));
        System.out.println(course.isStudentEnrolled(103));
        System.out.println(course.isStudentEnrolled(104));
        System.out.println(course.isStudentEnrolled(105));
        System.out.println("all grades are :" + course.getAllGrades());
        course.listAllGrades();
        course.getGrade(102);
        course.listAllStudents();
//        course.enrollStudent(109);
        // System.out.println(course.enrollStudent(8));

    }
}

