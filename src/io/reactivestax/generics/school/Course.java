package io.reactivestax.generics.school;

import java.util.*;

public class Course<S, G extends Number> {

    HashMap<S, G> students;

    public Course() {
        this.students = new HashMap<>();
    }

    public boolean containstStudent(S studentId){
        return students.containsKey(studentId);
    }

    public Map<S, G> addStudent(S studentId){
        students.put(studentId, null);
        return students;
    }

    public Map<S, G> addGrade(S studentId, G grade){
        if(students.containsKey(studentId)){
            students.put(studentId, grade);
            return students;
        }else {
            return null;
        }
    }

    public void getAllStudents() {
        for(Map.Entry<S, G> student: students.entrySet()){
            System.out.println("Student: "+student.getKey() +", Grade: "+ student.getValue());
        }
    }

    public double getAverageGrade() {
        double sum=0;
        for(Map.Entry<S, G> student: students.entrySet()){
            sum += student.getValue().doubleValue();
        }
        return sum/students.size();
    }
}
