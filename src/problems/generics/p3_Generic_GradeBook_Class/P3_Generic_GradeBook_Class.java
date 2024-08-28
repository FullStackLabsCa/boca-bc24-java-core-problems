package problems.generics.p3_Generic_GradeBook_Class;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class P3_Generic_GradeBook_Class {
    public static void main(String[] args) {

        GradeBook<Integer> integerGradeBook = new GradeBook<>();
        integerGradeBook.addGrade(50);
        integerGradeBook.addGrade(100);
        integerGradeBook.addGrade(50);

        System.out.println("Integer");
        System.out.println("Average: " + integerGradeBook.calculateAverage());
        System.out.println("Highest value: " + integerGradeBook.highestGrade());
        System.out.println("Lowest Value: " + integerGradeBook.lowestGrade());

        GradeBook<Double> doubleGradeBook = new GradeBook<>();
        doubleGradeBook.addGrade(500.25);
        doubleGradeBook.addGrade(100.50);
        doubleGradeBook.addGrade(50.50);

        System.out.println("Double");
        System.out.println("Average: " + doubleGradeBook.calculateAverage());
        System.out.println("Highest value: " + doubleGradeBook.highestGrade());
        System.out.println("Lowest Value: " + doubleGradeBook.lowestGrade());


    }
}
