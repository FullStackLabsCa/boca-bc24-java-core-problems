package problems;

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
        GenericGradeBook<Integer> integerGenericGradeBook = new GenericGradeBook<>(integerList);

        System.out.println("Added grade 6 = " + integerGenericGradeBook.addGrade(6));
        System.out.println("Removed grade 7 = " + integerGenericGradeBook.removeGrade(7));
        System.out.println("Highest of Integer grade = " + integerGenericGradeBook.findHighestGrade());
        System.out.println("Lowest of Integer grade = " + integerGenericGradeBook.findLowestGrade());
        System.out.println("Average of Integer grade = " + integerGenericGradeBook.calcAvgGrade());


        //To Test Double GradeBook
        List<Double> doubleList = new ArrayList<>();
        doubleList.add(5.5);
        doubleList.add(3.9);
        doubleList.add(0.2);
        doubleList.add(7.0);
        doubleList.add(4.1);
        GenericGradeBook<Double> doubleGenericGradeBook = new GenericGradeBook<>(doubleList);

        System.out.println("Added grade 6.4 to Double gradeList = " + doubleGenericGradeBook.addGrade(6.4));
        System.out.println("Removed grade 7.8 to Double gradeList = " + doubleGenericGradeBook.removeGrade(7.8));
        System.out.println("Highest of Double gradeList = " + doubleGenericGradeBook.findHighestGrade());
        System.out.println("Lowest of Double gradeList = " + doubleGenericGradeBook.findLowestGrade());
        System.out.println("Average of Double gradeList = " + doubleGenericGradeBook.calcAvgGrade());
    }
}
