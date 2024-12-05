package problems.generics.grade_book;

import java.util.ArrayList;
import java.util.List;

public class GradeBook<T extends Number> {
    private List<T> gradesList;

    @Override
    public String toString() {
        return "gradesList = " + gradesList;
    }

    public GradeBook() {
        this.gradesList = new ArrayList<>();
    }

    public void addGrade(T grades) {
        gradesList.add(grades);
    }

    public int getNumberOfGrades() {
        return gradesList.size();
    }

    public String calculateAverage() {
        if (gradesList.isEmpty()) {
            return ("No grades available to calculate the average.");
        }
        double sum = 0;
        double average = 0;
        for (int i = 0; i < gradesList.size(); i++) {
            sum += gradesList.get(i).doubleValue();
        }
        average = sum / gradesList.size();
        return ("Average grade: " + average);

    }


    public String findHighestGrade() {
        if (gradesList.isEmpty()) {
            return ("No grades available to find the highest grade.");
        } else {
            T highest = gradesList.get(0);

            for (T highestInList : gradesList) {
                if (highestInList.doubleValue() > highest.doubleValue()) {
                    highest = highestInList;
                }
            }
            return ("Highest grade: " + highest);
        }
    }

    public String findLowestGrade() {
        if (gradesList.isEmpty()) {
            return ("No grades available to find the lowest grade.");
        } else {
            T lowestInt = gradesList.get(0);
            for (T lowestIntInList : gradesList) {
                if (lowestIntInList.intValue() < lowestInt.intValue()) {
                    lowestInt = lowestIntInList;
                    System.out.println("Lowest grade is: " + lowestIntInList);

                }
            }
            return ("Lowest grade: " + lowestInt);
        }
    }

    public static void main(String[] args) {
        GradeBook<Double> gradesList = new GradeBook<>();
        gradesList.addGrade(85.0);
        gradesList.addGrade(90.0);
        gradesList.addGrade(95.0);
        //gradesList.addGrade(null);


        GradeBook<Integer> integerGradeList = new GradeBook<>();
        integerGradeList.addGrade(85);
        integerGradeList.addGrade(90);
        integerGradeList.addGrade(95);


        System.out.println(gradesList);
        System.out.println(integerGradeList);
        System.out.println(gradesList.calculateAverage());
        System.out.println(gradesList.findHighestGrade());
        System.out.println(gradesList.findLowestGrade());
        System.out.println(integerGradeList.findHighestGrade());
        System.out.println(integerGradeList.calculateAverage());
        System.out.println(integerGradeList.findLowestGrade());
    }
}


