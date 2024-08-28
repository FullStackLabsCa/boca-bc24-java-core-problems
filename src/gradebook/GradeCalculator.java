package gradebook;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GradeCalculator {
    public static void main(String[] args) {
        Scanner scanner =new Scanner(System.in);

        List<Double> grades = new ArrayList<>();

        GradeBook<Double> gradeBook = new GradeBook<>(grades);
        while(true){
            System.out.println("Enter the grade:- ");
            double grade = scanner.nextInt();
            if(grade==0) {
                break;
            }else {
                gradeBook.addGrade(grade);
            }
        }

        System.out.println("Average grade: "+gradeBook.calculateAverage());

        System.out.println("Total marks:- "+gradeBook.totalMarks(grades));

    }
}
