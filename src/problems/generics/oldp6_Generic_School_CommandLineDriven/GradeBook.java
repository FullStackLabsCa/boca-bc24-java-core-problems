package problems.generics.oldp6_Generic_School_CommandLineDriven;

import java.util.HashMap;
import java.util.Map;

public class GradeBook<S, G> {

    private Map<S, G> studentGrades;

    // Constructor
    public GradeBook() {
        this.studentGrades = new HashMap<>();
    }

    // Adds a student to the gradebook with np grade
    public void addStudent(S studentId) {
        studentGrades.putIfAbsent(studentId, null);
    }

    // Sets the grade for a student
    public void setGrade(S studentId, G grade){
        if(studentGrades.containsKey(studentId)){
            studentGrades.put(studentId, grade);
        }else {
            throw new IllegalArgumentException("Student not found");
        }
    }

    //Gets all grades in the gradebook
    public Map<S,G> getGrades(){
        return new HashMap<>(studentGrades);
    }
}
