package genericsProblems.schoolGenerics;

import java.util.Collection;
import java.util.Collections;
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

    public void assignGrade(S studentIdentifier, G grade) {
        this.studentToGrade.put(studentIdentifier, grade);
    }

    public double averageGrade(){
        Collection<G> grades = studentToGrade.values();
        double sum = 0;

        for (G grade : grades){
            sum += grade.doubleValue();
        }

        return (sum / grades.size());
    }

}


