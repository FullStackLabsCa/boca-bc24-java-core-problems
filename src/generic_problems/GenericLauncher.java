package generic_problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenericLauncher {
    public static void main(String[] args) {
        System.out.println("List To Array Method is invoked");
        List<String> fruitList = new ArrayList<>();
        fruitList.add("Apple");
        fruitList.add("Banana");
        fruitList.add("Grapes");
        System.out.println(Arrays.toString(GenericProblems.listToArray(fruitList)));

        System.out.println("\n\nOccurrence Count method is invoked");
        Integer[] integerArray = {2,3,4,5,2,2};
        System.out.println(GenericProblems.countOccurrences(integerArray, 2));
        String[] stringArray = {"Hello", "heLLo", "Gur", "gUR"};
        System.out.println(GenericProblems.countOccurrences(stringArray, "gur"));


        System.out.println("Grade class is invoked");
        GradeBook<Number> gradeBook = new GradeBook<>();
        gradeBook.addingGrades(45);
        gradeBook.addingGrades(59.6);
        gradeBook.addingGrades(89.9);
        System.out.println("List of Grades is : "+gradeBook.grade);
        System.out.println("Average of a Grades is : "+gradeBook.calculateAverage(gradeBook.grade));
        System.out.println("Highest Grades is : "+gradeBook.highestGrades(gradeBook.grade));
        System.out.println("Lowest Grade is : "+gradeBook.lowestGrades(gradeBook.grade));


        System.out.println("\n\nCourse class is invoked");
        Course<String,Double> java = new Course<>();
        java.enrollStudent("Gurpreet");
        java.assignGrade("Gurpreet",90.0);
        java.retrieveGrade("Gurpreet");
        java.listAllStudents();


        System.out.println("\n\nOddInteher class is invoked");
        List<Integer> integerArrayList = new ArrayList<>();
        integerArrayList.add(12);
        integerArrayList.add(3);
        integerArrayList.add(8);
        integerArrayList.add(5);
        integerArrayList.add(7);
        integerArrayList.add(2);
        GenericProblems.occureneceOfOddNumbers(integerArrayList);
    }
}