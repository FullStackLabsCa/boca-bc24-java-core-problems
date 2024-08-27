package problems.generics;

public class CourseRunner {
    public static void main(String[] args) {

        Course<String,Double> courseGradeMapOne = new Course<>();
        System.out.println(courseGradeMapOne.listAllGrades());

        courseGradeMapOne.enrollStudent("student1");
        courseGradeMapOne.enrollStudent("student2");
        courseGradeMapOne.enrollStudent("student3");
        courseGradeMapOne.enrollStudent("student4");
        courseGradeMapOne.enrollStudent("student5");
        System.out.println(courseGradeMapOne.listAllGrades());


        courseGradeMapOne.assignGrades("student1",62.00);
        courseGradeMapOne.assignGrades("student2",72.00);
        courseGradeMapOne.assignGrades("student4",82.00);


        System.out.println(courseGradeMapOne.listAllGrades());
        System.out.println("Retrieve grade :"+ courseGradeMapOne.retrieveGrade("student1"));
//        System.out.println("Average "+courseGradeMapOne.averageOfGrades());


    }
}
