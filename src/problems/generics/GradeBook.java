package problems.generics;
import java.util.ArrayList;
import java.util.List;

public class GradeBook<T extends Number & Comparable<T>> {
    List<T> grades;

    public GradeBook() {
        grades = new ArrayList<>();
    }

    public GradeBook(List<T> grades) {
        this.grades = grades;
    }


    public List<T> addGrade(T grade) {
        this.grades.add(grade);
        return this.grades;
    }

    public  List<T> removeGrade(T grade) {
        if(this.grades.contains(grade)){
            while (this.grades.contains(grade)){
                this.grades.remove(grade);
            }
        }else {
            System.out.println("Given grade not exist in grade book");
        }
        return this.grades;
    }


    public String findHighestGrade() {
        if(!grades.isEmpty()){
            T max = this.grades.getFirst();
            for (int i = 1; i < this.grades.size(); i++) {
                if (max.compareTo(this.grades.get(i)) < 0) {
                    max = this.grades.get(i);
                }
            }
            return "Highest grade: " + max;

        }
        return "No grades available to find the highest grade.";
    }

    public String findLowestGrade() {
        if (!grades.isEmpty()) {
            T min = this.grades.getFirst();
            for (int i = 1; i < this.grades.size(); i++) {
                if (min.compareTo(this.grades.get(i)) > 0) {
                    min = this.grades.get(i);
                }
            }
            return "Lowest grade: " + min;
        }
        return "No grades available to find the lowest grade.";
    }

    public Double calcAvgGrade() {
        double result;
        double total = 0.0;
        for (T grade : this.grades) {
            total += grade.doubleValue();
        }
        result = (total / (double) this.grades.size());
        return (result);
    }


    public int getNumberOfGrades() {
        return this.grades.size();
    }

    public String calculateAverage() {
        double total = 0.0;
        if (!grades.isEmpty()){
            for (T grade : grades){
                total += grade.doubleValue();
            }
            System.out.println("Average grade: " + total/grades.size());
            return "Average grade: " + total/grades.size();
        }
        return "No grades available to calculate the average.";
    }
}
