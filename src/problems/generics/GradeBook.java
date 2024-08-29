package problems.generics;

import java.util.*;

public class GradeBook<T extends Number & Comparable<T>> {

    private final List<T> grades;

    public GradeBook() {
        this.grades = new ArrayList<>();
    }

    public void addGrade(T grade) {
        grades.add(grade);
    }

    public String calculateAverage() {
        double average = Double.MIN_VALUE;
        if (!grades.isEmpty()) {
            average = 0;
            for (T grade : grades) {
                average = average + grade.doubleValue();
            }

            return "Average grade: " + String.format("%.1f", average / this.grades.size());
        }

        return "No grades available to calculate the average.";
    }

    public String findHighestGrade() {
        if (!grades.isEmpty()) {
            return "Highest grade: " + Collections.max(grades).toString();
        }

        return "No grades available to find the highest grade.";
    }

    public int getNumberOfGrades() {
        return this.grades.size();
    }

    public String findLowestGrade() {
        if (!grades.isEmpty()) {
            return "Lowest grade: " + Collections.min(grades).toString();
        }

        return "No grades available to find the lowest grade.";
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        GradeBook<Integer> grades = new GradeBook<>();

        System.out.println("Please enter grades of 10 students: ");
        for (int i = 0; i < 10; i++) {
            grades.addGrade(s.nextInt());
        }

        System.out.println(grades.calculateAverage());
        System.out.println(grades.findHighestGrade());
        System.out.println(grades.findLowestGrade());
    }
}
