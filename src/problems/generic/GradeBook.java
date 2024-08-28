package problems.generic;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("java:S106")
public class GradeBook<T extends Number & Comparable<T>>{
    private List<T> gradesList;

    public GradeBook() {
        this.gradesList = new ArrayList<>();
    }

    public void addGrade(T grade) {
        this.gradesList.add(grade);
    }

    public int getNumberOfGrades() {
        return this.gradesList.size();
    }

    public String calculateAverage() {
        if(gradesList.isEmpty()) return "No grades available to calculate the average.";
        double sum = 0;
        for (T value : gradesList) {
            sum += Double.parseDouble(String.valueOf(value));
        }
        double average = sum / gradesList.size();
        System.out.println("Average grade: " + average);
        return "Average grade: " + average;
    }

    @Override
    public String toString() {
        return "Grade Book{" +
                "gradesList=" + gradesList +
                '}';
    }

    public String findLowestGrade() {
        if(gradesList.isEmpty()) return "No grades available to find the lowest grade.";
        T minValue = gradesList.get(0);

        for (T value : gradesList) {
            if (value.compareTo(minValue)<0) {
                minValue = value;
            }
        }
        return "Lowest grade: " + minValue;
    }

    public String findHighestGrade() {
        if(gradesList.isEmpty()) return "No grades available to find the highest grade.";

        T maxValue = gradesList.get(0);

        for (T value : gradesList) {
            if (value.compareTo(maxValue) > 0) {
                maxValue = value;
            }
        }
        return "Highest grade: " + maxValue;
    }

    public static void main(String[] args) {
        GradeBook<Integer> integerGradeBook = new GradeBook<>();
        integerGradeBook.addGrade(89);
        integerGradeBook.addGrade(78);
        integerGradeBook.addGrade(75);
        integerGradeBook.addGrade(98);
        integerGradeBook.addGrade(92);
        integerGradeBook.addGrade(70);

        System.out.println("integerGradeBook = " + integerGradeBook);

        integerGradeBook.calculateAverage();

        System.out.println("findLowestValue = " + integerGradeBook.findLowestGrade());
        System.out.println("findHighestValue = " + integerGradeBook.findHighestGrade());
    }
}
