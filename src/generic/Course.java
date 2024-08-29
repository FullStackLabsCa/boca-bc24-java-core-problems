package generic;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Course<S, G extends Number> extends School {

    Map<S, G> studentToGradeMap = new HashMap<>();

    public Set<S> listStudents(){
        return studentToGradeMap.keySet();
    }

    public void enrollStudents (S s) {
        studentToGradeMap.put(s,null);
    }

    public boolean isStudentEnrolled(S s) {
        return studentToGradeMap.containsKey(s);
    }

    public void assignGrades (S s,G g){
        if(isStudentEnrolled(s) == true){
            studentToGradeMap.put(s,g);
        }
    }

    public void listGrades (){
        Set<S> students = studentToGradeMap.keySet();
        for(S s: students){
            System.out.println("Student: " +s+ ", Grade: "+studentToGradeMap.get(s));
        }

    }

    public double averageScore (){
        Collection<G> grades = studentToGradeMap.values();
        double sum = 0;
        if(grades.isEmpty()){
           return sum/ grades.size();
        } else {
            for (G g : grades) {
                sum += g.doubleValue();
            }
            return sum / grades.size();
        }
    }

    public G getGrade (S student){
        G gradeOfStudent = studentToGradeMap.get(student);
        if(gradeOfStudent == null){
//            System.out.println("Grades for student : " + student + " has not Assigned !!");
        } else {
//            System.out.println("Grades for student : " + student + " => " + gradeOfStudent);
        }
        return gradeOfStudent;
    }

    public void printStudentsAndGrades (){
        System.out.println(studentToGradeMap);
    }
}
