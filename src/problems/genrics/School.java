package problems.genrics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class School<S,G> {

    HashMap<S,G> course = new HashMap<>();
    HashMap<S,G> gradeBook = new HashMap<>();
    HashMap<S,S> enrollStudent = new HashMap<>();
    List<S> courseList = new ArrayList<>();



    public void add_course(S courseName){
        courseList.add(courseName);
    }

    public void enroll_student(S courseName,S studentIdentifier){
        if (courseList.contains(courseName )){
            enrollStudent.put(courseName,studentIdentifier);
        }
        else{
            System.out.println("Course is not available please add the course using (add_course).");
        }
    }
    public void assignGrade(S courseName,S studentIdentifier,G grade){

    }


    public void list_grades(courseName, studentIdentifier,grade){

    }

    public void list_courses(){

    }



}
