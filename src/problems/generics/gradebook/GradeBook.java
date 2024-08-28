package problems.generics.gradebook;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class GradeBook<T extends Number> {
    List<T> gradeList = new ArrayList<>();

    /*public Double sum(List<T> gradeList){
        T totalSum= 0;

        for(T grade: gradeList) {
            totalSum = totalSum + grade;
        }
        return totalSum;
    }*/

    public String calculateAverage(){
        double totalSum= 0;
        double avg= 0;

        if(gradeList.isEmpty()){
            return "No grades available to calculate the average.";
        }
        else {
            for (T grade : gradeList) {
                totalSum = totalSum + grade.doubleValue();
            }
            avg = totalSum / gradeList.size();
        }
        return "Average grade: "+avg;
    }

    public String findLowestGrade() {
        if (gradeList.isEmpty()) {
            return "No grades available to find the lowest grade.";
        } else {
            T min = gradeList.get(0);

            for (T grade : gradeList) {
                if (grade.doubleValue() < min.doubleValue()) {
                    min = grade;
                }
            }
            return "Lowest grade: " + min;
        }
    }

    public String findHighestGrade() {
        if (gradeList.isEmpty()) {
            return "No grades available to find the highest grade.";
        } else {
            T max = gradeList.get(0);


            for (T grade : gradeList) {
                if (grade.doubleValue() > max.doubleValue()) {
                    max = grade;
                }
            }
            return "Highest grade: " + max;
        }
    }

    public void addGrade(T v) {
        gradeList.add(v);
    }

    public <T extends Number> int getNumberOfGrades() {

        return gradeList.size();
    }
}