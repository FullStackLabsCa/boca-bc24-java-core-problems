package problems.generics;

import java.util.ArrayList;
import java.util.List;

public class GradeBook<T extends Number & Comparable<T>> {

    private List<T> gradeBookList;

    public GradeBook() {
        this.gradeBookList = new ArrayList<>();
    }

    public void addGrade(T grade) {
        gradeBookList.add(grade);
    }

    public int getNumberOfGrades() {
        return gradeBookList.size();
    }

    public boolean removeGrade(T grade) {
        if (gradeBookList.contains(grade)) {
            gradeBookList.remove(grade);
            return true;
        }
        return false;
    }

    public String calculateAverage() {
        double sum = 0;
        String msg = "";
        if (gradeBookList.isEmpty()) {
            msg ="No grades available to calculate the average.";
        } else {
            if (!gradeBookList.isEmpty()) {
                for (T grades : gradeBookList) {
                    sum += grades.doubleValue();
                }
                msg = "Average grade: " + sum / gradeBookList.size();
            }
        }
        return msg;
    }

    public String findHighestGrade() {

        String msg = "";
        Double highestGrade = null;
        if (gradeBookList == null || gradeBookList.isEmpty()) {
            msg = "No grades available to find the highest grade.";
        } else {
            highestGrade = gradeBookList.get(0).doubleValue();
            for (T grade : gradeBookList) {
                if (highestGrade < grade.doubleValue()) {
                    highestGrade = grade.doubleValue();
                }
            }
            if(gradeBookList.get(0) instanceof Integer){
                msg = "Highest grade: " + highestGrade.intValue();
            }else{
                msg = "Highest grade: " + highestGrade;
            }
        }
        return msg;
    }

    public String findLowestGrade() {

        String msg = "";
        if (gradeBookList == null || gradeBookList.isEmpty()) {
            msg = "No grades available to find the lowest grade.";
        } else {
            double lowestGrade = gradeBookList.get(0).doubleValue();
            for (T grade : gradeBookList) {
                if (lowestGrade > grade.doubleValue()) {
                    lowestGrade = grade.doubleValue();
                }
            }
            if(gradeBookList.get(0) instanceof Integer){
                msg = "Lowest grade: " + (int)lowestGrade;
            }else{
                msg = "Lowest grade: " + (double)lowestGrade;
            }
        }
        return msg;
    }

    @Override
    public String toString() {
        return "GradeBook{" +
                "gradeBookList=" + gradeBookList +
                '}';
    }


}

