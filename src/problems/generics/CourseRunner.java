package problems.generics;

public class CourseRunner {
    public static void main(String[] args) {

        Course<String,Double> courseGradeMapOne = new Course<>();
        Course<Integer,Double> courseGradeMapTwo = new Course<>();
        System.out.println(courseGradeMapOne.listAllGrades());

        courseGradeMapTwo.enrollStudent(12345);
        courseGradeMapOne.enrollStudent("student1");
        courseGradeMapOne.enrollStudent("student2");
        courseGradeMapOne.enrollStudent("student3");
        courseGradeMapOne.enrollStudent("student4");
        courseGradeMapOne.enrollStudent("student5");
        System.out.println(courseGradeMapOne.listAllGrades());


        courseGradeMapOne.assignGrade("student1",62.00);
        courseGradeMapOne.assignGrade("student2",72.00);
        courseGradeMapOne.assignGrade("student4",82.00);


        System.out.println(courseGradeMapOne.listAllGrades());
        System.out.println("Retrieve grade :"+ courseGradeMapOne.retrieveGrade("student1"));
//        System.out.println("Average "+courseGradeMapOne.averageOfGrades());


    }
}
