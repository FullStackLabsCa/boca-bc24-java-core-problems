package genproblem.schoolbook;

import java.util.HashMap;
import java.util.Map;

public class CourseBook<S,G extends Number> {

    private final String courseName;
    private final Map<S, G> studentGrades;

    //constructor
    public CourseBook(String courseName) {
        this.courseName = courseName;
        this.studentGrades = new HashMap<>();
    }

     //get course
    public String getCourseName() {
        return courseName;
    }


    //add student
    public void addStudent(S studentId, G grade) {
        studentGrades.put(studentId, grade);
    }


    //update student grade
    public void updateGrade(S studentId, G grade) {
        studentGrades.put(studentId, grade);
    }


    //get all the grades
    public Map<S, G> getAllGrades() {
        return (studentGrades);
    }

   //get grade with the help of student key
    public G getGrade(S studentId) {
        return studentGrades.get(studentId);
    }

    //has student
    public boolean hasStudent(S studentId) {
        return studentGrades.containsKey(studentId);
    }
}
