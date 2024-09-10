package course_exception;

import java.util.*;

public class Course<S,G extends Number> {
    private final Map<S, G> studentGrades;
    private G grade;


    //create the constructor
    public Course() {
        this.studentGrades = new HashMap<>();
    }


    //enroll the student
    public void enrollStudent(S studentId) throws StudentAlreadyEnrolledException{
        if(studentGrades.containsKey(studentId)){
            throw new StudentAlreadyEnrolledException(studentId.toString());
        }
        studentGrades.putIfAbsent(studentId,null);
    }

    //check if the student is enrolled if not return false
    public boolean isStudentEnrolled(S studentId) {
        if (studentGrades.containsKey(studentId))
            return true;
        else
            return false;
    }


    //assign the grade
    public void assignGrade(S studentId, G grade) throws StudentNotFoundException {
        if (!studentGrades.containsKey(studentId)) {
           throw new StudentNotFoundException(studentId.toString());
        }
        System.out.println(studentGrades.put(studentId, grade));
    }

    //get the grade for a student
    public G getGrade(S studentId) throws StudentNotFoundException,GradeNotAssignedException{
        if (!studentGrades.containsKey(studentId)) {
            throw new StudentNotFoundException(studentId.toString());
        }
        G grade = studentGrades.get(studentId);
        if (grade == null) {
            throw new GradeNotAssignedException(studentId.toString());
        }
        return grade;
    }

    //get all the grades as  a map
    public Map<S, G> getAllGrades() {
        return studentGrades;//return a new copy of the map to avoid external modification

    }

    public void listAllGrades() {
        if (studentGrades.isEmpty()) {
            System.out.println("No grades assigned. ");
        } else {
            for (Map.Entry<S, G> entry : studentGrades.entrySet()) {
                System.out.println("Student ID: " + entry.getKey() + ", Grade: " + entry.getValue());
            }
        }
    }
}


