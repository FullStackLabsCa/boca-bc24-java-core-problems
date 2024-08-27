package generics.course;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseRunner {
    public static void main(String[] args) {
//        Student <-> Course <-> Grade
//        Map<String, Map<String, Double>> studentToCourseMap = new HashMap<>();
//        studentToCourseMap.put("java-fall-2024", null);
//        studentToCourseMap.put("java-winter-2025", null);
//        studentToCourseMap.put("java-spring-2025", null);
//        studentToCourseMap.put("java-summer-2025", null);
//        studentToCourseMap.put("java-fall-2025", null);

        Course<String, Double> course = new Course<>();

        course.enrollStudent("s01");
        course.enrollStudent("s02");

        course.setCourse("s01", "java-fall-2024");
    }
}
