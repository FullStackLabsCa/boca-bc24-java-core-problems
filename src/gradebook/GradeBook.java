package gradebook;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GradeBook<T extends Number> {

    private final List<T> grades;

    public GradeBook() {
        grades = new ArrayList<>();
    }

    public GradeBook(List<T> grades) {
        this.grades = grades;
    }


    public double totalMarks(List<Double> grades){
        double avg;
        double sum = 0;
        for(Double i: grades){
                sum += i;
        }
        avg = sum/grades.size();
        return avg;
    }

    @Override
    public String toString() {
        return "GradeBook{ grades= " + grades + '}';
    }

    public void addGrade(T v) {
            grades.add(v);
    }

    public int getNumberOfGrades() {
        return grades.size();
    }

    public String calculateAverage() {
        if (grades.isEmpty()) {
            return "No grades available to calculate the average.";
        }else {

            double sum = 0.0;
            for(T grade:grades){
                sum += ((Number)grade).doubleValue();
            }
            double avg = sum / grades.size();
            return "Average grade: " + avg;
        }

    }

    public String findHighestGrade() {
        if (grades.isEmpty()) {
            return "No grades available to find the highest grade.";
        }else {
            T max = grades.get(0);
            for (T grade : grades) {
                if (grade.doubleValue() > max.doubleValue()) {
                    max = grade;
                }
            }

            return "Highest grade: " + max;
        }
    }

    public String findLowestGrade() {
        if (grades.isEmpty()) {
            return "No grades available to find the lowest grade.";
        }
        else {
            T min = grades.get(0);
            for (T grade : grades) {
                if (grade.doubleValue() < min.doubleValue()) {
                    min = grade;
                }
            }

            return "Lowest grade: " + min;
        }
    }

}
