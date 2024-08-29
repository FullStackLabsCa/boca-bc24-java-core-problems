package problems.generics.school;

import problems.generics.course.Course;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class School<S, G extends Number> {
    Set<String> courseName;
    Map<String, Course<S, G>> courseToStudentGrade;

    public School() {
        this.courseName = new HashSet<>();
        this.courseToStudentGrade = new HashMap<>();
    }

    public Set<String> getCourseName() {
        return courseName;
    }

    public Map<String, Course<S, G>> getCourseToStudentGrade() {
        return courseToStudentGrade;
    }

    public School(Set<String> courseName, Map<String, Course<S, G>> courseToStudentGrade) {
        this.courseName = courseName;
        this.courseToStudentGrade = courseToStudentGrade;
    }

    public Map<String, Course<S, G>> add_Course(String name){
        courseToStudentGrade.put(name, null);
        return courseToStudentGrade;
    }

    public Map<String, Course<S, G>> enroll_Student(String name, Course course){
        courseToStudentGrade.put(name, course);
        return courseToStudentGrade;
    }

    public Map<String, Course<S, G>> enrollStudent(String courseName, S student, G grade) {
        Course<S, G> course = courseToStudentGrade.get(courseName);
        if (course != null) {
            course.assignGrade(student, grade);
        } else {
            System.out.println("Course not found.");
        }
        return courseToStudentGrade;
    }

    public void processCommand(String command) {
        String[] array= command.split(" ");
        if("add_course".equals(array[0])){
            courseName.add(array[1]);
            System.out.println("Course '" + array[1] + "' added.");
        }

        if("list_courses".equals(array[0])){
            String[] arrayCourseName = courseName.toArray(new String[courseName.size()]);
            System.out.println("\n" + "Courses offered:");
            for (String s : arrayCourseName) {
                System.out.println(s);
            }
        }

        if("enroll_student".equals(array[0])){
            String course = array[1];
            S student = (S) array[2];
            G grade = null;

            Course<S, G> courseObj = new Course<>();
            courseObj.assignGrade(student,grade);
            courseToStudentGrade.put(array[1], courseObj);
            System.out.println("Student '" + array[2] + "' enrolled in course '" + array[1] + "'.");
        }
    }
}
