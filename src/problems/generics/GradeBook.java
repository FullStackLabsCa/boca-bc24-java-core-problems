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

    public boolean removeGrade(T grade) {
        if (gradeBookList.contains(grade)) {
            gradeBookList.remove(grade);
            return true;
        }
        return false;
    }

    public double calculateAverage() {
        double sum = 0;
        if (gradeBookList.isEmpty()) {
            System.out.println("List Empty! Cannot calculate Average");
        } else {
            if (!gradeBookList.isEmpty()) {
                for (T grades : gradeBookList) {
                    sum += grades.doubleValue();
                }
            }
        }
        return sum / gradeBookList.size();
    }

    public T findHighestGrade() {
        T highestGrade = null;
        if (gradeBookList.isEmpty()) {
            System.out.println("List Empty! Cannot find highest grade");
        } else {
            highestGrade = gradeBookList.get(0);
            for (T grade : gradeBookList) {
                if (grade.compareTo(highestGrade) > 0) {
                    highestGrade = grade;
                }
            }
        }
        return highestGrade;
    }


    public T findLowestGrade() {

        T lowestGrade = null;
        if (gradeBookList.isEmpty()) {
            System.out.println("List Empty! Cannot find lowest grade");
        } else {
            lowestGrade = gradeBookList.get(0);
            for (T grade : gradeBookList) {
                if (grade.compareTo(lowestGrade) < 0) {
                    lowestGrade = grade;
                }
            }
        }
        return lowestGrade;
    }

    @Override
    public String toString() {
        return "GradeBook{" +
                "gradeBookList=" + gradeBookList +
                '}';
    }
}

