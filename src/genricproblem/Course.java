package genricproblem;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Course<S,G> {
   Map<S,G> courseGpa= new HashMap<>();

    @Override
    public String toString() {
        return "Course[" +
                "Student Record with GPA=" + courseGpa +
                ']';
    }

    //enroll the student
    public void enrollStudent(S studentID){
        this.courseGpa.putIfAbsent(studentID,null);  //only add if student not enrolled
    }

    // assigning grade
    public void assignGrade(S studentID, G grade){
        if(this.courseGpa.containsKey(studentID)){
            this.courseGpa.put(studentID, grade);
        } else {
            System.out.println("Student ID not found : "+studentID);
        }
    }

    // retrieve grade

    public G retrieveGrade(S studentID){
        return this.courseGpa.get(studentID);
    }
    //list all the grade
    // Method to list all students and their grades

    public Set<Map.Entry<S, G>> listAllGrades() {
        return courseGpa.entrySet(); // Returns a set of entries
    }

    public static void main(String[] args) {
        Course<Integer, String> studentGrades = new Course<>();

        //enroll the student
        studentGrades.enrollStudent(12);
        studentGrades.enrollStudent(23);
        studentGrades.enrollStudent(34);
        studentGrades.enrollStudent(45);
        studentGrades.enrollStudent(56);

        //assign the grade
        studentGrades.assignGrade(12, "A");
        studentGrades.assignGrade(23, "B");
        studentGrades.assignGrade(34, "A");
        studentGrades.assignGrade(45, "A");
        studentGrades.assignGrade(56, "C");

        //retrieve and display the grade
        System.out.println("Grade for student 12 : " + studentGrades.retrieveGrade(65656));
        System.out.println("Grade for student 23 : " + studentGrades.retrieveGrade(23));
        System.out.println("Grade for student 34 : " + studentGrades.retrieveGrade(34));
        System.out.println("Grade for student 45 : " + studentGrades.retrieveGrade(45));
        System.out.println("Grade for student 56 : " + studentGrades.retrieveGrade(56));

        //list the grade
        System.out.printf("All student garde : " + studentGrades.listAllGrades());
    }
    }


