package io.reactivestax.generics;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class Course<S, G extends Number>{

    public static void main(String[] args) {
        Course<Integer, Double> course = new Course<>();
        course.assignGrade(12345, 95.0);
        assertEquals(Optional.empty(), course.getGrade(12345));
    }
    Map<S, G> students;

    public Course() {
        this.students = new HashMap<>();
    }

    public void enrollStudent(S studentId){
        students.put(studentId, null);
    }

    public void assignGrade(S studentId, G grade){
        if(students.containsKey(studentId)){
            students.put(studentId, grade);
        }
    }

    public Optional<Double> getGrade(S studentId) {
        if(students.containsKey(studentId)){
            G grade = students.get(studentId);
            return Optional.of(grade.doubleValue());
        }else{
            return Optional.empty();
        }

    }

    public boolean isStudentEnrolled(S studentId){
      return students.containsKey(studentId);

    }

    public void listAllGrades(){
        for (Map.Entry<S, G> student:   students.entrySet() ){
            System.out.println(student.getKey() +" "+ student.getValue());
        }
    }

    public Map<S, G> getAllGrades() {
        return students;
    }


}
