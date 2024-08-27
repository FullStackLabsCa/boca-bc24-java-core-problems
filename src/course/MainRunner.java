package course;

public class MainRunner {
    public static void main(String[] args) {
        Course<String,Number> math = new Course<>();
        Course<String,Number> phy = new Course<>();


        math.addStudentToGrade("Mann",53);
        phy.addStudentToGrade("Mann",29);

        math.printStudentGrade("Mann");
        phy.printStudentGrade("Mann");

        System.out.println(math.toString());


        System.out.println("SCHOOL");
        School<String,Number> school = new School<>();
        System.out.println(school.addCourseNameToSchool("math"));
        System.out.println(school.enrollStudent("math",phy));

    }
}
