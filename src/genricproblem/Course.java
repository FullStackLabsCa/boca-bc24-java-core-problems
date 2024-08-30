package genricproblem;

import java.util.*;

public class Course<S, G extends Number> {
    private G grade;
    Map<S, G> courseGpa = new HashMap<>();

    public Course() {
    }

    public void enrollStudent(S studentid) {
        courseGpa.put(studentid, grade);
    }

    public boolean isStudentEnrolled(S studentid) {
        if (courseGpa.containsKey(studentid))
            return true;
        else
            return false;
    }

    public Collection<Object> getAllGrades(S studentid) {

        Set<Map.Entry<S, G>> entries = courseGpa.entrySet();
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

    public Set<S> getStudents() {
        return courseGpa.keySet();
    }

    public void assignGrade(S studentid, G grade) {
        if (courseGpa.containsKey(studentid) == true)
            System.out.println(courseGpa.put(studentid, grade));

    }

    public Map<S, G> getAllGrades() {
        return new HashMap<>(courseGpa);
    }

    public void listAllGrades() {
        if (courseGpa.isEmpty()) {
            System.out.println("No grades assigned.");
        } else {
            for (Map.Entry<S, G> entry : courseGpa.entrySet()) {
                System.out.println("Student: " + entry.getKey() + ", Grade: " + entry.getValue());
            }
        }
    }
}





