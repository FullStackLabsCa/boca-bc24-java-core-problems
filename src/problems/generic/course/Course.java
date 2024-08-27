package problems.generic.course;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Course<S, G extends Number> {

    Map<S, G> students = new HashMap<>();
    List<G> grades;

    public S enrollStudentToCourse(S student) {
        this.students.put(student, null);
        return student;
    }

    public G assignGradeToStudent(G grade, S identifier) {
        return this.students.putIfAbsent(identifier, grade);
    }

    public G retrieveGradeOfStudent(S identifier) {
        return this.students.get(identifier);
    }

    public void listStudentWithGrade() {
        students.forEach((k, v) -> {
            System.out.println(k + ":" + v);
        });
    }

    public static void main(String[] args) {

        Course<String, Double> stringDoubleCourse = new Course<>();
        stringDoubleCourse.enrollStudentToCourse("Gaurav");
        stringDoubleCourse.assignGradeToStudent(90.00, "Gaurav");
        stringDoubleCourse.retrieveGradeOfStudent("Gaurav");

        stringDoubleCourse.enrollStudentToCourse("Nippa");
        stringDoubleCourse.assignGradeToStudent(85.00, "Nippa");
        stringDoubleCourse.retrieveGradeOfStudent("Nippa");
        stringDoubleCourse.listStudentWithGrade();
    }
}
