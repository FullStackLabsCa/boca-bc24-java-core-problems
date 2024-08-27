package problems.generics;

import java.util.HashMap;
import java.util.Map;

public class Course<S, G extends Number> {

    private Map<S, G> studentToGradeMap;

    public Course() {
        studentToGradeMap = new HashMap<>();
    }

    public void enrollStudent(S studentId) {
        if (!studentToGradeMap.containsKey(studentId)) {
            studentToGradeMap.put(studentId, null);
        }
    }

    public void assignGrades(S studentId, G grade) {
        if (studentToGradeMap.containsKey(studentId)) {
            studentToGradeMap.put(studentId, grade);
        } else {
            System.out.println("Student not enrolled in the course.");
        }
    }


    public G retrieveGrade(S studentId) {
        return studentToGradeMap.get(studentId);
    }

    public Map<S, G> listAllGrades() {
        return new HashMap<>(studentToGradeMap);
    }

    /*public Double averageOfGrades() {
        Double average = 0.0;
        for (Map.Entry<S, G> entry : studentToGradeMap.entrySet()) {
            if(entry.getValue()!=null) {
                average += (entry.getValue()).doubleValue();
            }
        }
        *//*for (G value : studentToGradeMap.values()) {
            System.out.println(value);
        }*//*
        return average/studentToGradeMap.size();
    }*/


}

