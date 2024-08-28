//package problems.generics.course;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class Course <S, G extends Number>{
//        String courseName;
//        Map<S, G> studentGrades;
//
//    public Course(String courseName, Map<S, G> studentGrades) {
//        this.courseName = courseName;
//        this.studentGrades = studentGrades;
//    }
//
//    public String getCourseName() {
//        return courseName;
//    }
//
//    public void addStudent(S studentId, G grade){
//        studentGrades.put(studentId, grade);
//    }
//
//    public void removeStudent(S studentId){
//        studentGrades.remove(studentId);
//    }
//
//    public Map<S, G> getStudentGrades() {
//        return studentGrades;
//    }
//
//    public Map<S, G> getAllGrades() {
//        return new HashMap<>(studentGrades);
//    }
//
//    @Override
//    public String toString() {
//        return "Course{" +
//                "courseName='" + courseName + '\'' +
//                ", studentGrades=" + studentGrades +
//                '}';
//    }
//
//    public static void main(String[] args) {
//        Course<Integer, Double> mathCourse = new Course<>("Math 101");
//
//        mathCourse.addStudent(1, 95.5);
//        mathCourse.addStudent(2, 88.0);
//
//        System.out.println(mathCourse);
//
//        mathCourse.removeStudent(2);
//
//        System.out.println("Grade for student 1: " + mathCourse.getStudentGrades(1));
//        System.out.println("All grades: " + mathCourse.getAllGrades());
//    }
//}
