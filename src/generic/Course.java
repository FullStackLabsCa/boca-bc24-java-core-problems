package generic;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.SortedMap;

public class Course <S, G extends Number> {

   Map<S, G> studentToGradeMap = new LinkedHashMap<>();

   public void enrollStudents (S s, G g) {
       studentToGradeMap.put(s,g);
   }

   public void getGrade (S s){
       G gradeOfStudent = studentToGradeMap.get(s);
       if(gradeOfStudent == null){
           System.out.println("Grades for student : " + s + " has not Assigned !!");
       } else {
           System.out.println("Grades for student : " + s + " => " + gradeOfStudent);
       }
   }

   public void printStudentsAndGrades (){
       System.out.println(studentToGradeMap);
   }

    public static void main(String[] args) {
       Course<String, Integer> java2024 = new Course<>();
       java2024.enrollStudents("Nimanshu", 100);
       java2024.enrollStudents("Kiran", 80);
       java2024.enrollStudents("Anant", 101);
       java2024.enrollStudents("Shifa",110);
       java2024.enrollStudents("Akshita",null);
       java2024.enrollStudents("Akshat", 50);

       Course<Integer,Double> java2025Bootcamp = new Course<>();
       java2025Bootcamp.enrollStudents(1,22.20);
       java2025Bootcamp.enrollStudents(2,20.58);
       java2025Bootcamp.enrollStudents(3,25.23);
       java2025Bootcamp.enrollStudents(4,20.19);
       java2025Bootcamp.enrollStudents(5,null);
       System.out.println("===== Grades of course java2025Bootcamp =====");

       java2025Bootcamp.getGrade(5);
       java2025Bootcamp.printStudentsAndGrades();
       java2025Bootcamp.getGrade(3);
       System.out.println("\n\n");


       System.out.println("===== Grades of course java2024 =====");
       java2024.getGrade("Akshita");
       java2024.getGrade("Kiran");
       java2024.printStudentsAndGrades();

    }
}


