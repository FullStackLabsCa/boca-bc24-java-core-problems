package problems.generic.grade;

/*Problem 3: Generic GradeBook Class
        Objective:
        Design and implement a generic class GradeBook that is capable of storing and managing the grades of students.
        The GradeBook class should be versatile, allowing it to work with any numeric type, such as Integer, Double, Float,
        etc. This class will provide essential functionalities like adding grades, calculating the average grade, and determining
        the highest and lowest grades.

        Adding Grades: The class should support adding individual grades of type T to the grade book.
        Calculating the Average: The class should provide a method to calculate and return the average of all the grades stored
        in the grade book. This average should be returned as a double value.
        Finding the Highest Grade: The class should offer a method to find and return the highest grade in the grade book.
        Finding the Lowest Grade: Similarly, the class should provide a method to find and return the lowest grade in the grade book.
        */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GradeBook<T extends Number & Comparable<T>> {
    private List<T> grades = new ArrayList<>();

    public int getNumberOfGrades() {
        return grades.size();
    }

    public GradeBook(List<T> grades) {
        this.grades = grades;
    }

    public GradeBook() {}

    public T addGrade(T grade) {
        this.grades.add(grade);
        return grade;
    }

    public String calculateAverage() {

        if (grades.isEmpty()) return "No grades available to calculate the average." ;
        double sum = 0;
        for (T g : grades) {
            sum += Double.parseDouble(String.valueOf(g));
        }
        String size = String.valueOf(sum/grades.size());
        return "Average grade: " +size;
    }

    public String findHighestGrade() {
        if (grades== null || grades.isEmpty()) return "No grades available to find the highest grade.";
        T highestGrade = grades.get(0);
        for (T grade : grades) {
            if (grade.compareTo(highestGrade) > 0) {
                highestGrade = grade;
            }
        }
        return "Highest grade: "+ String.valueOf(highestGrade);
    }

    public String findLowestGrade() {
        if (grades== null || grades.isEmpty()) return "No grades available to find the lowest grade.";

        T lowestGrade = grades.get(0);

        for (T grade : grades) {
            if (grade.compareTo(lowestGrade) < 0) {
                lowestGrade = grade;
            }
        }
        return "Lowest grade: "+ String.valueOf(lowestGrade);
    }

    public static void main(String[] args) {
        List<Double> list =new ArrayList<>(Arrays.asList(3.4, 3.5, 2.9, 4.0, 3.9));
        GradeBook<Double> doubleGradeBook = new GradeBook<>(list);
        doubleGradeBook.addGrade(3.3);
        System.out.println("doubleGradeBook.calculateAverageGrade() = " + doubleGradeBook.calculateAverage());
        System.out.println("  doubleGradeBook.findHighestGrade() = " + doubleGradeBook.findHighestGrade());
        System.out.println("  doubleGradeBook.findHighestGrade() = " + doubleGradeBook.findLowestGrade());

        List<Integer> integerList =new ArrayList<>(Arrays.asList(50, 59, 80, 95, 60, 78));
        GradeBook<Integer> integerGradeBook = new GradeBook<>(integerList);
        integerGradeBook.addGrade(40);
        System.out.println("integerGradeBook.calculateAverageGrade() = " + integerGradeBook.calculateAverage());
        System.out.println("  integerGradeBook.findHighestGrade() = " + integerGradeBook.findHighestGrade());
        System.out.println("  integerGradeBook.findHighestGrade() = " + integerGradeBook.findLowestGrade());
    }
}
