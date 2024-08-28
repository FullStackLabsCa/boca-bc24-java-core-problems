package genproblem.schoolbook;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CourseBook<S,G>{
    private final String courseName;
    private final Map<S,G> studentGrade;

    //constructor

    public CourseBook(String courseName) {
        this.courseName = courseName;
        this.studentGrade = new HashMap<>();
    }

    // add the course name

    public String getCourseName() {
        return courseName;
    }

    //add grade to student

    public void addStudent(S studentId,G grade){
        studentGrade.put(studentId,grade);
    }

    //get the grade with the help of student id
    public G getGrade(S studentId){
        return studentGrade.get(studentId);
    }

    //update the grade
    public void upgradeGrade(S studentId,G grade){
        studentGrade.put(studentId,grade);
    }
    // get all the grades

    public Map<S, G> getAllGrade() {
        //return new HashMap<>(studentGrade);
        return Collections.unmodifiableMap(studentGrade);
    }
}


