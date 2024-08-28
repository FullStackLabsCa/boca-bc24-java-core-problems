package genricproblem;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GradeBook<T extends Number & Comparable> {

    private List<T> grades;// type of  grades

    public GradeBook() {
        grades = new ArrayList<>();
    } // list of grades

    public void addGrade(T grade) {
        grades.add(grade);
    }

    public int getNumberOfGrades() {
        return this.grades.size();
    }

    public String calculateAverage() {
        if (grades.isEmpty()) {
            return "No grades available to calculate the average.";
        }
        double sum = 0;
        double average = 0;
        for (T grade : grades) {
            sum += grade.doubleValue();
            average = sum / grades.size();
        }
        return "Average grade: " + average;
    }

    public String findHighestGrade() {
        if (grades.isEmpty()) {
            return "No grades available to find the highest grade.";
        }
        T highest = grades.get(0);
        for (T grade : grades) {
            if (grade.compareTo(highest) > 0) {
                highest = grade;
            }
        }
        return "Highest grade: " + highest;
    }

    public String findLowestGrade() {
        if (grades.isEmpty()) {
            return "No grades available to find the lowest grade.";
        }
        T lowest = grades.get(0);
        for (T grade : grades) {
            if (grade.compareTo(lowest) < 0) {
                lowest = grade;
            }
        }
        return "Lowest grade: " + lowest;
    }

    public static void main(String[] args) {
        GradeBook<Integer> grade = new GradeBook<>();
        grade.addGrade(54);
        grade.addGrade(56);
        grade.addGrade(23);
        grade.addGrade(22);
        System.out.println(grade.calculateAverage());
        System.out.println(grade.findHighestGrade());
        System.out.println(grade.findLowestGrade());

        GradeBook<Double> grade1 = new GradeBook<>();
        grade1.addGrade(2.4);
        grade1.addGrade(5.6);
        grade1.addGrade(2.3);
        grade1.addGrade(2.2);
        System.out.println(grade1.calculateAverage());
        System.out.println(grade1.findHighestGrade());
        System.out.println(grade1.findLowestGrade());
    }

}

