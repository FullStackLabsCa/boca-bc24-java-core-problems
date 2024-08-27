package generic;

public class Launcher {
    public static void main(String[] args) {
        GradeBook<Integer> gradeBook = new GradeBook<>();
        gradeBook.add(1);
        gradeBook.add(2);
        gradeBook.add(3);
        gradeBook.add(4);
        gradeBook.add(5);

        System.out.println("Grades of the course is : " + gradeBook.grades);
        System.out.println("Maximum grade is : " + gradeBook.findMax());
        System.out.println("Minimum grade is : " + gradeBook.findMin());
        System.out.println("Average grades of the course is :" + gradeBook.average());
    }
}
