package problems;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class GenericCourse<S, G extends Number & Comparable<G>> {
    Map<S, G> studentToGradeMap;

    public GenericCourse() {
        this.studentToGradeMap = new HashMap<>();

    }

    public boolean enrollStudent(S studentIdentifier) {
        if (studentToGradeMap.isEmpty()) {
            studentToGradeMap.put(studentIdentifier, null);
            return true;
        } else if (studentToGradeMap.containsKey(studentIdentifier)) {
            System.out.println("Student with this Identifier already exists");
            return false;
        } else {
            studentToGradeMap.put(studentIdentifier, null);
            return true;
        }
    }

    public boolean enrollStudent(S studentIdentifier, G grade) {
        if (studentToGradeMap.containsKey(studentIdentifier)) {
            System.out.println("Student with this Identifier already exists");
            return false;
        } else {
            studentToGradeMap.put(studentIdentifier, grade);
            return true;
        }
    }

    public boolean setGradeforAStuent(S studentIdentifier, G grade) {
        if (!studentToGradeMap.containsKey(studentIdentifier)) {
            System.out.println("Student with this Identifier does not exists");
            return false;
        } else {
            studentToGradeMap.put(studentIdentifier, grade);
            return true;
        }
    }

    public G getGradeOfAStudent(S studentIdentifier) {
        if (!studentToGradeMap.containsKey(studentIdentifier)) {
            System.out.println("Student with this Identifier does not exists");
            return null;
        } else {
            return studentToGradeMap.get(studentIdentifier);
        }
    }

    public void displayAllStudentsAndGrade() {
        if (studentToGradeMap.isEmpty()) {
            System.out.println("There is no student in this Course yet!");
        } else {
            Set<S> studentIdentifierSet = this.studentToGradeMap.keySet();
            for (S student : studentIdentifierSet) {
                System.out.println("Student Identifier :\t" + student + "\t Grade :\t" + studentToGradeMap.get(student));
            }
        }
    }

    public Map<S, G> getAllStudentsAndGrade() {
        return studentToGradeMap;
    }

}
