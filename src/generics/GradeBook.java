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
    public boolean addGrade(T grade){

        //Validate Input
        //.
        //Add to the GradeBook
        gradeBook.add(grade);
        return true;
    }

    public int getNumberOfGrades(){
        return gradeBook.size();
    }

    //Calculate Average Grade
    public String calculateAverage(){
        double average = 0;

        for(T element : gradeBook){
            average += element.doubleValue();
        }

        average = average/gradeBook.size();

        return "Average grade: "+average;
//        return average;
    }

    //Determine Highest Grade
    public String findHighestGrade(){
        double max = gradeBook.get(0).doubleValue();

        for(T grade : gradeBook){
            if(grade.doubleValue() >= max){
                max = grade.doubleValue();
            }
        }

        return "Highest grade: " + max;
//        return max;
    }

    //Determine Lowest Grade
    public String findLowestGrade(){
        double min = gradeBook.get(0).doubleValue();

        for(T grade : gradeBook){
            if(grade.doubleValue() <= min){
                min = grade.doubleValue();
            }
        }

        String result = "Lowest grade: " + min;
        return result;

        //return min;
    }

//    public static void main(String[] args) {
//        GradeBook<Double> classA = new GradeBook<>();
//
//        classA.addGrade(85.0);
//        classA.addGrade(90.0);
//
//        System.out.println("Class Average: " + classA.calculateAverage());
//        System.out.println("Highest Grade: " + classA.findHighestGrade());
//        System.out.println("Lowest Grade: " + classA.findLowestGrade());
//
//        GradeBook<Integer> classB = new GradeBook<>();
//
//        classB.addGrade(3);
//        classB.addGrade(4);
//
//        System.out.println("Class Average: " + classB.calculateAverage());
//        System.out.println("Highest Grade: " + classB.findHighestGrade());
//        System.out.println("Lowest Grade: " + classB.findLowestGrade());
//    }
}
