package problems.generics.schoolbook;

import java.util.HashMap;
import java.util.Map;

public class Course<S, G extends Number> {

    private final Map<S, G> studentToGrade;

    public Course() {
        this.studentToGrade = new HashMap<>();
    }

    public Map<S, G> getStudentToGrade() {
        return studentToGrade;
    }

    public void enrollStudent(S studentIdentifier) {
        this.studentToGrade.put(studentIdentifier, null);
    }

    public void assignGrade(S studentName, G grade) {
        this.studentToGrade.put(studentName, grade);
    }
}
