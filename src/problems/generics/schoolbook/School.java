package problems.generics.schoolbook;

import java.util.HashMap;
import java.util.Map;

public class School<S, G extends Number> {
    Map<String, Course<S,G>> schoolToCourses;

    public School() {
        this.schoolToCourses = new HashMap<>();
    }

    public void addCourse(String courseName, Course<S, G> course) {
        if (!this.schoolToCourses.containsKey(courseName)) {
            this.schoolToCourses.put(courseName, course);
        } else System.out.println("Course already present.");
    }

    public Course<S,G> getCourse(String courseName){
        return this.schoolToCourses.get(courseName);
    }

    public Map<String, Course<S, G>> getSchoolToCourses() {
        return schoolToCourses;
    }

    public Course<S,G> listGrades(String courseName){
        return this.schoolToCourses.get(courseName);
    }

}
