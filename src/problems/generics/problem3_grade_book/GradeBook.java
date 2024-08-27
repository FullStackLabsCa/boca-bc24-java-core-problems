package problems.generics.problem3_grade_book;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GradeBook<G extends Number & Comparable<G>> {
    List<G> listOfGrades;

    public GradeBook() {
        this.listOfGrades = new ArrayList<>();
    }


    public void addingGradesToGradeBook(G grade) {
        if (grade == null) {
            throw new RuntimeException("Can not add the Null value to the GradeBook");
        } else {
            listOfGrades.add(grade);
        }
    }

    //Average of Grades method
    public double averageOfGrades() {
        Double average = null;
        double sum = 0;
        if (listOfGrades.isEmpty()) {
            throw new RuntimeException("List of Grades are empty");
        } else {
            for (int j = 0; j < listOfGrades.size(); j++) {
                sum += listOfGrades.get(j).doubleValue();
            }
            average = sum / (listOfGrades.size());
        }
        return average;
    }

    //find the highest grade in the grade book

    public G findHighestGrade() {
        if (listOfGrades.isEmpty()) {
            throw new RuntimeException("List of the Grades are empty");
        }
        return Collections.max(listOfGrades);
    }

    //Find the lowest grade in the grade book
    public G findLowestGrade() {
        if (listOfGrades.isEmpty()) {
            throw new RuntimeException("List of the Grades are empty");
        } else return Collections.min(listOfGrades);
    }

    public void clearGradeBook() {
        listOfGrades.clear();
    }

    public List<G> getListOfGrades() {
        return new ArrayList<>(listOfGrades);
    }
}
