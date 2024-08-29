package course;

public class Start {
    public static void main(String[] args) {
        Course<String,Number> math = new Course<>();
        Course<String,Number> phy = new Course<>();


        math.enrollStudent("Abhay");
        phy.enrollStudent("Abhay");

        math.printStudentGrade("Abhay");

        System.out.println(math.toString());

    }
}
