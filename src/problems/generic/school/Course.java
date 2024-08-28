package problems.generic.school;


import java.util.*;

public class  Course<S, G extends Number> {

     Map<S, G> course = new HashMap<>();

    public Course(Map<S, G> course) {
        this.course = course;
    }

    public Course() {
    }

    public S enrollStudent(S student) {
        this.course.put(student, null);
        return student;
    }

    public G assignGrade(S identifier, G grade) {
        if(this.course.containsKey(identifier)){
           return this.course.put(identifier, grade);
        }
        return grade;
    }

    public Optional<G> getGrade(S identifier) {
        if(course.containsKey(identifier)) {
            return Optional.of(this.course.get(identifier));
        }
        return Optional.empty();
    }


    public void listStudentWithGrade() {
        course.forEach((k, v) -> {
            System.out.println(k + ":" + v);
        });
    }

    public static void main(String[] args) {

        Course<String, Double> stringDoubleCourse = new Course<>();
        stringDoubleCourse.enrollStudent("Gaurav");
        stringDoubleCourse.assignGrade( "Gaurav", 90.00);
        stringDoubleCourse.getGrade("Gaurav");

        stringDoubleCourse.enrollStudent("Nippa");
        stringDoubleCourse.assignGrade("Nippa", 85.00);
        stringDoubleCourse.getGrade("Nippa");
        stringDoubleCourse.listStudentWithGrade();
    }

    public Map<S, G> getAllGrades() {
            return this.course;
    }

    public boolean isStudentEnrolled(S i) {
        return this.course.containsKey(i);
    }

    public List<G> listAllGrades() {
        Set<S> allKeys = this.course.keySet();
        List<G> listOfGrades = new ArrayList<>();
        for (S k : allKeys) {
           listOfGrades.add(this.course.get(k));
        }
        return listOfGrades;
    }
}
