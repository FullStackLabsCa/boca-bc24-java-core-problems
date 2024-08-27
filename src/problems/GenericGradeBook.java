package problems;
import java.util.List;

public class GenericGradeBook<T extends Number & Comparable<T>> {
    List<T> grades;

    public GenericGradeBook(List<T> grades) {
        this.grades = grades;
    }


    public List<T> addGrade(T grade) {
        grades.add(grade);
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


    public T findHighestGrade() {
        T max = this.grades.getFirst();
        for (int i = 1; i < this.grades.size(); i++) {
            if (max.compareTo(this.grades.get(i)) < 0) {
                max = this.grades.get(i);
            }
        }
        return max;
    }

    public T findLowestGrade() {
        T min = this.grades.getFirst();
        for (int i = 1; i < this.grades.size(); i++) {
            if (min.compareTo(this.grades.get(i)) > 0) {
                min = this.grades.get(i);
            }
        }
        return min;
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


}
