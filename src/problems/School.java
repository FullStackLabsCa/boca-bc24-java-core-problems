package problems;

import java.util.HashMap;
import java.util.Map;

public class School<S,G extends Number>{
    //Map<S,G> studentToGradeMap;
    Map<String, Course<S,G>> courses= new HashMap<>();

//    "java-fall-2025" <-> course
//    "java-fall-2025" <-> course
//    "java-fall-2025" <-> course
//    "java-fall-2025" <-> course
//    "java-fall-2025" <-> course


    public Map<String, Course<S, G>> getCourses() {
        return courses;
    }

    public void setCourses(Map<String, Course<S, G>> courses) {
        this.courses = courses;
    }
}
