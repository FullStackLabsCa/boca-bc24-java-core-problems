package generics.school;

import java.util.*;

// NOTE: NO NEED FOR THIS CLASS UNTIL TO MANAGE MODULARITY

public class Course<S, G extends Number> {
    public final Map<S, G> studentToGrade = new HashMap<>();

    public void enrollStudent(S studentIdentifier) {
        studentToGrade.putIfAbsent(studentIdentifier, null);
    }

    public void assignGrade(S studentIdentifier, G grade) {
        studentToGrade.put(studentIdentifier, grade);
    }

    public Map<S, G> getStudentToGrade() {
        return studentToGrade;
    }

     public void printMap() {
         System.out.println("\nstudentToGrade===\n" + studentToGrade);
     }
}
