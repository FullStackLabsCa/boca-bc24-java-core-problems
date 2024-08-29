package problems.generics.course;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.*;

public class Course< S, G>
{
//    private Set<S> studentSet;

    private Map<S, G> studentGrades;
    public Course() {
        this.studentGrades = new HashMap<>();
//        this.studentSet = new HashSet<>();
    }
    public boolean isStudentEnrolled(S studentId) {
        if(studentGrades.containsKey(studentId)){
            return true;
        }else{
            return false;
        }
    }
    public void enrollStudent(S studentId) {
        if(!studentGrades.containsKey(studentId)){
            studentGrades.put(studentId,null);
        }else{
            System.out.println("It is already enrolled");
        }
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
    public Optional<G> getGrade(S studentId) {
        return Optional.ofNullable(studentGrades.get(studentId));
    }
    public Map<S, G> getAllGrades() {
        return studentGrades;
    }


    public void listAllGrades() {
        Set<Map.Entry<S, G>> entries = studentGrades.entrySet();
        for (Map.Entry<S, G> entry : entries) {
            System.out.println("Student ID: " + entry.getKey() + ", Grade: " + entry.getValue());
        }
    }



}
