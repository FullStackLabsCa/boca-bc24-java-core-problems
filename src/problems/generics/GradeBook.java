package problems.generics;

import java.util.*;

public class GradeBook<T extends Number> {

    private List<T> grades;

    public GradeBook(){
        this.grades = new ArrayList<>();
    }

    public void addGrades(T grade){
        grades.add(grade);
    }

    public double calculateAverage() {
        double average = Double.MIN_VALUE;
        if (!grades.isEmpty()) {
            average = 0;
            for (T grade : grades) {
                average = average + grade.doubleValue();
            }
        }

        return average/this.grades.size();
    }

    public double findHighestGrade(){
        double highestGrade =Double.MIN_VALUE;
        if (!grades.isEmpty()) {
            for (T grade : this.grades) {
                if (grade.doubleValue() > highestGrade) {
                    highestGrade = grade.doubleValue();
                }
            }
        }

        return highestGrade;
    }

    public double findLowestGrade(){
        double lowestGrade = Double.MAX_VALUE;
        if (!grades.isEmpty()) {
            for (T grade : this.grades) {
                if (grade.doubleValue() < lowestGrade) {
                    lowestGrade = grade.doubleValue();
                }
            }
        }

        return lowestGrade;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        GradeBook<Integer> grades = new GradeBook<>();

        System.out.println("Please enter grades of 10 students: ");
        for(int i=0; i<10; i++){
            grades.addGrades(s.nextInt());
        }

        System.out.println("Average Grade: " + grades.calculateAverage());
        System.out.println("Highest Grade: " + (grades.findHighestGrade() != Double.MIN_VALUE ? grades.findHighestGrade() : "No grades added."));
        System.out.println("Lowest Grade: " + (grades.findLowestGrade() != Double.MAX_VALUE ? grades.findLowestGrade() : "No grades added."));
    }
}
