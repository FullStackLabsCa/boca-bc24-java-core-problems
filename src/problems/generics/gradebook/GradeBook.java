package problems.generics.gradebook;

import java.util.ArrayList;
import java.util.List;

public class GradeBook<T extends Number> {
    List<T> gradeList = new ArrayList<>();

    public Double sum(List<T> gradeList){
        double totalSum= 0;

        for(T grade: gradeList) {
            totalSum = totalSum + grade.doubleValue();
        }
        return totalSum;
    }

    public Double avg(List<T> gradeList){
        double totalSum= 0;
        double avg= 0;

        for(T grade: gradeList) {
            totalSum = totalSum + grade.doubleValue();
        }
        avg= totalSum/ gradeList.size();
        return avg;
    }

    public Double min(List<T> gradeList){
        double min=gradeList.get(0).doubleValue();

        for(T grade: gradeList) {
            if((grade.doubleValue() < min)) {
                min =grade.doubleValue();
            }
        }
        return min;
    }

    public Double max(List<T> gradeList){
        double max=gradeList.get(0).doubleValue();

        for(T grade: gradeList) {
            if((grade.doubleValue() > max)) {
                max =grade.doubleValue();
            }
        }
        return max;
    }
}