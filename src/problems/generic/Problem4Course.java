package problems.generic;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("java:S106")
public class Problem4Course {
    public static void main(String[] args) {
        Course<String, Double> course = new Course<>();
        course.enrollingStudent().put("Harry", null);
        course.enrollingStudent().put("Garry", null);
        course.enrollingStudent().put("Parry", null);

        course.setGradeToStudent("Harry", 76.50);

        String key = String.valueOf(course.retrievingGrades("Harry"));
        System.out.println("key = " + key);

        course.getGradeValues();

        System.out.println("course.mapOfStudentToGrade = " + course.mapOfStudentToGrade);
    }

    public static class Course<S, G extends Number> {
        Map<S, G> mapOfStudentToGrade = new HashMap<>();

        public Map<S, G> enrollingStudent() {
            return mapOfStudentToGrade;
        }

        public G retrievingGrades(S key) {
            G value = null;
            if (mapOfStudentToGrade.containsKey(key)) {
                value = mapOfStudentToGrade.get(key);
            }
            return value;
        }

        public void getGradeValues() {
            System.out.println("mapOfStudentToGrade.values() = " + mapOfStudentToGrade.values());

        }

        public void setGradeToStudent(S key, G value) {
            if (key == null) {
                System.out.println("key should not be null..... ");
                return;
            }

            mapOfStudentToGrade.putIfAbsent(key, value);
//            if (mapOfStudentToGrade.containsKey(key)) {
//                mapOfStudentToGrade.put(key, value);
//                System.out.println("Grade added/updated");
//            } else {
//                System.out.println(key+" : key not found in the map");
//            }
        }
    }
}
