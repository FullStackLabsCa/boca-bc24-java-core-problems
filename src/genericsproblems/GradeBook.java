package gradebook;

import java.util.ArrayList;
import java.util.List;

public class GradeBook <T extends Number> {

    private List<T> grades;

    public GradeBook() {
       grades = new ArrayList<>();
    }


    public void addGrade(T grade) {
        grades.add(grade);
    }


    public double AvgCalculation() {
        if (grades.isEmpty()){
            return 0.0;
        }
        double avg = 0.0;
        for (T t : grades) {
            avg += t.doubleValue()/ grades.size();
        }
        return avg;
    }


    public T findHighestGrade() {

        if (grades.isEmpty()) {
            return null;
        }
        T highestgrade = grades.get(0);
        for (T grade : grades) {
            if (grade.doubleValue() > highestgrade.doubleValue()) {
                highestgrade = grade;
            }
        }
        return highestgrade;
    }


    public T findLowestGrade() {

        if (grades.isEmpty()) {
            return null;
        }
        T lowestgrade = grades.get(0);
        for (T grade : grades) {
            if (grade.doubleValue() < lowestgrade.doubleValue()) {
                lowestgrade = grade;
            }
        }
        return lowestgrade;
    }

    public static void main(String[] args) {

        GradeBook<Integer> integerGradeBook = new GradeBook<>();

        integerGradeBook.addGrade(60);
        integerGradeBook.addGrade(70);
        integerGradeBook.addGrade(80);
        integerGradeBook.addGrade(40);

        System.out.println("Calculate the average:  " + integerGradeBook.AvgCalculation());
        System.out.println("Find the highest grade: "+ integerGradeBook.findHighestGrade());
        System.out.println("Find the lowest grade: "+ integerGradeBook.findLowestGrade());


        GradeBook<Double> doubleGradeBook = new GradeBook<>();

        doubleGradeBook.addGrade(75.25);
        doubleGradeBook.addGrade(85.50);
        doubleGradeBook.addGrade(55.50);
        doubleGradeBook.addGrade(36.50);

        System.out.println("Calculate the average:  " + doubleGradeBook.AvgCalculation());
        System.out.println("Find the highest grade: "+ doubleGradeBook.findHighestGrade());
        System.out.println("Find the lowest grade: "+ doubleGradeBook.findLowestGrade());


    }
}


