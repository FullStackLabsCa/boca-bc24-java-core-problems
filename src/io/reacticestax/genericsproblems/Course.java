package io.reacticestax.genericsproblems;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Course <S , G extends Number >{

    private final Map<S ,G> students = new HashMap<>() ;
//    S studentIdentifier ;
//    G grade;



    //methods for Course class

    public void enrollStudent(S studentIdentifier ) {
         this.students.put(studentIdentifier , null);
    }

    public boolean isStudentEnrolled(S studentIdentifier) {
        if(studentIdentifier == null){
            return false;
        }
        return students.containsKey(studentIdentifier);
    }


    public void assignGrade(S studentIdentifier, G grade) {
        if(this.students.containsKey(studentIdentifier)) {
            this.students.put(studentIdentifier, grade);
        }
    }

    public Optional<G> getGrade(S studentIdentifier) {
            return Optional.ofNullable(students.get(studentIdentifier));
    }

    public Map<S,G> getAllGrades() {
        return students;
    }



    public void listAllGrades() {
        System.out.println(this.students);;
    }


    public static void main(String[] args) {
        Course<Integer, Double> course = new Course<>();

        course.enrollStudent(12345);
        course.enrollStudent(23456);
        course.isStudentEnrolled(12345);

        course.assignGrade(12345, 8.0);
        course.assignGrade(23456, 9.8);


        System.out.println(  course.getGrade(12345));
        System.out.println( course.getGrade(23456));


        System.out.println("All grades: " + course.getAllGrades());


    }

}
