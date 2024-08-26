package generics;

import java.util.HashMap;
import java.util.Map;


public class Course <S, G extends Number> {

//    private S studentInformation; //Could be String or Integer being student ID
//    private G grade; //Have to be something Number

    //Maps will be a good choice because we have a unique student with some grade in the course.
    private Map<S,G> courseMapping;

    public Course() {
         courseMapping = new HashMap<>();
    }

    //Functionalities
    //Enroll Students
    public boolean enrollStudent(S student){
        //ValidateStudent
        //.
        if(!courseMapping.containsKey(student)){
            courseMapping.put(student,null);
            System.out.println("student enrolled : " + student);
            return true;
        } else {
            System.out.println("Student Already enrolled in the course.");
            return false;
        }

    }

    //Assign Grades
    public boolean assignGrade(S student, G grade){
        if(courseMapping.containsKey(student)){
             courseMapping.put(student, grade);
             return true;
        } else {
            System.out.println("No such Student is enrolled in the course.");
            return false;
        }
    }

    //Retrieve Grades
    public double retrieveGrade(S studentID){
        if(courseMapping.containsKey(studentID)){
            return courseMapping.get(studentID).doubleValue();
        } else {
            System.out.println("No such Student is enrolled in the course.");
            return -1;
        }
    }

    //List all Grades
    public void listAllGrades(){
        if(!courseMapping.isEmpty()) {
            for (S student : courseMapping.keySet()) {
                System.out.println("Student: " + student.toString() + ", Grade: " + courseMapping.get(student));
            }
        } else {
            System.out.println("EMPTY COURSE!!! No students currently enrolled within the course.");
        }
    }

    public static void main(String[] args) {

        Course<String, Double> javaProgramming  = new Course<>();

        javaProgramming.retrieveGrade("Akshat");
        javaProgramming.listAllGrades();

        javaProgramming.enrollStudent("Akshat");
        javaProgramming.assignGrade("Akshat", 10.0);
        System.out.println(javaProgramming.retrieveGrade("Akshat"));
        javaProgramming.listAllGrades();

    }

}
