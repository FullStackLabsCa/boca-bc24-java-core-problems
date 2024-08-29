package problems;

import java.util.ArrayList;
import java.util.List;

public class GradeBook<T extends Number & Comparable> {


    private List<T> grades;         // Create List

    public GradeBook() {
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

    public String calculateAverage() {

        if (grades.isEmpty()) {
            //System.out.println("No grades available to calculate the average.");
            return "No grades available to calculate the average.";
        }

        double sum = 0.0;

        for (T grade : grades) {
            sum = sum + grade.doubleValue();
        }
        double s= sum / grades.size();

        return "Average grade: "+ s  ;

    }

    public String findHighestGrade() {
        if (grades.isEmpty()) {
            return "No grades available to find the highest grade.";
        }

        T highestGrade = grades.get(0);
        for (T grade : grades) {

            if (grade.doubleValue() > highestGrade.doubleValue()) {
                highestGrade = grade;
            }


        }
        return "Highest grade: " + highestGrade;


    }

    public String findLowestGrade() {
        if (grades.isEmpty()) {
            return "No grades available to find the lowest grade.";
        }
        T lowestGrade = grades.get(0);
        for (T grade : grades) {
            if (grade.doubleValue() < lowestGrade.doubleValue()) {
                lowestGrade = grade;
            }
        }
        return "Lowest grade: "+lowestGrade;
    }

    public int getNumberOfGrades() {
        return grades.size();
    }
}
