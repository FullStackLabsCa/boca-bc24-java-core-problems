package course;

import java.util.HashMap;
import java.util.Map;

public class Course <S,G extends Number>{

    private G grade;

    public G getGrade() {
        return grade;
    }

    public void setGrade(G grade) {
        this.grade = grade;
    }

    private String studentName;
    private int studentId;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
    public String toString() {
        return studentName;
    }


    private Map<S, G> studentGradeMap;

    public Course() {
        this.studentGradeMap = new HashMap<>();
    }

    public void enrollStudent(Course student, Integer grade) {
        studentGradeMap.put((S) student, (G) grade);
    }

    public Map<S, G> getStudentToGradeMap() {
        return studentGradeMap;
    }

}
