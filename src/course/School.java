package course;

import java.util.Arrays;
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
        System.out.println("Course '"+courseName+"' added.");
        return courseNameToCourseMap;
    }

public Map<String,Course> enrollStudent(String name, Course course){
    courseNameToCourseMap.put(name,course);
    return courseNameToCourseMap;
}


public void processCommand(String command) {
    String[] strArray = command.trim().split("\\s+");
    String testCommand = strArray[0];
    String testParameter = strArray[1];
        if("add_course".equals(testCommand)){
            addCourseNameToSchool(testParameter);
        }
    }
}
