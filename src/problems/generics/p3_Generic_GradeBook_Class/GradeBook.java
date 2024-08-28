package problems.generics.p3_Generic_GradeBook_Class;

import java.util.ArrayList;
import java.util.List;

public class GradeBook <T extends Number>{

    // refer: GradeBook.md -
    private List<T> gradeList; // refer: GradeBook.md file - line 52

    public GradeBook() {
        gradeList = new ArrayList<>();
    }

    // Addition
    public void addGrade(T grade){
        gradeList.add(grade);
    }

    // Find Average
    public double calculateAverage(){
        if(gradeList.isEmpty()){
            return 0.0;
        }

        double sum = 0.0;
        for (T grade : gradeList){
            sum += grade.doubleValue(); // convert each grade o double for summation
        }

        return sum/gradeList.size(); // here we are taking average of the list. i.e. total sum / total number of the subject list(grade list) -
    }

    // Find Highest Grade
    public T highestGrade(){

        if(gradeList.isEmpty()){
            return null;
        }

        T highestGrade = gradeList.get(0);
        for (T grade : gradeList){
            if(grade.doubleValue() > highestGrade.doubleValue()){
                highestGrade = grade;
            }
        }

        return highestGrade;
    }

    // Find lowest Grade
    public T lowestGrade(){

        if(gradeList.isEmpty()){
            return null;
        }

        T lowestGrade = gradeList.get(0);
        for (T grade : gradeList){
            if(grade.doubleValue() < lowestGrade.doubleValue()){
                lowestGrade = grade;
            }
        }

        return lowestGrade;
    }


}
