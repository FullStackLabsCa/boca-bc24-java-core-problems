package problems.generics;

public class GradeBookRunner {

    public static void main(String[] args) {

        //Integer List
        GradeBook<Integer> gradeBookListInteger = new GradeBook<>();
        gradeBookListInteger.addGrade(70);
        gradeBookListInteger.addGrade(62);
        gradeBookListInteger.addGrade(65);
        gradeBookListInteger.addGrade(76);

        //Double List
        GradeBook<Double> gradeBookListDouble = new GradeBook<>();
        gradeBookListDouble.addGrade(70.33);
        gradeBookListDouble.addGrade(62.52);
        gradeBookListDouble.addGrade(Double.valueOf(62));
        gradeBookListDouble.addGrade(65.68);
        gradeBookListDouble.addGrade(76.20);


        System.out.println(gradeBookListDouble);
        System.out.println("Remove Grade " + gradeBookListDouble.removeGrade(63.00));
        System.out.println(gradeBookListDouble);
        System.out.println("Average of Grades " + gradeBookListDouble.calculateAverage());
        System.out.println("Lowest Grade " + gradeBookListDouble.findLowestGrade());
        System.out.println("Highest Grade " + gradeBookListDouble.findHighestGrade());


        System.out.println(gradeBookListInteger);
        System.out.println("Remove Grade " + gradeBookListInteger.removeGrade(62));
        System.out.println(gradeBookListInteger);
        System.out.println("Average of Grades " + gradeBookListInteger.calculateAverage());
        System.out.println("Lowest Grade " + gradeBookListInteger.findLowestGrade());
        System.out.println("Highest Grade " + gradeBookListInteger.findHighestGrade());
    }
}
