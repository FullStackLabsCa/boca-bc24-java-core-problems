package genericexample;

import java.util.LinkedHashMap;
import java.util.Map;

public class Course<S, G extends Number> {
    Map<S, G> course = new LinkedHashMap<>();

    public void enrollStudent(S studentID) {
        course.put(studentID, null);
    }

    public void displayStudents() {
        for (Map.Entry<S, G> entry : course.entrySet()) {
            System.out.println("Student ID: " + entry.getKey() + " " + entry.getValue());

        }
    }

    public void assignGrades(S studentID, G grades) {
        course.put(studentID, grades);
    }

    public void displayGrades() {
        for (Map.Entry<S, G> entry : course.entrySet()) {
            System.out.println("Student ID: " + entry.getKey() + "  " + "Student Grades: " + entry.getValue());

        }
    }

    public G getGrades(S studentID) {

        return course.get(studentID);

    }
   public void listOfGrades(){
        if()
   }

    public static void main(String[] args) {
        Course<String, Integer> studentCourse = new Course<>();
        studentCourse.enrollStudent("Gagan");
        studentCourse.enrollStudent("Nippa");
        studentCourse.displayStudents();


        studentCourse.assignGrades("Gagan", 56);
        studentCourse.assignGrades("Nippa", 79);
        studentCourse.displayGrades();


    }
}
