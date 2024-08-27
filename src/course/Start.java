package course;

public class Start {
    public static void main(String[] args) {
        Course<String,Number> math = new Course<>();
        Course<String,Number> phy = new Course<>();


        math.addStudentToGrade("Abhay",65);
        phy.addStudentToGrade("Abhay",89);

        math.printStudentGrade("Abhay");

        System.out.println(math.toString());

    }
}
