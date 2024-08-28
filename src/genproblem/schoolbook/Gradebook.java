package genproblem.schoolbook;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Gradebook <S,G> {
    private final Map<String, CourseBook<S, G>> courseGradeBookMap;

    //constructor

    public Gradebook() {
        this.courseGradeBookMap = new HashMap<>();
    }

    //add course from coursebook to add course
    public void addCourse(CourseBook<S, G> course) {
        courseGradeBookMap.put(course.getCourseName(), course);
    }

    //get the course name
    public CourseBook<S, G> getCourse(String courseName) {
        return courseGradeBookMap.get(courseName);
    }

    // add the student to the course from coursebook class
    public void addStudentToCourse(String couseName, S studentId, G grade) {
        CourseBook<S, G> course = courseGradeBookMap.get(couseName);
        if (course != null) {
            course.addStudent(studentId, grade);
        } else {
            System.out.println("Course Not Found");
        }
    }

    //get the student grade
    public G getStudentGrade(String courseName, S studentID) {
        CourseBook<S, G> course = courseGradeBookMap.get(courseName);
        if (course != null) {
            return course.getGrade(studentID);
        } else {
            System.out.println("Course Not Found");
            return null;
        }
    }

    //update the student grade
    public void updateStudentGrade(String courseName, S studentId, G grade) {
        CourseBook<S, G> course = courseGradeBookMap.get(courseName);
        if (course != null) {
            course.upgradeGrade(studentId, grade);
        } else {
            System.out.println("Courses Not Found");
        }
    }

    //get all the grades for course
    public Map<S, G> getAllGradeForCourse(String courseName) {
        CourseBook<S, G> course = courseGradeBookMap.get(courseName);
        if (course != null) {
            return course.getAllGrade();
        } else {
            System.out.println("Course Not Found");
            return Collections.emptyMap();
        }
    }

    public void listCourses() {
        if (courseGradeBookMap.isEmpty()) {
            System.out.println("No Courses available");
        } else {
            System.out.println("List of courses:");
            for (String courseName : courseGradeBookMap.keySet()) {
                System.out.println(courseName);
            }
        }
    }
}
