package problems.generics.problem3_grade_book;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GradeBook<G extends Number & Comparable<G>> {

    List<G> listOfGrades;

    public GradeBook() {
        this.listOfGrades = new ArrayList<>();
    }


    public void addGrade(G grade) {
        if (grade == null) {
            throw new IllegalStateException("Can not add the Null value to the GradeBook");
        } else {
            listOfGrades.add(grade);
        }
    }

    //Average of Grades method
    public String calculateAverage() {
        Double average = null;
        double sum = 0;
        if (listOfGrades.isEmpty()) {
//            throw new IllegalStateException("No grades available to calculate the average.");
            return "No grades available to calculate the average.";
        } else {
            for (int j = 0; j < listOfGrades.size(); j++) {
                sum += listOfGrades.get(j).doubleValue();
            }
            average = sum / (listOfGrades.size());
        }
        return "Average grade: " + average;
    }

    //find the highest grade in the grade book

    public String findHighestGrade() {
        if (listOfGrades.isEmpty()) {
//            throw new IllegalStateException("No grades available to calculate the average.");
            return "No grades available to find the highest grade.";

        }
        G max = Collections.max(listOfGrades);

        return "Highest grade: " + max;
    }

    //Find the lowest grade in the grade book
    public String findLowestGrade() {
        if (listOfGrades.isEmpty()) {
//            throw new IllegalStateException("No grades available to calculate the average.");
            return "No grades available to find the lowest grade.";

        } else {
            G min = Collections.min(listOfGrades);
            return "Lowest grade: " + min;
        }
    }

    public void clearGradeBook() {
        listOfGrades.clear();
    }

    public int getNumberOfGrades() {
        return listOfGrades.size();
    }
}
