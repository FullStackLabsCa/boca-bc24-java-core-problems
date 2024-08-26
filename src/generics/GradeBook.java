package generics;

import java.util.ArrayList;
import java.util.List;

public class GradeBook <T extends Number> {

    //Create a collection to store the grades
    //Arraylist should fit well
    private List<T> gradeBook;

    public GradeBook() {
        //Initialize an ArrayList for the GradeBook
        this.gradeBook = new ArrayList<>();
    }

    //Method to add grades
    public boolean addGrades(T grade){

        //Validate Input
        //.
        //Add to the GradeBook
        gradeBook.add(grade);
        return true;
    }

    //Calculate Average Grade
    public double calculateAverage(){
        double average = 0;

        for(T element : gradeBook){
            average += element.doubleValue();
        }

        average = average/gradeBook.size();

        return average;
    }

    //Determine Highest Grade
    public double getHighestGrade(){
        double max = gradeBook.get(0).doubleValue();

        for(T grade : gradeBook){
            if(grade.doubleValue() >= max){
                max = grade.doubleValue();
            }
        }
        return max;
    }

    //Determine Lowest Grade
    public double getLowestGrade(){
        double min = gradeBook.get(0).doubleValue();

        for(T grade : gradeBook){
            if(grade.doubleValue() <= min){
                min = grade.doubleValue();
            }
        }
        return min;
    }

    public static void main(String[] args) {
        GradeBook<Double> classA = new GradeBook<>();

        classA.addGrades(85.0);
        classA.addGrades(90.0);

        System.out.println("Class Average: " + classA.calculateAverage());
        System.out.println("Highest Grade: " + classA.getHighestGrade());
        System.out.println("Lowest Grade: " + classA.getLowestGrade());

        GradeBook<Integer> classB = new GradeBook<>();

        classB.addGrades(3);
        classB.addGrades(4);

        System.out.println("Class Average: " + classB.calculateAverage());
        System.out.println("Highest Grade: " + classB.getHighestGrade());
        System.out.println("Lowest Grade: " + classB.getLowestGrade());
    }
}
