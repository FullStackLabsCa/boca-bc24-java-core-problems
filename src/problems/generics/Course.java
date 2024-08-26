package problems.generics;

import java.util.HashMap;
import java.util.Map;

public class Course<S, G> {
    private Map<S, G> students;

    public Course(Map<S, G> students) {
        this.students = new HashMap<>();
    }

    public void enrollStudent(S studentIdentifier){
        this.students.put(studentIdentifier, null);
    }

    public void assignGrade(S studentIdentifier, G grade){
        this.students.replace(studentIdentifier, grade);
    }

    public G retrieveGrade(S studentIdentifier){
        return this.students.get(studentIdentifier);
    }

}
