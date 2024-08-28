package problems.genrics;

import java.util.ArrayList;
import java.util.List;

public class GradeBook<T extends Number & Comparable<T>> {


    private List<T> gradeList;

    public GradeBook() {
        gradeList = new ArrayList<>();
    }

    public void addGrade(T grade) {
        gradeList.add(grade);
    }

    public  String calculateAverage() {
        Double sum = 0.0;
        if (gradeList.size()>0) {
            for (T element : gradeList) {
                sum = sum + element.doubleValue();
            }
            return "Average grade: "+sum / gradeList.size();
        }
        else{
            return "No grades available to calculate the average.";
        }
    }


    public  String findHighestGrade() {

        if (gradeList.size()>0) {
            T highest = gradeList.get(0);
            for (T element : gradeList) {
                if (element.compareTo(highest) > 0) {
                    highest = element;
                }
            }
            return "Highest grade: "+ highest;
        }
        else {
            return "No grades available to find the highest grade.";
        }

    }

    public  String findLowestGrade() {

        if (gradeList.size()>0) {
            T lowest = (T) gradeList.get(0);
            for (T element : gradeList) {
                if (element.compareTo(lowest) < 0) {
                    lowest = element;
                }
            }
            return "Lowest grade: "+lowest;
        }
        else {
            return "No grades available to find the lowest grade.";
        }


    }
    public static void main(String[] args) {
        GradeBook<Integer> gradebook = new GradeBook<>();
        gradebook.addGrade(10);
        gradebook.addGrade(20);
        gradebook.addGrade(30);
        gradebook.addGrade(40);
        gradebook.addGrade(40);
        gradebook.addGrade(50);
        System.out.println("Highest: "+gradebook.findHighestGrade());
        System.out.println("Lowest: "+gradebook.findLowestGrade());
        System.out.println("Average: "+gradebook.calculateAverage());


    }

    public int getNumberOfGrades() {
        return gradeList.size();
    }
}



