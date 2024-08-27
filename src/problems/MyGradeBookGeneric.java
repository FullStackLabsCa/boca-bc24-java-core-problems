package problems;

import java.util.ArrayList;
import java.util.List;

public class MyGradeBookGeneric<T extends Number & Comparable> {


    private List<T> grades;         // Create List

    public MyGradeBookGeneric() {
        grades = new ArrayList<>();// null Constructor
    }

    public void addGrade(T grade) {            // add method

        grades.add(grade);

    }

    public List<T> getGrades() {
        return grades;
    }

    public void setGrades(List<T> grades) {
        this.grades = grades;
    }

    public double calculateAverage() {

        if (grades.isEmpty()) {
            return 0.0;
        }

        double sum = 0.0;

        for (T grade : grades) {
            sum = sum + grade.doubleValue();
        }

        return sum / grades.size();

    }

    public T highestGrades() {
        if(grades.isEmpty()){
            return null;
        }

        T highestGrade = grades.get(0);
        for (T grade : grades) {

            if( grade.doubleValue()>highestGrade.doubleValue()){
                highestGrade = grade;
            }


        }
        return highestGrade;


    }
    public T lowestGrades(){
        if(grades.isEmpty()){
            return null;
        }
        T lowestGrade= grades.get(0);
        for(T grade: grades){
            if( grade.doubleValue()<lowestGrade.doubleValue()){
                lowestGrade = grade;
            }
        }
        return lowestGrade;
    }

}
