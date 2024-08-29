package genricproblem;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class School<S, G> {
    Map<String, Course<S, G>> courseHashMap = new HashMap<>();// creating a hashMap for student and grade in course class

    public School() {
    }

    public void addCourse(String courseName) {  // add courseName passing with Course
        courseHashMap.containsKey(courseName);
        courseHashMap.put(courseName, (Course<S, G>) new Course<>(courseName));

    }

    public void enrollStudent(String courseName, S studentId) { // enrolling studentid with coursename only
        Course<S, G> course = courseHashMap.get(courseName);
        course.enrollStudent(studentId);
        System.out.println(studentId+" student id assigned with "+ courseName + " course");
    }

    public void assignGrade(String courseName, S studentid, G grade) {
        Course<S, G> course = courseHashMap.get(courseName);
        course.assignGrade(studentid, grade);
        System.out.println("Student id "+ studentid +" having "+courseName +" with grade: "+grade);
    }

    public void listAllGrades(String courseName) {
        Course<S, G> course = courseHashMap.get(courseName);
            for (Map.Entry<S, G> entry : course.getAllGrades().entrySet()) {
                System.out.println("Student id " + entry.getKey() + ": " + entry.getValue());
        }
    }

    public void listCourses() {
        System.out.println("Courses are :");
        for (String courseName: courseHashMap.keySet() ) {

            System.out.println(courseName);
        }
    }
    public static void main(String[] args) {
        School<Integer, Integer> schoolentry = new School<>();
        schoolentry.addCourse("Math");
        schoolentry.addCourse("Science");
        schoolentry.enrollStudent("Math",101);
        schoolentry.enrollStudent("Science",102);
       schoolentry.listCourses();
       schoolentry.assignGrade("Math",101,4);
       schoolentry.assignGrade("Science",102,5);
       schoolentry.listAllGrades("Math");
       schoolentry.listAllGrades("Science");
    }
}


