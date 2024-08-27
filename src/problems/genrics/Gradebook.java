package problems.genrics;

import java.util.ArrayList;
import java.util.List;

public class Gradebook<T extends Number & Comparable<T>> {


    private List<T> gradeList;

    public Gradebook() {
        gradeList = new ArrayList<>();
    }

    public void addGrade(T grade) {
        gradeList.add(grade);
    }

    public  Double calculateAverage() {
        Double sum = 0.0;
        for (T element : gradeList) {
            sum = sum +  element.doubleValue();
        }
        return sum / gradeList.size();
    }


    public  T highestGrade() {
        T highest = gradeList.get(0);
        for (T element : gradeList) {
            if (element.compareTo(highest) > 0) {
                highest = element;
            }
        }
        return highest;
    }

    public  T lowestGrade() {
        T lowest = (T) gradeList.get(0);
        for (T element : gradeList) {
            if (element.compareTo(lowest) < 0) {
                lowest = element;
            }
        }
        return lowest;
    }
    public static void main(String[] args) {
        Gradebook<Integer> gradebook = new Gradebook<>();
        gradebook.addGrade(10);
        gradebook.addGrade(20);
        gradebook.addGrade(30);
        gradebook.addGrade(40);
        gradebook.addGrade(40);
        gradebook.addGrade(50);
        System.out.println("Highest: "+gradebook.highestGrade());
        System.out.println("Lowest: "+gradebook.lowestGrade());
        System.out.println("Average: "+gradebook.calculateAverage());


    }

}



