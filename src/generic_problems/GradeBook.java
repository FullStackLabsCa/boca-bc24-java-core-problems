package generic_problems;

import java.util.ArrayList;
import java.util.List;

public class GradeBook<T extends Number> {
    public List<T> gradeList;

    public GradeBook() {
        this.gradeList = new ArrayList<>();
    }

    public List<T> addGrade(T t) {
        gradeList.add(t);
        return gradeList;
    }

    public String calculateAverage() {
        double avg, sum = 0;
        int count = 0;
        String output;
        if (gradeList==null||gradeList.isEmpty()) {
            output = "No grades available to calculate the average.";
        } else {
            for (T t : gradeList) {
                sum = sum + t.doubleValue();
                count++;
            }
            avg = sum / count;
            output ="Average grade: "+avg;
        }
        return output;
    }

    public String findHighestGrade() {
        T max;
        String output;
        if (gradeList==null||gradeList.isEmpty()) {
            output ="No grades available to find the highest grade.";
        } else {
            max = gradeList.get(0);
            for (T t : gradeList) {
                if (max.doubleValue() < t.doubleValue()) {
                    max = t;
                }
            }
            output ="Highest grade: "+max;
        }
        return output;
    }

    public String findLowestGrade() {
        T min;
        String output;
        if (gradeList==null||gradeList.isEmpty()) {
            output ="No grades available to find the lowest grade.";
        } else {
            min = gradeList.get(0);
            for (T t : gradeList) {
                if (min.doubleValue() > t.doubleValue()) {
                    min = t;
                }
            }
            output ="Lowest grade: "+min;
        }
        return output;
    }

    public int getNumberOfGrades() {
        int gradeListSize=0;
        if (gradeList==null||gradeList.isEmpty()) {
            gradeListSize=0;
        } else {
            gradeListSize = gradeList.size();
        }
        return gradeListSize;
    }

    public static void main(String[] args) {
        GradeBook<Number> gradeBook = new GradeBook<>();
        gradeBook.addGrade(45);
        gradeBook.addGrade(59.6);
        gradeBook.addGrade(89.9);
        System.out.println("List of Grades is : "+gradeBook.gradeList);
        System.out.println("Average of a Grades is : "+gradeBook.calculateAverage());
        System.out.println("Highest Grades is : "+gradeBook.findHighestGrade());
        System.out.println("Lowest Grade is : "+gradeBook.findLowestGrade());
    }


}
