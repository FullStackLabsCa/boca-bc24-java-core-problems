package gradebook;

import java.util.List;

public class GradeBook<T extends Number> {

    private List<T> grades;

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
}
