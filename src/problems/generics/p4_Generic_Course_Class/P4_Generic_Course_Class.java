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

        System.out.println("Krishna " + course.getGrade("Krishna"));
        System.out.println("Govardhan " + course.getGrade("Govardhan"));
        System.out.println("Dwarkadhish " + course.getGrade("Dwarkadhish"));
        System.out.println("TempName " + course.getGrade("TempName"));

        // List all students and their grades
        course.listAllGrade();
    }
}