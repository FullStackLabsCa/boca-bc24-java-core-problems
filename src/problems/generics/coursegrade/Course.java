package problems.generics.coursegrade;

import java.util.HashMap;
import java.util.Map;

public class Course<S, G> {
    private Map<S, G> students;

    public Course() {
        this.students = new HashMap<>();
    }

    public void enrollStudent(S studentIdentifier){
        this.students.put(studentIdentifier, null);
    }

    public void assignGrade(S studentIdentifier, G grade){
        if(this.students.containsKey(studentIdentifier)) this.students.replace(studentIdentifier, grade);
        else System.out.println("Student does not exist.");
    }

    public G retrieveGrade(S studentIdentifier){

        return this.students.get(studentIdentifier);
    }

    public Map<S, G> listAllGrades(){
        return this.students;
    }

}
