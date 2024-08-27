package course;

import java.util.HashMap;
import java.util.Map;

public class Course <S,G extends Number>{
    private final Map<S,G> studentGradeMap;


    public Course() {
        this.studentGradeMap = new HashMap<>();
    }

    public void addStudentToGrade(S studentName,G studentGrade){
        studentGradeMap.put(studentName,studentGrade);
    }
    public G getStudentGrade(S studentName) {
        return studentGradeMap.get(studentName);
    }

    public void printStudentGrade(S studentName) {
        G grade = getStudentGrade(studentName);
        if (grade != null) {
            System.out.println("Student: " + studentName + ", Grade: " + grade);
        } else {
            System.out.println("Student: " + studentName + " not found.");
        }
    }

    public void updateStudentGrade(S studentName, G newGrade) {
        if (studentGradeMap.containsKey(studentName)) {
            studentGradeMap.put(studentName, newGrade);
        } else {
            System.out.println("Student not found!");
        }
    }

    @Override
    public String toString() {
        return "Course{" +
                "studentGradeMap=" + studentGradeMap +
                '}';
    }
}
