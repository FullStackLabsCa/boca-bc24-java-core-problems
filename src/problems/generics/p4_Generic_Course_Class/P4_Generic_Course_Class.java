package problems.generics.p4_Generic_Course_Class;

public class P4_Generic_Course_Class {
    public static void main(String[] args) {

        Course<String, Integer> course = new Course<>();

        // Enroll Students
        course.enrollStudent("Krishna");
        course.enrollStudent("Govardhan");
        course.enrollStudent("Dwarkadhish");

        // Assign grade
        course.assignGrade("Krishna", 100);
        course.assignGrade("Dwarkadhish", 95);

        // Assign grade to not enrolled student
        course.assignGrade("TempName", 50);

        System.out.println("***** call getAllGrades() *****");
        System.out.println("Krishna " + course.getAllGrades("Krishna"));
        System.out.println("Govardhan " + course.getAllGrades("Govardhan"));
        System.out.println("Dwarkadhish " + course.getAllGrades("Dwarkadhish"));
        System.out.println("TempName " + course.getAllGrades("TempName"));

        // List all students and their grades
        System.out.println("***** Call listAllGrade() *****");
        course.listAllGrades();
    }
}