package problems.generics.gradeManager;

import java.lang.reflect.Array;

public class GradeBook<T extends Number>{
    private T[] gradeBookArray;
    private int currentSize = 0;

    public GradeBook() {
        this.gradeBookArray = (T[]) new Number[10]; // Default size of 10
    }

    public GradeBook(Class<T> clazz, int arraySize) {
        this.gradeBookArray = (T[]) Array.newInstance(clazz, arraySize);
    }

    public void addGrade(T grade) {
        if (currentSize < gradeBookArray.length) {
            gradeBookArray[currentSize++] = grade;
        } else {
            System.out.println("Grade book is full.");
        }
    }

    public int getNumberOfGrades() {
        return currentSize;
    }

    public double getAverage() {
        double sum = 0;
        for (int i = 0; i < currentSize; i++) {
            sum += gradeBookArray[i].doubleValue();
        }
        return currentSize > 0 ? sum / currentSize : 0;
    }

    public String calculateAverage() {
        if (currentSize == 0) {
            return "No grades available to calculate the average.";
        }
        double average = getAverage();
        return "Average grade: " + average;
    }

    public String findHighestGrade() {
        if (currentSize == 0) {
            return "No grades available to find the highest grade.";
        }

        double highest = gradeBookArray[0].doubleValue();
        for (int i = 1; i < currentSize; i++) {
            double gradeValue = gradeBookArray[i].doubleValue();
            if (gradeValue > highest) {
                highest = gradeValue;
            }
        }
        return "Highest grade: " + (int) Math.round(highest);
    }

    public String findLowestGrade() {
        if (currentSize == 0) {
            return "No grades available to find the lowest grade.";
        }

        double lowest = gradeBookArray[0].doubleValue();
        for (int i = 1; i < currentSize; i++) {
            double gradeValue = gradeBookArray[i].doubleValue();
            if (gradeValue < lowest) {
                lowest = gradeValue;
            }
        }
        return "Lowest grade: " + (int) Math.round(lowest);
    }
}
