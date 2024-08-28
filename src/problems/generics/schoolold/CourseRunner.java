package generics.schoolold;

public class CourseRunner {
    public static void main(String[] args) {
        Course<String, Double> course = new Course<>();

        // Enroll student in a school
        course.enrollStudent("so1");
        course.enrollStudent("so2");

        // Assign course to the student
        course.assignCourse("so1", "java-fall-2024");
        course.assignCourse("so1", "reactjs-fall-2024");
        course.assignCourse("so2", "java-winter-2025");


        course.listGrades();

        // Assign grade to the student
        course.assignGrade("so1", "java-fall-2024", Double.valueOf(25));
        course.assignGrade("so2", "java-winter-2025", Double.valueOf(50));

        System.out.println("Get student grade" + course.retrieveGrade("so1", "java-fall-2024"));
        System.out.println("Grade lists====");

        course.listGrades();
    }
}
