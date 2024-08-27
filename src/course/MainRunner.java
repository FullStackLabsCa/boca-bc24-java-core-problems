package course;

public class MainRunner {
    public static void main(String[] args) {

        Course<Student, Integer> course = new Course<>();
        Course course1 = new Course<>();
//        Course student1 = new Student();
        course1.setStudentName("Mann");
        course1.setStudentId(1);
        Course course2 = new Course<>();
        course2.setStudentName("Abhay");
        course2.setStudentId(2);

        course.enrollStudent(course1, 90);
        course.enrollStudent(course2,85);

        System.out.println(course.getStudentToGradeMap());
    }
}
