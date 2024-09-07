package genericsproblems;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GradeBook<T extends Number> {

    private List<T> grades;

    public GradeBook() {
        grades = new ArrayList<>();
    }


    public void addGrade(T grade) {
        grades.add(grade);
    }

    public Integer getNumberOfGrades() {
        return grades.size();
    }


    public String calculateAverage() {
        if (grades == null || grades.isEmpty()) {
            return "No grades available to calculate the average.";
        }
        double avg = 0.0;
        for (T t : grades) {
            avg += t.doubleValue() / grades.size();
        }
        return "Average grade: "+avg;
    }


    public String findHighestGrade() {

        if (grades == null ||grades.isEmpty()) {
            return "No grades available to find the highest grade.";
        }
        T highestgrade = grades.get(0);
        for (T grade : grades) {
            if (grade.doubleValue() > highestgrade.doubleValue()) {
                highestgrade = grade;
            }
        }
        return "Highest grade: "+highestgrade;
    }


    public String findLowestGrade() {

        if (grades == null || grades.isEmpty()) {
            return "No grades available to find the lowest grade.";
        }
        T lowestgrade = grades.get(0);
        for (T grade : grades) {
            if (grade.doubleValue() < lowestgrade.doubleValue()) {
                lowestgrade = grade;
            }
        }
        return "Lowest grade: " +lowestgrade;
    }

    public static void main(String[] args) {

        GradeBook<Integer> integerGradeBook = new GradeBook<>();

        integerGradeBook.addGrade(85);
        integerGradeBook.addGrade(90);
        integerGradeBook.addGrade(95);


        System.out.println("List Of grades: " + integerGradeBook.getNumberOfGrades());
        System.out.println("Calculate the average:  " + integerGradeBook.calculateAverage());
        System.out.println("Find the highest grade: " + integerGradeBook.findHighestGrade());
        System.out.println("Find the lowest grade: " + integerGradeBook.findLowestGrade());


        GradeBook<Double> doubleGradeBook = new GradeBook<>();

        doubleGradeBook.addGrade(85.00);
        doubleGradeBook.addGrade(90.00);

        System.out.println("List of Grades: " + doubleGradeBook.getNumberOfGrades());
        System.out.println("Calculate the average:  " + doubleGradeBook.calculateAverage());
        System.out.println("Find the highest grade: " + doubleGradeBook.findHighestGrade());
        System.out.println("Lowest grade: " + doubleGradeBook.findLowestGrade());


    }

}

