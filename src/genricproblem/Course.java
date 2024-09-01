package genricproblem;

import java.util.*;

public class Course<S,G extends Number> {
    private final Map<S, G> studentGrades;
    private G grade;

    //create the constructor
    public Course() {
        this.studentGrades = new HashMap<>();

    }

    //enroll the student
    public void enrollStudent(S studentId) {
        studentGrades.putIfAbsent(studentId, null);

    }

    //check if the student is enrolled if not return false
    public boolean isStudentEnrolled(S studentId) {
        if (studentGrades.containsKey(studentId))
            return true;
        else
            return false;
    }

    //assign the grade
    public void assignGrade(S studentId, G grade) {
        if (studentGrades.containsKey(studentId) == true) {
            System.out.println(studentGrades.put(studentId, grade));
        }
    }

    //get the grade for a student
    public Optional<G> getGrade(S studentId) {
        if(studentId==null){
            System.out.println("no student id");
        }
        if(studentGrades.containsKey(studentId)){
            grade=studentGrades.get(studentId);
            return Optional.of(grade);

        }
        return Optional.empty();
    }

    //get all the grades as  a map
    public Map<S, G> getAllGrades() {
        return new HashMap<>(studentGrades);//return a new copy of the map to avoid external modification

    }

    public void listAllGrades() {
        if (studentGrades.isEmpty()) {
            System.out.println("No grades assigned. ");
        } else {
            for (Map.Entry<S, G> entry : studentGrades.entrySet()) {
                System.out.println("Student ID: " + entry.getKey() + ", Grade: " + entry.getValue());
            }
        }
    }
}


