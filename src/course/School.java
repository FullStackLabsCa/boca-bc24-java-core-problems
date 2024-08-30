package course;

import com.sun.jdi.Value;
import com.sun.source.tree.UsesTree;

import java.security.KeyStore;
import java.util.*;

public class School <S,G extends Number>{

    private Map<String,Course> courseNameToCourseMap = new HashMap<>();
    Course<S,G> course = new Course<>();



//    public Set<String> getCourseName() {
//        return courseNameToCourseMap.keySet();
//    }
//
//    public void setCourseName(Set<String> courseName) {
//        this.courseName = courseName;
//    }

    public School() {
        this.courseNameToCourseMap = new HashMap<>();
//        this.courseName = new HashSet<>();
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

//            courseName.add((String) strArray[1]);
            courseNameToCourseMap.put(strArray[1],new Course<>());
        }
        if("list_courses".equals(strArray[0])){
            System.out.println("Courses offered:");
            System.out.println(courseNameToCourseMap.keySet());
        }

        if("enroll_student".equals(strArray[0])){
            //        school.processCommand("enroll_student Math101 12345");
            String courseName = strArray[1];
            S studentName = (S) strArray[2];
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
//                courseNameToCourseMap.put(strArray[1],course);
                  course.assignGrade(studentName, (G) grade);
                System.out.println("Grade '"+grade+"' assigned to student '"+studentName+"' in course '"+courseName+"'.");
            }
        }

        if("list_grades".equals(strArray[0])){
            String courseName = strArray[1];
//          S studentName = (S) strArray[2];
//          Double grade =  Double.parseDouble(strArray[3]);
            if(courseNameToCourseMap.containsKey(courseName)){
                for(Map.Entry<S,G> entry : course.getAllGrades().entrySet()  ){
                    System.out.println("Student: "+entry.getKey()+", Grade: "+entry.getValue());
                }
//                System.out.println("Student: "+course.listAllGrades().keySet()+", Grade: "+course.listAllGrades().values());
            }
        }

        if("report_unique_courses".equals(strArray[0])){
            System.out.println("Courses offered:");
            for(Map.Entry<String,Course> entry : courseNameToCourseMap.entrySet()){
                System.out.println(entry.getKey());
            }
        }

    }
}
