package generics.course;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class Course<S, G> {
    Map<S, G> studentToGradeMap = new LinkedHashMap<>();

    public void enrollStudent(S studentIdentifier) {
        studentToGradeMap.putIfAbsent(studentIdentifier, null);
    }

    public boolean isStudentEnrolled(S studentIdentifier) {
        return studentToGradeMap.containsKey(studentIdentifier);
    }

    public void assignGrade(S studentIdentifier, G grade) {
        if(isStudentEnrolled(studentIdentifier)) {
            studentToGradeMap.put(studentIdentifier, grade);
        }
    }

    public Optional<G> getGrade(S studentIdentifier) {
        return Optional.ofNullable(studentToGradeMap.get(studentIdentifier));
    }

    public Map<S, G>  getAllGrades() {
        return studentToGradeMap;
    }

    public void listAllGrades() {
        for (Map.Entry<S, G> studentGradeEntry: studentToGradeMap.entrySet()) {
            System.out.println(studentGradeEntry.getKey() + " <-> " + studentGradeEntry.getValue());
        }
    }

    public static void main(String[] args) {
        System.out.println("Student with string student identifier and double grade");
        Course<String, Double> javFall2024 = new Course();

        //Enroll students for a course
        javFall2024.enrollStudent("Nimanshu");
        javFall2024.enrollStudent("Kiran");
        javFall2024.enrollStudent("Anant");
        javFall2024.enrollStudent("Akshita");

        //Assign grade to specific student
        javFall2024.assignGrade("Nimanshu", 98.50);
        javFall2024.assignGrade("Kiran", 99.5);
        javFall2024.assignGrade("Anant", Double.valueOf(100));
        javFall2024.assignGrade("Akshita", 98.5);

        //Retrieve grade of specific student
        System.out.println("studentGrade.getGrade(\"Nimanshu\") = " + javFall2024.getGrade("Nimanshu"));
        System.out.println("studentGrade.getGrade(\"Akshita\") = " + javFall2024.getGrade("Akshita"));

        javFall2024.listAllGrades();

        System.out.println("\nStudent with integer student identifier and integer grade");
        Course<Integer, Integer> javaWinter2025 = new Course();

        //Enroll students for a course
        javaWinter2025.enrollStudent(1);
        javaWinter2025.enrollStudent(2);
        javaWinter2025.enrollStudent(3);

        //Assign grade to specific student
        javaWinter2025.assignGrade(1, 98);
        javaWinter2025.assignGrade(2, 99);
        javaWinter2025.assignGrade(3, 100);
        javaWinter2025.assignGrade(4, 98);

        javaWinter2025.getAllGrades();

        //Retrieve grade of specific student
        System.out.println("studentGrade.getGrade(\"1\") = " + javaWinter2025.getGrade(1));
        System.out.println("studentGrade.getGrade(\"4\") = " + javaWinter2025.getGrade(4));

        javaWinter2025.listAllGrades();
    }
}
