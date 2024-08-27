package generics;

import java.util.ArrayList;
import java.util.List;

public class Gradebook<T extends Number> {
    List<T> grades = new ArrayList<>();

    public double calculateAverageGrades(){
        double average=0;
        for(T grade: grades){
            average = average+ grade.doubleValue();
        }
        System.out.print("The Average Score is: ");
        return average/grades.size();
    }

    public double highestGrade(){
        double highest = grades.get(0).doubleValue();
        for(T grade: grades){
            if(grade.doubleValue() > highest){
                highest = grade.doubleValue();
            }
        }
        System.out.print("The Highest Score is: ");
        return highest;
    }

    public double lowestGrade(){
        double lowest = grades.get(0).doubleValue();
        for(T grade: grades)
            if (grade.doubleValue() < lowest) {
                lowest = grade.doubleValue();
            }
        System.out.print("The Lowest Score is: ");
        return lowest;
    }

    public void addGrades(T i) {
        grades.add(i);
    }
}
