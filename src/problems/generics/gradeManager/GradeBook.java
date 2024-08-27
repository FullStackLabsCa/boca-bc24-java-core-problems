package problems.generics.gradeManager;

import java.lang.reflect.Array;

public class GradeBook<T extends Number>{
    public T[] gradeBookArray;
    private int currentSize = 0;

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

    public double getAverage() {
        double sum = 0;
        double average = 0;

        if (gradeBookArray != null) {
            for(int i = 0; i<gradeBookArray.length ;i++){
                sum = sum+ (double) gradeBookArray[i];
            }
            System.out.println(sum);
            average = sum/(gradeBookArray.length);
            System.out.println(average);
        }
        return average;
    }

    public double findHighestGrade() {
        if (gradeBookArray == null || gradeBookArray.length == 0) {
            System.out.println("Grade book is empty or null");
        }

        double highest = (Double) gradeBookArray[0];
        for (int i = 1; i < gradeBookArray.length; i++) {
            if ((Double)gradeBookArray[i] > highest)
                highest = (double) gradeBookArray[i];
        }

//        System.out.println("Highest grade: " + highest);
        return highest;
        }



    double findLowestGrade(){
        if (gradeBookArray == null || gradeBookArray.length == 0) {
            System.out.println("Grade book is empty or null");
        }

        double lowest = (Double) gradeBookArray[0];
        for (int i = 1; i < gradeBookArray.length; i++) {
            if ((Double)gradeBookArray[i] < lowest)
                lowest = (double) gradeBookArray[i];
        }        return lowest;
    }
}
