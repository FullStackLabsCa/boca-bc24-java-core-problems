package course;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Course <S,G extends Number>{
    private final Map<S,G> studentGradeMap;


    public Course() {
        this.studentGradeMap = new HashMap<>();
    }

    public void enrollStudent(S studentName){
        studentGradeMap.put(studentName,null);
    }


    public boolean isStudentEnrolled(S studentName) {
        if(studentGradeMap.containsKey(studentName)){
            return true;
        }
        else
            return false;
    }

    public Map<S, G> getAllGrades() {
        return studentGradeMap;
    }

    public void assignGrade(S studentName, G grade) {
        if (studentGradeMap.containsKey(studentName)){
            studentGradeMap.put(studentName,grade);
        }
        else {
            System.out.println("Student does not exist");
        }
    }

    public G getStudentGrade(S studentName) {
        return studentGradeMap.get(studentName);
    }

    public Optional<Double> getGrade(S studentName) {
        G grade = getStudentGrade(studentName);
        if(studentGradeMap.containsKey(studentName)){
            return Optional.ofNullable((Double) grade);
        }
        else{
            return Optional.empty();
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
