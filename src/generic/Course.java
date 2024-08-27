package generic;

import java.util.HashMap;
import java.util.Map;

public class Course <S, G extends Number> {

   Map<S, G> studentToGradeMap = new HashMap<>();

   public void enrollStudents (S s, G g) {
       studentToGradeMap.put(s,g);
   }

   public G getGrade (S s){
       G gradeOfStudent = studentToGradeMap.get(s);
       return gradeOfStudent;
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

        System.out.println(java2024.getGrade("Shifa"));
        java2024.printStudentsAndGrades();

    }
}


