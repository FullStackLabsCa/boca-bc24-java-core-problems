package genericexample;

import java.util.HashMap;
import java.util.Map;

public class School<S, G extends Number> {
    Map<String, Map<S, G>> studentToCourseMap = new HashMap<>();

    public void addCourse(String courseName) {
        studentToCourseMap.put(courseName, new HashMap<>());
    }

    public void listOfAllCourse(){
        if(studentToCourseMap.isEmpty()){
            System.out.println("No Course is available");
        }
       else{
            for (String courseName : studentToCourseMap.keySet()) {
                System.out.println(courseName);
            }
        }
    }
  public void enrolledStudent(String courseName, S s){


  }

    public static void main(String[] args) {
        School<String, Integer> school = new School<>();
        school.addCourse("Maths 101");
        school.addCourse("Java 2.0");

        System.out.println("List of Course:");
        school.listOfAllCourse();
    }
}
