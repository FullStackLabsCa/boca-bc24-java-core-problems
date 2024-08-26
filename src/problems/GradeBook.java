package problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GradeBook <T extends Number> {
    private List<T> gradelist = new ArrayList<>();

    public  double sumOfGrades(List <T> gradelist){
        double totalSum = 0;
        for(T grade : gradelist){
            totalSum += grade.doubleValue();
        }
        return totalSum;
    }

    public  double averageOfGrades(List <T> gradelist){
        double avg = 0;
        double totalSum = 0;
        int count = 0;
        for(T grade : gradelist){
            totalSum += grade.doubleValue();
            count++;
        }
        avg = totalSum / count;
        return avg;
    }

    public  double minOfGrades(List <Double> gradelist){
        double minGrade = 0;
        for(Double grade : gradelist){
            if(grade.doubleValue() < minGrade){
                minGrade = grade;
            }
        }
        return minGrade;
    }

    public  double maxGrade(List <Double> gradelist){
        double maxGrade = 0;
        for(Double grade : gradelist){
            if(grade.doubleValue() > maxGrade){
                maxGrade = grade;
            }
        }
        return maxGrade;
    }
}
