package problems.genrics;

import java.util.ArrayList;
import java.util.List;

public class GradebokRunner {
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
