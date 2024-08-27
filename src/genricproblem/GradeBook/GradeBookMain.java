package genricproblem.GradeBook;
public class GradeBookMain {
    public static void main(String[] args) {


        GradeBook<Integer> grade = new GradeBook<>();
        grade.addGrades(54);
        grade.addGrades(56);
        grade.addGrades(23);
        grade.addGrades(22);
        System.out.println("Average of gradeș"+ grade.averageGrade());
        System.out.println("Highest grade "+ grade.highestGrade());
        System.out.println("Lowest grade "+ grade.lowestGrade());

        GradeBook<Double> grade1 = new GradeBook<>();
        grade1.addGrades(2.4);
        grade1.addGrades(5.6);
        grade1.addGrades(2.3);
        grade1 .addGrades(2.2);
        System.out.println("Average of gradeș"+ grade1.averageGrade());
        System.out.println("Highest grade "+ grade1.highestGrade());
        System.out.println("Lowest grade "+ grade1.lowestGrade());
    }
}
