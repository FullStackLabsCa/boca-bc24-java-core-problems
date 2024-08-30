package course;

import com.sun.jdi.Value;
import com.sun.source.tree.UsesTree;

import java.security.KeyStore;
import java.util.*;

public class School <S,G extends Number>{

    private Map<String,Course> courseNameToCourseMap = new HashMap<>();
   // Course<S,G> course = new Course<>();

//Map<CourseName,<Student,Grade>>

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
            addCourseNameToSchool(strArray[1]);

//            courseName.add((String) strArray[1]);
            courseNameToCourseMap.put(strArray[1],new Course<>());
        }
        else if("list_courses".equals(strArray[0])){
            System.out.println("Courses offered:");
            System.out.println(courseNameToCourseMap.keySet());
        }
        else if("enroll_student".equals(strArray[0])){
            //        school.processCommand("enroll_student Math101 12345");
            String courseName = strArray[1];
            S studentName = (S) strArray[2];
            if(!courseNameToCourseMap.containsKey(strArray[1])){
                System.out.println("Error: Cannot enroll student. Course '"+strArray[1]+"' does not exist.");
            } else {
                Course<S,G> course = courseNameToCourseMap.get(courseName);
                course.enrollStudent(studentName);
                enrollStudent(strArray[1],course);
                System.out.println("Student '"+strArray[2]+"' enrolled in course '"+strArray[1]+"'.");
            }

        }
        else if("assign_grade".equals(strArray[0])){
            String courseName = strArray[1];
            S studentName = (S) strArray[2];
            Double grade =  Double.parseDouble(strArray[3]);
            Course<S,G> course = courseNameToCourseMap.get(courseName);
            if(!course.isStudentEnrolled(studentName)){
                System.out.println("Error: Cannot assign grade. Student '"+studentName+"' is not enrolled in course '"+courseName+"'.");
            }
//            for(Course<S,G>course:courseNameToCourseMap.values()){
//
//            }
            else if(course.isStudentEnrolled(studentName)){
//                courseNameToCourseMap.put(strArray[1],course);
                  course.assignGrade(studentName, (G) grade);
                System.out.println("Grade '"+grade+"' assigned to student '"+studentName+"' in course '"+courseName+"'.");
            }
        }
        else if("list_grades".equals(strArray[0])){
            String courseName = strArray[1];
            Course<S,G> course = courseNameToCourseMap.get(courseName);
//          S studentName = (S) strArray[2];
//          Double grade =  Double.parseDouble(strArray[3]);
            if(courseNameToCourseMap.containsKey(courseName)){
                for(Map.Entry<S,G> entry : course.getAllGrades().entrySet()  ){
                    System.out.println("Student: "+entry.getKey()+", Grade: "+entry.getValue());
                }
//                System.out.println("Student: "+course.listAllGrades().keySet()+", Grade: "+course.listAllGrades().values());
            }
        }
        else if("report_unique_courses".equals(strArray[0])){
            System.out.println("Courses offered:");
            for(Map.Entry<String,Course> entry : courseNameToCourseMap.entrySet()){
                System.out.println(entry.getKey());
            }
        }
        else if("report_unique_students".equals(strArray[0])){
            System.out.println("Unique students enrolled:");
            for(Course<S,G> entry : courseNameToCourseMap.values()){
                System.out.println(entry.getAllGrades().keySet());
            }
        }
        else if("report_average_score".equals(strArray[0])){
            if(courseNameToCourseMap.containsKey(strArray[1])){
                for(Course<S,G> entry : courseNameToCourseMap.values()){
                    for(G i : entry.getAllGrades().values()) {
                        System.out.println("Average score for course '" + strArray[1] + "': " + i);
                    }
                }
            }
        }
        else if("report_cumulative_average".equals(strArray[0])){
            S studentName = (S) strArray[1];
            double totalgrades = 0;
            int count = 0;

//            if(course.isStudentEnrolled(studentName)){
                for(Course<S,G> course : courseNameToCourseMap.values()) {
                    G grade = course.getStudentGrade(studentName);
                     totalgrades += grade.doubleValue();
                     count ++;
                }
//            }
            double cumulativeAvg = totalgrades/count;
            System.out.println("Cumulative average score for student '" + studentName + "': " + cumulativeAvg);

        }
        else{
            System.out.println("Error: Unknown command 'unknown_command'. Please use a valid command.");
        }

    }
}
