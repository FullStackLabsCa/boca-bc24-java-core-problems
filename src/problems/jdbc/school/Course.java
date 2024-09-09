package problems.jdbc.school;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@SuppressWarnings("java:S106")
public class Course<S, G extends Number> {
    Map<S, G> mapOfStudentToGrade = new HashMap<>();

    public Map<S, G> enrollStudent(S studentId) {
        mapOfStudentToGrade.put(studentId, null);
        return mapOfStudentToGrade;
    }

    public Optional<G> getGrade(S key) {
        G value = null;
        if (mapOfStudentToGrade.containsKey(key)) {
            value = mapOfStudentToGrade.get(key);
            return Optional.of(value);
        }

        return Optional.empty();
    }

    public S getStudents(){
        S key = null;
        for (S s : mapOfStudentToGrade.keySet()) {
            key = s;
        }
        return key;
    }

    public Map<S, G> getAllGrades() {
        return mapOfStudentToGrade;
    }

    public Map<S, G> listAllGrades() {
        S key = null;
        G value = null;
        for (S s : mapOfStudentToGrade.keySet()) {
            key = s;
        }

        for (G v : mapOfStudentToGrade.values()) {
            value = v;
        }

        System.out.println("Student: " + key+ ", Grade: " + value);
        return mapOfStudentToGrade;
    }

    public void assignGrade(S key, G value) {
        if (key == null) {
            System.out.println("key should not be null..... ");
            return;
        }

//        mapOfStudentToGrade.putIfAbsent(key, value);
        if (mapOfStudentToGrade.containsKey(key)) {
            mapOfStudentToGrade.put(key, value);
            System.out.println("Grade added/updated");
        } else {
            System.out.println(key + " : key not found in the map");
        }
    }

    public boolean isStudentEnrolled(S key) {
        if (mapOfStudentToGrade.containsKey(key))
            return true;
        return false;
    }

    public static void main(String[] args) {
        Course<String, Double> student = new Course<>();
        student.enrollStudent("Harry");
        student.enrollStudent("Garry");
        student.enrollStudent("Parry");

        student.assignGrade("Harry", 76.50);

        String key = String.valueOf(student.getGrade("Harry"));
        System.out.println("key = " + key);

        student.getAllGrades();

        System.out.println("course.mapOfStudentToGrade = " + student.mapOfStudentToGrade);
    }
}
