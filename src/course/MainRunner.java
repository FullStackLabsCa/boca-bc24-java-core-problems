package course;

public class MainRunner {
    public static void main(String[] args) {
        Course<String,Number> math = new Course<>();
        Course<String,Number> physics = new Course<>();


        math.enrollStudent("Mann");
        physics.enrollStudent("Dhruv");

        math.assignGrade("Mann",98);
        physics.assignGrade("Mann",97);

        math.assignGrade("Dhruv",95);
        physics.assignGrade("Dhruv",90);
//
//        math.printStudentGrade("Mann");
//        physics.printStudentGrade("Dhruv");

        System.out.println(math.toString());


        System.out.println("SCHOOL");
        School<String,Number> school = new School<>();
        System.out.println(school.addCourseNameToSchool("math"));
//        System.out.println(school.enrollStudent("Mann",math));


//
   }
}
