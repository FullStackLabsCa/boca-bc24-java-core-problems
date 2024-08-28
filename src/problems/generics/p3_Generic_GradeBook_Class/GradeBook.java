package problems.generics.p3_Generic_GradeBook_Class;

import java.util.ArrayList;
import java.util.List;

public class GradeBook<T extends Number> {

    // refer: GradeBook.md -
    public List<T> gradeList; // refer: GradeBook.md file - line 52

    public GradeBook() {
        gradeList = new ArrayList<>();
    }

    // Addition
    public void addGrade(T grade) {
        gradeList.add(grade);
    }

    // Find Average
    public String calculateAverage() {
        if (gradeList.isEmpty()) {
            return "No grades available to calculate the average.";
        }

        double sum = 0.0, avg;
        for (T grade : gradeList) {
            sum += grade.doubleValue(); // convert each grade o double for summation
        }

        avg = (sum / gradeList.size()); // here we are taking average of the list. i.e. total sum / total number of the subject list(grade list) -

        return "Average grade: " + avg;
    }

    // Find Highest Grade
    public String findHighestGrade() {

        if (gradeList.isEmpty()) {
            return "No grades available to find the highest grade.";
        }

        T highestGrade = gradeList.get(0);
        for (T grade : gradeList) {
            if (grade.doubleValue() > highestGrade.doubleValue()) {
                highestGrade = grade;
            }
        }

        return "Highest grade: "+ highestGrade;
    }

    // Find lowest Grade
    public String findLowestGrade() {

        if (gradeList.isEmpty()) {
            return "No grades available to find the lowest grade.";
        }

        T lowestGrade = gradeList.get(0);
        for (T grade : gradeList) {
            if (grade.doubleValue() < lowestGrade.doubleValue()) {
                lowestGrade = grade;
            }
        }

        return "Lowest grade: "+lowestGrade;
    }


    public int getNumberOfGrades() {

        return gradeList.size();

    }
}
