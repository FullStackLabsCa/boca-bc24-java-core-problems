package generics;

public class Teacher{
    public static void main(String[] args) {


        Gradebook<Integer> student1 = new Gradebook<>();
        student1.addGrades(100);
        student1.addGrades(200);
        student1.addGrades(300);
        System.out.println(student1.calculateAverageGrades());
        System.out.println(student1.highestGrade());
        System.out.println(student1.lowestGrade());

        Course<String, Integer> student = new Course<>();
        student.addStudent("Ankit");
        student.addGrade("Ankit", 20);
        student.addStudent("Joshi");
        student.addGrade("Joshi", 40);
        student.addStudent("John");
        student.addGrade("John", 80);

        student.getAllStudents();

    }
}
