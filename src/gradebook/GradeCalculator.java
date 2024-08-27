package gradebook;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GradeCalculator {
    public static void main(String[] args) {
        Scanner scanner =new Scanner(System.in);
        System.out.println("Enter the number of grades you want");
    int num = scanner.nextInt();
        List<Double> grades = new ArrayList<>();
        System.out.println("Enter the grades:- ");
    for(int i=0;i<num;i++){
        double grade = scanner.nextInt();
        grades.add(grade);
    }

        GradeBook<Double> gradeBook = new GradeBook<>(grades);
        System.out.println(gradeBook.toString());

        System.out.println("Total marks:- "+gradeBook.totalMarks(grades));

    }
}
