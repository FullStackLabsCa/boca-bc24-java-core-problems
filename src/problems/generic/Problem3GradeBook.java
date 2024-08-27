package problems.generic;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("java:S106")
public class Problem3GradeBook<T extends Number> {
    private List<T> gradesList;

    public Problem3GradeBook() {
        this.gradesList = new ArrayList<>();
    }

    public void addGrade(T grade) {
        this.gradesList.add(grade);
    }

    public void calculateAverage() {
        double sum = 0;
        for (T value : gradesList) {
            sum += Double.parseDouble(String.valueOf(value));
        }
        System.out.println("average = " + sum / gradesList.size());
    }

    @Override
    public String toString() {
        return "Grade Book{" +
                "gradesList=" + gradesList +
                '}';
    }

    public double findLowestValue() {
        double minValue = Double.parseDouble(String.valueOf(gradesList.get(0)));

        for (T value : gradesList) {
            if (minValue > Double.parseDouble(String.valueOf(value))) {
                minValue = Double.parseDouble(String.valueOf(value));
            }
        }
        return minValue;
    }

    public double findHighestValue() {
        double maxValue = Double.parseDouble(String.valueOf(gradesList.get(0)));

        for (T value : gradesList) {
            if (maxValue < Double.parseDouble(String.valueOf(value))) {
                maxValue = Double.parseDouble(String.valueOf(value));
            }
        }
        return maxValue;
    }

    public static void main(String[] args) {
        Problem3GradeBook<Integer> integerGradeBook = new Problem3GradeBook<>();
        integerGradeBook.addGrade(89);
        integerGradeBook.addGrade(78);
        integerGradeBook.addGrade(75);
        integerGradeBook.addGrade(98);
        integerGradeBook.addGrade(92);
        integerGradeBook.addGrade(70);

        System.out.println("integerGradeBook = " + integerGradeBook);

        integerGradeBook.calculateAverage();

        System.out.println("findLowestValue = " + integerGradeBook.findLowestValue());
        System.out.println("findHighestValue = " + integerGradeBook.findHighestValue());
    }
}
