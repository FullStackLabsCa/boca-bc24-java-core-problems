package problems.generics.schoolbook;

import java.util.HashMap;
import java.util.Map;

public class School<C, S, G extends Number> {
    Map<C, Course<S,G>> schoolToCourses;

    public School() {
        this.schoolToCourses = new HashMap<>();
    }

    public void addCourse(C courseName, Course<S, G> course) {
        if (!this.schoolToCourses.containsKey(courseName)) {
            this.schoolToCourses.put(courseName, course);
        } else System.out.println("Course already present.");
    }

    public Course<S,G> getCourse(C courseName){
        return this.schoolToCourses.get(courseName);
    }

    public Map<C, Course<S, G>> getSchoolToCourses() {
        return schoolToCourses;
    }

    public Course<S,G> listGrades(C courseName){
        return this.schoolToCourses.get(courseName);
    }

}
