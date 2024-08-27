package problems.genrics;

public class CoursebookRunner {
    public static void main(String[] args) {

        problems.generics.CourseBook<String,Double> courseGrade = new problems.generics.CourseBook<>();
        System.out.println(courseGrade.listAllGrades());

        courseGrade.enrollStudent("john");
        courseGrade.enrollStudent("jacob");
        System.out.println(courseGrade.listAllGrades());


        courseGrade.assignGrades("john",62.00);
        courseGrade.assignGrades("jacob",72.00);


        System.out.println(courseGrade.listAllGrades());
        System.out.println("Grade :"+ courseGrade.retrieveGrade("john"));
    }

}
