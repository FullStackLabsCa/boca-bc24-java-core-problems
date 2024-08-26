package problems.generics.coursegrade;

public class Main {

    public static void main(String[] args) {
        Course<String, Double> course = new Course<>();

        course.enrollStudent("John");
        course.enrollStudent("Jason");
        course.enrollStudent("Jack");

        course.assignGrade("John", 4.0);
        course.assignGrade("Jason", 3.8);
        course.assignGrade("Jack", 3.9);

        System.out.println("Grade of John: " + course.retrieveGrade("John"));
        System.out.println("Grade of Jason: " + course.retrieveGrade("Jason"));
        System.out.println("Grade of Jack: " + course.retrieveGrade("Jack"));

        System.out.println("All grades: " + course.listAllGrades());
    }
}
