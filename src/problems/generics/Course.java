package problems.generics;

import javax.swing.text.html.Option;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Course<S, G extends Number> {

    Map<S, G> studentToGradeMap;

    public Course() {
        studentToGradeMap = new HashMap<>();
    }

    public void enrollStudent(S studentId) {
        if (!studentToGradeMap.containsKey(studentId)) {
            studentToGradeMap.put(studentId, null);
        }
    }

    public void assignGrade(S studentId, G grade) {
        if (studentToGradeMap.containsKey(studentId)) {
            studentToGradeMap.put(studentId, grade);
        } else {
            System.out.println("Student not enrolled in the course.");
        }
    }


    public Optional<G> retrieveGrade(S studentId) {

        return Optional.of(studentToGradeMap.get(studentId));
    }

    public Optional<G> getGrade(S studentId) {
        if (studentToGradeMap.containsKey(studentId)) {
            return Optional.of(studentToGradeMap.get(studentId));
        } else {
            return Optional.empty();
        }
    }

    public Map<S, G> listAllGrades() {
        return new HashMap<>(studentToGradeMap);
    }

    public Map<S, G> getAllGrades() {
        return new HashMap<>(studentToGradeMap);
    }

    public boolean isStudentEnrolled(S i) {
        boolean isEnrolled = true;
        if (!studentToGradeMap.containsKey(i)) {
            isEnrolled = false;
        }
        return isEnrolled;
    }


    public Double averageOfGrades() {
        Double average = 0.0;
        for (Map.Entry<S, G> entry : studentToGradeMap.entrySet()) {
            if (entry.getValue() != null) {
                average += (entry.getValue()).doubleValue();
            }
        }
        for (G value : studentToGradeMap.values()) {
            System.out.println(value);
        }
        return average / studentToGradeMap.size();
    }


}

