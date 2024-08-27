package generic;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;

public class Course <S, G extends Number> {

   Map<S, G> studentToGradeMap = new HashMap<>();

   public void enrollStudents (S s, G g) {
       studentToGradeMap.put(s,g);
   }

   public void getGrade (S s){
       G gradeOfStudent = studentToGradeMap.get(s);
       if(gradeOfStudent == null){
           System.out.println("Grades for this student has not assigned");
       } else {
           System.out.println(gradeOfStudent);
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


       java2024.getGrade("Akshita");
       java2024.getGrade("Kiran");
       java2024.printStudentsAndGrades();

    }
}


