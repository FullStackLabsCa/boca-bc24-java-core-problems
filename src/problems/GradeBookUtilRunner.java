package problems;
import java.util.ArrayList;
import java.util.List;

public class GradeBookUtilRunner {
    public static void main(String[] args) {

        //To test Integer GradeBook
        List<Integer> integerList = new ArrayList<>();
        integerList.add(3);
        integerList.add(4);
        integerList.add(9);
        integerList.add(4);
        integerList.add(7);
        integerList.add(1);
        System.out.println("Input Integer garde = " + integerList);
        System.out.println("Add Integer grade: " + 5 + " to integer garde: " + GenericGradeBookUtil.addGrade(integerList, 5));
        System.out.println("Remove Integer : " + 4 + " to integer garde: " + GenericGradeBookUtil.removeGrade(integerList, 4));
        System.out.println("Highest of  integer garde: " + GenericGradeBookUtil.findHighestGrade(integerList));
        System.out.println("Lowest of  integer garde: " + GenericGradeBookUtil.findLowestGrade(integerList));
        System.out.println("Average of  integer garde: " + GenericGradeBookUtil.calcAvgGrade(integerList));

//      To test double GradeBook
        List<Double> doubleList = new ArrayList<>();
        doubleList.add(3.6);
        doubleList.add(4.3);
        doubleList.add(9.6);
        doubleList.add(4.5);
        doubleList.add(7.0);
        System.out.println("Input Double garde = " + doubleList);
        System.out.println("Add Double grade: " + 5.8 + " to integer garde: " + GenericGradeBookUtil.addGrade(doubleList, 5.8));
        System.out.println("Remove Double : " + 4.2 + " to integer garde: " + GenericGradeBookUtil.removeGrade(doubleList, 4.2));
        System.out.println("Highest of  Double garde: " + GenericGradeBookUtil.findHighestGrade(doubleList));
        System.out.println("Lowest of  Double garde: " + GenericGradeBookUtil.findLowestGrade(doubleList));
        System.out.println("Average of  Double garde: " + GenericGradeBookUtil.calcAvgGrade(doubleList));


        //      To test float GradeBook
        List<Double> floatList = new ArrayList<>();
        floatList.add(6.3);
        floatList.add(1.1);
        floatList.add(5.0);
        floatList.add(0.1);
        floatList.add(100.0);
        System.out.println("Input Float garde = " + floatList);
        System.out.println("Add Float grade: " + 5.8 + " to integer garde: " + GenericGradeBookUtil.addGrade(floatList, 5.8));
        System.out.println("Remove Float : " + 4.2 + " to integer garde: " + GenericGradeBookUtil.removeGrade(floatList, 4.2));
        System.out.println("Highest of  Float garde: " + GenericGradeBookUtil.findHighestGrade(floatList));
        System.out.println("Lowest of  Float garde: " + GenericGradeBookUtil.findLowestGrade(floatList));
        System.out.println("Average of  Float garde: " + GenericGradeBookUtil.calcAvgGrade(floatList));
    }
}
