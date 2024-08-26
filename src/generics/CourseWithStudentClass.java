package generics;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

class Student1 {
    String studentName;
    Integer studentID;

    public Student1(Integer studentID, String studentName) {
        this.studentID = studentID;
        this.studentName = studentName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getStudentID() {
        return studentID;
    }

    public void setStudentID(Integer studentID) {
        this.studentID = studentID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student1 student1 = (Student1) o;
        return Objects.equals(studentName, student1.studentName) && Objects.equals(studentID, student1.studentID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentName, studentID);
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentName='" + studentName + '\'' +
                ", studentID=" + studentID +
                '}';
    }
}

public class CourseWithStudentClass<S extends Student1, G extends Number> {

//    private S studentInformation; //Could be String or Integer being student ID
//    private G grade; //Have to be something Number

    //Maps will be a good choice because we have a unique student with some grade in the course.
    private Map<S,G> courseMapping;

    public CourseWithStudentClass() {
         courseMapping = new HashMap<>();
    }

    //Functionalities
    //Enroll Students
    public boolean enrollStudent(S student){
        //ValidateStudent
        //.
        if(!courseMapping.containsKey(student)){
            courseMapping.put(student,null);
            System.out.println("student enrolled = " + student.toString());
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
                //Output
                //Student : ABC, Grade:
                System.out.println("Student: " + student.toString() + ", Grade: " + courseMapping.get(student));
            }
        } else {
            System.out.println("EMPTY COURSE!!! No students currently enrolled within the course.");
        }
    }

    public static void main(String[] args) {

        CourseWithStudentClass<Student1, Double> javaProgramming  = new CourseWithStudentClass<>();
        Student1 akshat = new Student1(1, "Akshat");

        javaProgramming.retrieveGrade(akshat);
        javaProgramming.listAllGrades();
        javaProgramming.enrollStudent(akshat);
        javaProgramming.assignGrade(akshat, 10.0);
        System.out.println(javaProgramming.retrieveGrade(akshat));
        javaProgramming.listAllGrades();

    }

}
