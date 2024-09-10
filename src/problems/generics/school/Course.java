package problems.generics.school;

import java.util.HashMap;
import java.util.Map;

public class Course<S, G extends Number> {


    Map<S, G> studentToGradesMap = new HashMap<>();


    public void enrollStudent(S studentId) {
        studentToGradesMap.put(studentId, null);
    }

    public void assignGrade(S studentId, G grade) {
        studentToGradesMap.put(studentId, grade);
//        if(studentToGradesMap.containsKey(studentId)){
//            studentToGradesMap.put(studentId,grade);
//        }else{
//            return "Student with "+ studentId + "is not enrolled yet";
//        }
//        return null;
    }

    public G retrieveGrades(S studentId) {
        return studentToGradesMap.get(studentId);

    }

    public void listAllGrade() {
        studentToGradesMap.values();

    }

    public void listAllStudent() {
        if (studentToGradesMap.isEmpty()) {
            System.out.println("No grades");
        } else {
            for (Map.Entry<S, G> entry : studentToGradesMap.entrySet()) {
                System.out.println("Student Id: " + entry.getKey() + " Grades " + entry.getValue());
            }
        }
    }

//
//    public Optional<G> getGrade(S i) {
//            return studentToGradesMap.get(i);
//    }

    public Map<S, G> getAllGrades() {
        return studentToGradesMap;
    }

    public void listAllGrades() {

    }

    public boolean isStudentEnrolled(S i) {
        if (studentToGradesMap.containsKey(i))
            return true;
        return false;
    }
}
