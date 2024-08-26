package problems;

import javax.security.auth.Subject;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        List<Double> intGradeList = new ArrayList<>();
        intGradeList.add(78.0);
        intGradeList.add(89.0);
        intGradeList.add(93.0);
        GradeBook gradeBook = new GradeBook();

        System.out.println("WELCOME TO GRADE BOOK      ");
        System.out.println("      MENU       ");
        System.out.println("Enter 1 for Sum Of All Grades");
        System.out.println("Enter 2 for Average Of All Grades");
        System.out.println("Enter 3 for Min Of All Grades");
        System.out.println("Enter 4 for Max Of All Grades");

        int option = 0;
        Scanner scanner = new Scanner(System.in);
        option = scanner.nextInt();

        switch (option){
            case 1:
                System.out.println("Sum of all grades => " + gradeBook.sumOfGrades(intGradeList));
                break;
            case 2:
                System.out.println("Average of all grades => " + gradeBook.averageOfGrades(intGradeList));
                break;
            case 3:
                System.out.println("Min of all grades => " + gradeBook.minOfGrades(intGradeList));
                break;
            case 4:
                System.out.println("Max of all grades => " + gradeBook.maxGrade(intGradeList));
                break;
            default:
                System.out.println("Invalid outpu!!!!");
        }

    }
}
