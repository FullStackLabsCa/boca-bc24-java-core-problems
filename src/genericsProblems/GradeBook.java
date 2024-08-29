package genericsProblems;

import java.util.*;



public class GradeBook<T extends Number & Comparable<T>> {

    public List<T> grades = new ArrayList();


    public void GradeBook(List<T> grades) {
        this.grades = new ArrayList<>();;
    }


    public void addGrade(T grade) {
        grades.add(grade);
    }

    public int getNumberOfGrades() {
        return this.grades.size();

    }

    public String calculateAverage() {
        Double average = Double.MIN_VALUE;

        if (grades.isEmpty()){
            average=0.0;
        }

        if(!grades.isEmpty()){
           for ( T grade : grades) {
                average +=  grade.doubleValue();
           }
           return "Average grade: " +String.format("%.1f", average / this.grades.size());
        }


       return "No grades available to calculate the average.";

    }


    public String findHighestGrade() {
        if (!grades.isEmpty()) {
            return "Highest grade: " + Collections.max(grades).toString();
        }

        return "No grades available to find the highest grade.";
    }


    public String findLowestGrade() {
        if (!grades.isEmpty()) {
            return "Lowest grade: " + Collections.min(grades).toString();
        }

        return "No grades available to find the lowest grade.";
    }
}


