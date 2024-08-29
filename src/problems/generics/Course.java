package problems.generics;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class Course<S, G extends Number & Comparable<G>> {
    Map<S, G> studentToGradeMap;

    public Course() {
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

    public boolean assignGrade(S studentIdentifier, G grade) {
        if (!studentToGradeMap.containsKey(studentIdentifier)) {
            System.out.println("Student with this Identifier does not exists");
            return false;
        } else {
            studentToGradeMap.put(studentIdentifier, grade);
            return true;
        }
    }

    public Optional<G> getGrade(S studentIdentifier) {
        if (!studentToGradeMap.containsKey(studentIdentifier)) {
            System.out.println("Student with this Identifier does not exists");
            return Optional.empty();
        } else {
            return Optional.of(studentToGradeMap.get(studentIdentifier));
        }
    }

    public void listAllGrades() {
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

    public Map<S, G> getAllGrades() {
        return studentToGradeMap;
    }


    public boolean isStudentEnrolled(S i) {
        if (studentToGradeMap.isEmpty()) {
            return false;
        } else return studentToGradeMap.containsKey(i);
    }

    public boolean isCourseNull() {
        if (studentToGradeMap == null) {
            return true;
        }
        return false;
    }

    public double getAverage() {
        double total = 0;
        for (G grade : this.studentToGradeMap.values()) {
            if (grade == null) {
                System.out.println("Student skipped due to grade is null");
            } else {
                total += grade.doubleValue();
            }
        }
        return (total / this.studentToGradeMap.size());
    }

}
