package generics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class GradeBook<T extends Number & Comparable> {
    List<T> grades;

    public GradeBook() {
        this.grades = new ArrayList<>();
    }

    public String calculateAverage(){
        if (grades == null || grades.isEmpty()) {
            return "No grades available to calculate the average.";
        }else{
            double average = 0;
            for(T grade: grades){
                average += grade.doubleValue();
            }
            average = average/grades.size();
            return "Average grade: " + Double.valueOf(average);
        }

    }

    public String findHighestGrade(){
        if (grades == null || grades.isEmpty()) {
            return "No grades available to find the highest grade.";
        } else {
            T highest = grades.get(0);
            for (T grade: grades){
                if(grade.compareTo(highest) > 0){
                    highest = grade;
                }
            }
            return "Highest grade: " + grades.get(grades.size()-1).toString();
        }
    }

    public String findLowestGrade() {

        if (grades == null || grades.isEmpty()) {
            return "No grades available to find the lowest grade.";
        } else {
            T lowest = grades.get(0);
            for (T grade: grades){
                if(grade.compareTo(lowest) < 0){
                    lowest = grade;
                }
            }
            return "Lowest grade: " + grades.get(0).toString();
        }
    }

    public void addGrade(T i) {
        grades.add(i);
    }

    public int getNumberOfGrades() {
        return grades.size();
    }
}
