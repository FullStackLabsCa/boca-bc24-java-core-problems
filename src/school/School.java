package school;

import course.Course;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class School <S,G extends Number>{
    private Set<String> courseName;
    private Map<String, Course<S,G>> courseToStudentGrade;


    public Set<String> getCourseName() {
        return courseName;
    }

    public School() {
        this.courseName = new HashSet<>();
        this.courseToStudentGrade = new HashMap<>();
    }


    public Map<String, Course<S, G>> getCourseToStudentGrade() {
        return courseToStudentGrade;
    }

    public School(Set<String> courseName, Map<String, Course<S, G>> courseToStudentGrade) {
        this.courseName = courseName;
        this.courseToStudentGrade = courseToStudentGrade;
    }
    public Map<String, Course<S,G>> add_Course(String name){
       courseToStudentGrade.put(name, null);
       return courseToStudentGrade;
    }
    public Map<String,Course<S,G>> enroll_Student(String name, Course course){
        courseToStudentGrade.put(name,course);
        return courseToStudentGrade;
    }

    public void processCommand(String command) {
        String[] parts = null;
        parts = command.split(" ");
        if (command.startsWith("add_course")) {
            if (parts.length == 2) {
                String name = parts[1];
                courseName.add(name);
                System.out.println("Course '" + name + "' added.");
            } else {
                System.out.println("Invalid command format.");
            }
        } else if (command.startsWith("list_courses")) {
            System.out.println("Courses offered:");
            for (String course : courseName) {
                System.out.println(course);
            }
        } else if (command.startsWith("enroll_student")) {
            if(courseName.contains(parts[1])) {
                System.out.println("Student '" + parts[2] + "' enrolled in course '" + parts[1] + "'.");
            }else{
                System.out.println("Error: Cannot enroll student. Course '"+parts[1]+"' does not exist.");
            }
        }
    }
}
