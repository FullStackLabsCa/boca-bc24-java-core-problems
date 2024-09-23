//NOT DONE YET
package genericsproblems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class School<S, G extends Number> {
    Map<String, Course<S, G>> studentToCourseGradeMap = new HashMap<>();

    public void addCourse(String courseName) {

        studentToCourseGradeMap.put(courseName, new Course<>());
    }

    public void listOfAllCourse() {
        if (studentToCourseGradeMap.isEmpty()) {
            System.out.println("No Course is available");
        } else {
            for (String courseName : studentToCourseGradeMap.keySet()) {
                System.out.println("Course Name: " + courseName);
            }
        }
    }

    public boolean isCourseAdded(String courseName) {
        return studentToCourseGradeMap.containsKey(courseName);
    }

    public void enrolledStudent(String courseName, S studentID) {
        if (isCourseAdded(courseName)) {
            Course<S, G> course = studentToCourseGradeMap.get(courseName);
            course.enrollStudent(studentID);
            System.out.println("Student " + studentID + " enrolled in the course " + courseName + ".");
        } else {
            System.out.println("Student is not enrolled.");
        }

    }

    public void assignGrade(String courseName, S studentID, G grade) {
        Course<S, G> course = studentToCourseGradeMap.get(courseName);
        if (course != null) {
            if (course.isStudentEnrolled(studentID)) {
                course.assignGrade(studentID, grade);
                System.out.println("Grade " + grade + " assigned to student " + studentID + " in course " + courseName + ".");
            } else {
                System.out.println("Error: Student " + studentID + " is not enrolled in course " + courseName + ".");
            }
        } else {
            System.out.println("Error: Course " + courseName + " does not exist.");
        }

    }
    public void listGrade(String courseName){
        Course<S, G> course = studentToCourseGradeMap.get(courseName);
        if (course != null){


        }

    }

    public void processCommand(String str ) {


    }

    public static void main(String[] args) {
        School<String, Integer> school = new School<>();
        school.addCourse("Maths 101");
        school.addCourse("Java 2.0");

        System.out.println("List of Course:");
        school.listOfAllCourse();

        school.enrolledStudent("Maths 101", "Gagan");
        school.enrolledStudent("Network", "Gungun");

        school.assignGrade("Maths 101", "Gagan", 67);
        school.listGrade("Maths 101");
    }


}
