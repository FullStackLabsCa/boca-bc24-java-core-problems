package problems.generics.course;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Course< S, G extends Number>
{
    private Map<S, G> studentGrades;
    public Course() {
        this.studentGrades = new HashMap<>();
    }

    public void enrollStudent(S studentId, G initialGrade) {
        studentGrades.put(studentId, initialGrade);
    }

    public void assignGrade(S studentId, G grade) {
        if (studentGrades.containsKey(studentId)) {
            studentGrades.put(studentId, grade);
        } else {
            System.out.println("Student not enrolled.");
        }
    }
    public G retrieveGrade(S studentId) {
        return studentGrades.get(studentId);
    }

    public void listAllGrades() {
        Set<Map.Entry<S, G>> entries = studentGrades.entrySet();
        for (Map.Entry<S, G> entry : entries) {
            System.out.println("Student ID: " + entry.getKey() + ", Grade: " + entry.getValue());
        }
    }
//
//    public boolean isStudentEnrolled(S studentId) {
//        return studentGrades.containsKey(studentId);
//    }
}
