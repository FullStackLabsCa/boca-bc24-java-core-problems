package genproblem.schoolbook;

import java.util.*;

public class Gradebook<S,G extends Number> {

    private final Map<String, CourseBook<S,G>> courseGradeBookMap;

    // constructor
    public Gradebook() {
        this.courseGradeBookMap = new HashMap<>();
    }

    //add course and get courseName
    public void addCourse(CourseBook<S,G> course) {
        courseGradeBookMap.put(course.getCourseName(), course);
    }

    //add students to the course
    public void addStudentToCourse(String courseName, S studentId,G grade) {
        CourseBook<S,G> course = courseGradeBookMap.get(courseName);

        if (course != null) {
            course.addStudent(studentId, grade);
        } else {
            System.out.println("Error: Cannot enroll student. Course '" + courseName + "' does not exist.");
        }
    }
    //update the student with grades

    public void updateStudentGrade(String courseName, S studentId, G grade) {
        CourseBook<S,G> course = courseGradeBookMap.get(courseName);
        if (course != null) {
            if (course.hasStudent(studentId)) {
                course.updateGrade(studentId, grade);
            } else {
                System.out.println("Error: Cannot assign grade. Student '" + studentId + "' is not enrolled in course '" + courseName + "'.");
            }
        } else {
            System.out.println("Error: Course '" + courseName + "' does not exist.");
        }
    }

    //get all the grades for the course

    public Map<S,G> getAllGradesForCourse(String courseName) {
        CourseBook<S,G> course = courseGradeBookMap.get(courseName);
        if (course != null) {
            return course.getAllGrades();
        } else {
            System.out.println("Error: Course '" + courseName + "' does not exist.");
            return Collections.emptyMap();
        }
    }
   //list all the courses
    public void listCourses() {
        if (courseGradeBookMap.isEmpty()) {
            System.out.println("No courses available.");
        } else {
            System.out.println("Courses offered:");
            for (String courseName : courseGradeBookMap.keySet()) {
                System.out.println(courseName);
            }
        }
    }
//get the unique students
    public Set<S> getUniqueStudents() {
        Set<S> uniqueStudents = new HashSet<>();
        for (CourseBook<S,G> course : courseGradeBookMap.values()) {
            uniqueStudents.addAll(course.getAllGrades().keySet());
        }
        return uniqueStudents;
    }
//calculate the score
    public double calculateAverageScore(String courseName) {
        CourseBook<S,G> course = courseGradeBookMap.get(courseName);
        if (course == null || course.getAllGrades().isEmpty()) {
            System.out.println("Error: No grades available for course '" + courseName + "'.");
            return 0.0;
        }
        double total = 0;
        int count = 0;
        for (G grade : course.getAllGrades().values()) {
            total += grade.doubleValue();
            count++;
        }
        return total / count;
    }

    //calculate the cumulative average
    public double calculateCumulativeAverage(S studentId) {
        double total = 0;
        int count = 0;
        for (CourseBook<S,G> course : courseGradeBookMap.values()) {
            G grade = course.getGrade(studentId);
            if (grade != null) {
                total += grade.doubleValue();
                count++;
            }
        }
        return count > 0 ? total / count : 0.0;
    }
// get all courses
    public Set<String> getCourses() {
        return courseGradeBookMap.keySet();
    }
}
