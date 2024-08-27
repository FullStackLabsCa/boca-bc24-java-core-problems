package problems.genrics;

import java.util.HashMap;
import java.util.Map;

public class CourseBook<S, G extends Number> {

    private Map<S, G> studentGrade;

    public CourseBook() {
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

    public Map<S, G> listAllGrades() {
        return new HashMap<>(studentGrade);
    }
}