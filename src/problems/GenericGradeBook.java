package problems;
import java.util.List;

public class GenericGradeBook<T extends Number & Comparable<T>> {
    List<T> gradeBook;

    public GenericGradeBook(List<T> gradeBook) {
        this.gradeBook = gradeBook;
    }


    public List<T> addGrade(T grade) {
        gradeBook.add(grade);
        return this.gradeBook;
    }

    public  List<T> removeGrade(T grade) {
        if(this.gradeBook.contains(grade)){
            while (this.gradeBook.contains(grade)){
                this.gradeBook.remove(grade);
            }
        }else {
            System.out.println("Given grade not exist in grade book");
        }
        return this.gradeBook;
    }


    public T findHighestGrade() {
        T max = this.gradeBook.getFirst();
        for (int i = 1; i < this.gradeBook.size(); i++) {
            if (max.compareTo(this.gradeBook.get(i)) < 0) {
                max = this.gradeBook.get(i);
            }
        }
        return max;
    }

    public T findLowestGrade() {
        T min = this.gradeBook.getFirst();
        for (int i = 1; i < this.gradeBook.size(); i++) {
            if (min.compareTo(this.gradeBook.get(i)) > 0) {
                min = this.gradeBook.get(i);
            }
        }
        return min;
    }

    public Double calcAvgGrade() {
        double result;
        double total = 0.0;
        for (T grade : this.gradeBook) {
            total += grade.doubleValue();
        }
        result = (total / (double) this.gradeBook.size());
        return (result);
    }


}
