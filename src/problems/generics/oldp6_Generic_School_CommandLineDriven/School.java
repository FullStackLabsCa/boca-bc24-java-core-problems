package problems.generics.oldp6_Generic_School_CommandLineDriven;

import java.util.*;
import java.util.HashMap;
import java.util.Map;

public class School<S, G> {

    private Map<String, Course<S, G>> courses;

    // Constructor
    public School() {
        this.courses = new HashMap<>();
    }

    // Adds a new course to the school
    public void addCourse(String courseName) {
        if (courses.containsKey(courseName)) {
            System.out.println("Error: Course '" + courseName + "' already exists.");
        } else {

            // Create a new GradeBook<S, G> instance
            GradeBook<S, G> gradeBook = new GradeBook<>();

            // Pass courseName and gradeBook to the Course constructor
            courses.put(courseName, new Course<>(courseName, gradeBook));
            System.out.println("Course '" + courseName + "' added.");
        }
    }

    // Enrolls a student in a specified course
    public void enrollStudent(String courseName, S studentId) {
        Course<S, G> course = courses.get(courseName);
        if (course == null) {
            System.out.println("Error: Cannot enroll student. Course '" + courseName + "' does not exist.");
        } else {
            course.enrollStudent(studentId);
            System.out.println("Student '" + studentId + "' enrolled in course '" + courseName + "'.");
        }
    }

    // Assigns a grade to a student in a specified course
    public void assignGrade(String courseName, S studentId, G grade) {
        Course<S, G> course = courses.get(courseName);
        if (course == null) {
            System.out.println("Error: Cannot assign grade. Course '" + courseName + "' does not exist.");
        } else {
            try {
                course.assignGrade(studentId, grade);
                System.out.println("Grade '" + grade + "' assigned to student '" + studentId + "' in course '" + courseName + "'.");
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // Lists all grades for a specified course
    public void listGrades(String courseName) {
        Course<S, G> course = courses.get(courseName);
        if (course == null) {
            System.out.println("Error: Course '" + courseName + "' does not exist.");
        } else {
            Map<S, G> grades = course.listGrades();
            System.out.println("Grades for course '" + courseName + "':");
            for (Map.Entry<S, G> entry : grades.entrySet()) {
                System.out.println("Student: " + entry.getKey() + " - Grade: " + entry.getValue());
            }
        }
    }

    // Lists all courses offered by the school
    public void listCourses() {
        System.out.println("Courses offered:");
        for (String courseName : courses.keySet()) {
            System.out.println(courseName);
        }
    }

    // Reports unique courses offered by the school
    public void reportUniqueCourses() {
        listCourses();
    }

    // Reports unique students enrolled in the school
    public void reportUniqueStudents() {
        Set<S> students = new HashSet<>();
        for (Course<S, G> course : courses.values()) {
            students.addAll(course.listGrades().keySet());
        }
        System.out.println("Unique students enrolled:");
        for (S student : students) {
            System.out.println(student);
        }
    }

    // Reports the average score for a given course
    public void reportAverageScore(String courseName) {
        Course<S, G> course = courses.get(courseName);
        if (course == null) {
            System.out.println("Error: Course '" + courseName + "' does not exist.");
        } else {
            Map<S, G> grades = course.listGrades();
            if (grades.isEmpty()) {
                System.out.println("No grades available for course '" + courseName + "'.");
                return;
            }
            double sum = 0;
            int count = 0;
            for (G grade : grades.values()) {
                // Assuming G is a type that can be converted to a double
                sum += ((Number) grade).doubleValue();
                count++;
            }
            double average = sum / count;
            System.out.println("Average score for course '" + courseName + "': " + average);
        }
    }

    // Reports the cumulative average score for a given student
    public void reportCumulativeAverage(S studentId) {
        double totalSum = 0;
        int totalCount = 0;
        for (Course<S, G> course : courses.values()) {
            Map<S, G> grades = course.listGrades();
            G grade = grades.get(studentId);
            if (grade != null) {
                totalSum += ((Number) grade).doubleValue();
                totalCount++;
            }
        }
        if (totalCount == 0) {
            System.out.println("Error: Student '" + studentId + "' is not enrolled in any courses.");
        } else {
            double average = totalSum / totalCount;
            System.out.println("Cumulative average score for student '" + studentId + "': " + average);
        }
    }

}






