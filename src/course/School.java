package course;

import com.sun.source.tree.UsesTree;

import java.util.*;

public class School <S,G extends Number>{
    private Set<String> courseName;
    private Map<String,Course> courseNameToCourseMap = new HashMap<>();
    Course<S,G> course = new Course<>();



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
            courseNameToCourseMap.put(strArray[1],null);
        }
        if("list_courses".equals(strArray[0])){
            System.out.println("Courses offered:");
            System.out.println(courseName);
        }

        if("enroll_student".equals(strArray[0])){
            String courseName = strArray[1];
            S studentName = (S) strArray[2];
            G grade =  null;
            if(!courseNameToCourseMap.containsKey(strArray[1])){
                System.out.println("Error: Cannot enroll student. Course '"+strArray[1]+"' does not exist.");
            } else if (courseName.contains(strArray[1])){
                course.enrollStudent(studentName);
                enrollStudent(strArray[1],course);
                System.out.println("Student '"+strArray[2]+"' enrolled in course '"+strArray[1]+"'.");
            }

        }
        if("assign_grade".equals(strArray[0])){
            String courseName = strArray[1];
            S studentName = (S) strArray[2];
            Double grade =  Double.parseDouble(strArray[3]);
            if(!course.isStudentEnrolled(studentName)){
                System.out.println("Error: Cannot assign grade. Student '"+studentName+"' is not enrolled in course '"+courseName+"'.");
            }
            else if(course.isStudentEnrolled(studentName)){
                courseNameToCourseMap.put(strArray[1],course);
                System.out.println("Grade '"+grade+"' assigned to student '"+studentName+"' in course '"+courseName+"'.");
            }
        }

    }
}
