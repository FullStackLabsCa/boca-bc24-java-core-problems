package problems.generics;

import java.util.ArrayList;
import java.util.List;

public class GradeBookRunner {
    public static void main(String[] args) {
        //To Test integer GradeBook
        List<Integer> integerList = new ArrayList<>();
        integerList.add(5);
        integerList.add(9);
        integerList.add(2);
        integerList.add(7);
        integerList.add(4);
        GradeBook<Integer> integerGradeBook = new GradeBook<>(integerList);

        System.out.println("Added grade 6 = " + integerGradeBook.addGrade(6));
        System.out.println("Removed grade 7 = " + integerGradeBook.removeGrade(7));
        System.out.println("Highest of Integer grade = " + integerGradeBook.findHighestGrade());
        System.out.println("Lowest of Integer grade = " + integerGradeBook.findLowestGrade());
        System.out.println("Average of Integer grade = " + integerGradeBook.calcAvgGrade());


        //To Test Double GradeBook
        List<Double> doubleList = new ArrayList<>();
        doubleList.add(5.5);
        doubleList.add(3.9);
        doubleList.add(0.2);
        doubleList.add(7.0);
        doubleList.add(4.1);
        GradeBook<Double> doubleGradeBook = new GradeBook<>(doubleList);

        System.out.println("Added grade 6.4 to Double gradeList = " + doubleGradeBook.addGrade(6.4));
        System.out.println("Removed grade 7.8 to Double gradeList = " + doubleGradeBook.removeGrade(7.8));
        System.out.println("Highest of Double gradeList = " + doubleGradeBook.findHighestGrade());
        System.out.println("Lowest of Double gradeList = " + doubleGradeBook.findLowestGrade());
        System.out.println("Average of Double gradeList = " + doubleGradeBook.calcAvgGrade());
    }
}
