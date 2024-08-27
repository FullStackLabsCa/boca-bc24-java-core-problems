package generics.course;

import java.util.LinkedHashMap;
import java.util.Map;

public class Course<S, G> {
    Map<S, G> studentToGradeMap = new LinkedHashMap<>();

    private void enrollstudent(S studentIdentifier) {
        studentToGradeMap.putIfAbsent(studentIdentifier, null);
    }

    private void assignGrade(S studentIdentifier, G grade) {
        studentToGradeMap.putIfAbsent(studentIdentifier, grade);
    }

    private G retrieveGrade(S studentIdentifier) {
        if (studentToGradeMap.get(studentIdentifier) != null) {
            return studentToGradeMap.get(studentIdentifier);
        } else {
            System.out.println("Student with this identifier doesn't exist in a map.!");
            return null;
        }
    }

    private void getStudentGradeList() {
//        System.out.println("\n*****Student grade list*****\n" + studentToGradeMap);

        for (Map.Entry<S, G> studentGradeEntry: studentToGradeMap.entrySet()) {
            System.out.println(studentGradeEntry.getKey() + " <-> " + studentGradeEntry.getValue());
        }
    }

    public static void main(String[] args) {
        System.out.println("Student with string student identifier and double grade");
        Course<String, Double> javFall2024 = new Course();

        //Enroll students for a course
        javFall2024.enrollstudent("Nimanshu");
        javFall2024.enrollstudent("Kiran");
        javFall2024.enrollstudent("Anant");
        javFall2024.enrollstudent("Akshita");

        //Assign grade to specific student
        javFall2024.assignGrade("Nimanshu", 98.50);
        javFall2024.assignGrade("Kiran", 99.5);
        javFall2024.assignGrade("Anant", Double.valueOf(100));
        javFall2024.assignGrade("Akshita", 98.5);

        //Retrieve grade of specific student
        System.out.println("studentGrade.retrieveGrade(\"Nimanshu\") = " + javFall2024.retrieveGrade("Nimanshu"));
        System.out.println("studentGrade.retrieveGrade(\"Akshita\") = " + javFall2024.retrieveGrade("Akshita"));

        javFall2024.getStudentGradeList();


        System.out.println("\nStudent with integer student identifier and integer grade");
        Course<Integer, Integer> javaWinter2025 = new Course();

        //Enroll students for a course
        javaWinter2025.enrollstudent(1);
        javaWinter2025.enrollstudent(2);
        javaWinter2025.enrollstudent(3);

        //Assign grade to specific student
        javaWinter2025.assignGrade(1, 98);
        javaWinter2025.assignGrade(2, 99);
        javaWinter2025.assignGrade(3, 100);
        javaWinter2025.assignGrade(4, 98);

        //Retrieve grade of specific student
        System.out.println("studentGrade.retrieveGrade(\"1\") = " + javaWinter2025.retrieveGrade(1));
        System.out.println("studentGrade.retrieveGrade(\"4\") = " + javaWinter2025.retrieveGrade(4));

        javaWinter2025.getStudentGradeList();
    }
}
