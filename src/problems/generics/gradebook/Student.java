package problems.generics.gradebook;

import java.util.HashMap;
import java.util.Map;

public class Student {
    String name;
    Map<Course, GradeBook> courseGradeBookMap;

    public Student(String name){
        this.name= name;
        this.courseGradeBookMap= new HashMap<>();
    }

    public void enrollInCourse(Course course, double grade){
        courseGradeBookMap.put(course, grade);
        course.addS
    }
}