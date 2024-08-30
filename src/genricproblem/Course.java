//package genricproblem;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class Course<S extends Number,G extends Number> {
//   private Map<S,G>  course;
//   private String courseName;
//
//   //create the constructor
//
//
//    public Course() {
//        this.course = new HashMap<>();
//        this.courseName = courseName;
//    }
//
//    public void enrollStudent(S studentId) {
//        course.get(studentId);
//    }
//
//    public boolean isStudentEnrolled(S studentId) {
//        return course.containsKey(studentId);
//    }
//
//    public S getAllGrades() {
//
//        if (course != null) {
//            S grade;
//            return grade;
//        } else {
//            System.out.println("Error: Course '" + courseName + "' does not exist.");
//        }
//        return null;
//    }
//}