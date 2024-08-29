package course;

import com.sun.source.tree.UsesTree;

import java.util.*;

public class School <S,G extends Number>{
    private Set<String> courseName;

    public Set<String> getCourseName() {
        return courseName;
    }

    public void setCourseName(Set<String> courseName) {
        this.courseName = courseName;
    }

    public School() {
        this.courseNameToCourseMap = new HashMap<>();
        this.courseName = new HashSet<>();
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
//    String testCommand = strArray[0];
//    String testParameter = strArray[1];
        if("add_course".equals(strArray[0])){
            addCourseNameToSchool((String) strArray[1]);
            courseName.add((String) strArray[1]);
        }
        if("list_courses".equals(strArray[0])){
            System.out.println("Courses offered:");
            System.out.println(courseName);
        }
        if("enroll_student".equals(strArray[0])){
            Course<S,G> course = new Course<>();
            String courseName = strArray[1];
            S studentName = (S) strArray[2];
            G grade = null;

            if(courseName.contains(strArray[1])){
                enrollStudent(strArray[1],course);
                System.out.println("Student '"+strArray[2]+"' enrolled in course '"+strArray[1]+"'.");
            }
        }
    }
}
