package course;

import java.util.HashMap;
import java.util.Map;

public class School <S,G extends Number>{
    private String courseName;

    public School() {
        this.courseNameToCourseMap = new HashMap<>();
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    private Map<String,Course> courseNameToCourseMap = new HashMap<>();

    public Map<String,Course> addCourseNameToSchool(String courseName){
        courseNameToCourseMap.put(courseName,null);
        return courseNameToCourseMap;
    }

    public Map<String,Course> enrollStudent(String name, Course course){
        courseNameToCourseMap.put(name,course);
        return courseNameToCourseMap;
    }


}
