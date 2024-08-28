package generic_problems.school;

import java.util.*;

public class Course<S, G extends Number> {
    private S student;
    private G grade;
    private Map<S, G> studentGradeMap;
//    private String courseName;

    public Course() {
//        this.courseName=courseName;
        this.studentGradeMap = new HashMap<>();
    }

    public Boolean enrollStudentToCourse(S student) {
        if (studentGradeMap.containsKey(student)) {
            return false;
        } else {
            studentGradeMap.put(student, grade);
            return true;
        }
    }

    public void assignGrade(S student, G grade) {
        if (studentGradeMap.containsKey(student)) {
            studentGradeMap.put(student, grade);
            System.out.println(student + "  grade is updated to " + grade);
        } else System.out.println(student + " doesn't exist");
    }

    public double retrieveGrade(S student) {
        double marks=0;
        if (studentGradeMap.containsKey(student)) {
            if (studentGradeMap.get(student)!=null){
                marks = studentGradeMap.get(student).doubleValue();
            } else
             marks = 0.0;
        } else System.out.println(student + " doesn't exist");
        return marks;
    }

    public void listAllStudents() {
        if (studentGradeMap.isEmpty()) {
            System.out.println("No Student enrolled in this course");
        } else {
            Set<Map.Entry<S, G>> entries = studentGradeMap.entrySet();
            for (Map.Entry<S, G> set : entries) {
                System.out.println("List of all Students");
                System.out.println("Student Name: " + set.getKey() + " " + "\nGrade: " + set.getValue());
            }
        }
    }

    public Collection<S> getStudents() {
        Set<S> studentsSet = new HashSet<>();
        if (studentGradeMap.isEmpty()) {
            System.out.println("No Student enrolled in this course");
        } else {
            Set<Map.Entry<S, G>> entries = studentGradeMap.entrySet();
            for (Map.Entry<S, G> set : entries) {
                studentsSet.add(set.getKey());
            }
//            System.out.println(studentsSet);
        }
        return studentsSet;
    }

    public double averageScore() {
        double count = 0, sum = 0;
        double average = 0;
        if (studentGradeMap.isEmpty()) {
            System.out.println("No Student enrolled in this course");
        } else {
            Set<Map.Entry<S, G>> studentGradeEntries = studentGradeMap.entrySet();
            for (Map.Entry<S, G> score : studentGradeEntries) {
                double studentGrade = score.getValue().doubleValue();
                sum += studentGrade;
                count++;
            }
            average = sum / count;
        }
        return average;
    }
}