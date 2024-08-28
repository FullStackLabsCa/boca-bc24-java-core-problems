package genricproblem;

import java.util.*;

public class Course<S, G> {

    private G grade;
    Map<S, G> courseGpa = new HashMap<>();

    public Course() {// constructor
        this.courseGpa = new HashMap<>();
    }

    public void enrollStudent(S studentid) {
        courseGpa.put(studentid, grade);
    }
    public boolean isStudentEnrolled(S studentid) {
        if (this.courseGpa.containsKey(studentid))
            return true;
        else
            return false;
    }

    public Collection<Object> getAllGrades(S studentid) {

        Set<Map.Entry<S, G>> entries = courseGpa.entrySet();
        System.out.println("List of students with grades");
        for (Map.Entry<S, G> s : entries) {
            System.out.println(" grades: " + s.getValue());
        }
        return java.util.List.of(this.courseGpa.containsKey(entries));
    }

    public Optional<G> getGrade(S studentid) {
        if (studentid == null) {
            System.out.println("no student id");
        }
        if (courseGpa.containsKey(studentid)) {
            grade = courseGpa.get(studentid);
            return Optional.of(grade);
        }
        return Optional.empty();
    }

    public void assignGrade(S studentid, G grade) {
        if (studentid == null) {
            System.out.println("no student id :");
            return;
        }
        if (courseGpa.containsKey(studentid)) {
            courseGpa.put(studentid, grade);
        }
    }

    public Map<S, G> getAllGrades() {
        return new HashMap<>(courseGpa);
    }

    public void listAllGrades() {
        Set<Map.Entry<S, G>> entries = courseGpa.entrySet();
        System.out.println("List of students with grades");
        for (Map.Entry<S, G> s : entries) {
            System.out.println("Student id:" + s.getKey() + " grades: " + s.getValue());
        }
    }

    public static void main(String[] args) {
        Course<Integer, Integer> courseGeneric = new Course<>();
        courseGeneric.enrollStudent(100);
        courseGeneric.enrollStudent(101);
        courseGeneric.enrollStudent(102);
        courseGeneric.assignGrade(101, 4);
        courseGeneric.assignGrade(102, 5);
        courseGeneric.getGrade(101);
        System.out.println("student with " + courseGeneric.getGrade(101) + " grades");
        courseGeneric.getAllGrades();
    }


}
