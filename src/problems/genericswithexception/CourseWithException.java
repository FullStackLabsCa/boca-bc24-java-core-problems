package problems.genericswithexception;

import java.util.HashMap;
import java.util.Map;

class StudentAlreadyEnrolledException extends Exception{
    public StudentAlreadyEnrolledException(String studentId) {
        super("Error: Student '" + studentId + "' is already enrolled.");
    }
}

class StudentNotFoundException extends Exception {
    public StudentNotFoundException(String studentId) {
        super("Error: Student '" + studentId + "' is not enrolled in the course.");
    }
}

class GradeNotAssignedException extends Exception {
    public GradeNotAssignedException(String studentId) {
        super("Error: Grade not assigned for student '" + studentId + "'.");
    }
}


public class CourseWithException<S, G> {
    private Map<S, G> course;
    S studentId;
    G grade;


    //constructor for course
    public CourseWithException() {
        course = new HashMap<>();
    }

    public void enrollStudent(S studentId) {
        course.put(studentId, null);
        System.out.println("New studentID: " + studentId);
    }

    public void assignGrade(S studentId, G grade) throws StudentNotFoundException{
    if(!course.containsKey(studentId)){
        throw new StudentNotFoundException(studentId.toString());
    }
        course.put(studentId, grade);
    }

    public void getGrade(S studentId) {
        System.out.println(course.get(studentId));
    }

    public boolean isStudentEnrolled(S studentId) {
        if (course.containsKey(studentId) == true) {
            return true;
        } else {
            return false;
        }
    }

    public Map<S, G> getAllGrades() {
        return new HashMap<>(course);
    }

    public void listAllGrades() {
        for (Map.Entry<S, G> entry : course.entrySet()) {
            System.out.println("StudentID: " + entry.getKey() + " their grade: " + entry.getValue());
        }
    }


    public static void main(String[] args) throws StudentNotFoundException {
        CourseWithException<Integer, Double> course = new CourseWithException<>();
        course.assignGrade(101, 3.5);
        course.assignGrade(102, 4.0);
        course.assignGrade(103, 3.1);
        course.assignGrade(104, 2.9);
        course.getGrade(1);
        course.getGrade(2);
        course.getGrade(3);
        course.getGrade(4);

        System.out.println(course.isStudentEnrolled(101));
        System.out.println(course.isStudentEnrolled(102));
        System.out.println(course.isStudentEnrolled(103));
        System.out.println(course.isStudentEnrolled(104));
        System.out.println(course.isStudentEnrolled(105));
        System.out.println("all grades are :" + course.getAllGrades());
        course.listAllGrades();
        course.getGrade(102);
        course.enrollStudent(109);
        // System.out.println(course.enrollStudent(8));

    }
}



