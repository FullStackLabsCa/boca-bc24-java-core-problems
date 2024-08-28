package problems.generics.schoolproblem;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class School<T, S, G extends Number> {
    private Map<T, Map<S, G>> courses;
    private Set<S> uniqueStudents;

    public School() {
        this.courses = new HashMap<>();
        this.uniqueStudents = new HashSet<>();
    }

    public void addCourse(T courseName) {
        if (!courses.containsKey(courseName)) {
            courses.put(courseName, new HashMap<>());
        } else {
            System.out.println("Error: Course " + courseName + " already exists.");
        }
    }

    public void enrollStudent(T courseName, S studentId) {
        if (courses.containsKey(courseName)) {
            Map<S, G> studentsInCourse = courses.get(courseName);
            if (!studentsInCourse.containsKey(studentId)) {
                studentsInCourse.put(studentId, null);
                uniqueStudents.add(studentId);
            } else {
                System.out.println("Error: Student '" + studentId + "' is already enrolled in course '" + courseName + "'.");
            }
        } else {
            System.out.println("Error: Cannot enroll student. Course '" + courseName + "' does not exist.");
        }
    }

    public void assignGrade(T courseName, S studentId, G grade) {
        if (courses.containsKey(courseName)) {
            Map<S, G> studentsInCourse = courses.get(courseName);
            if (studentsInCourse.containsKey(studentId)) {
                studentsInCourse.put(studentId, grade);
            } else {
                System.out.println("Error: Cannot assign grade. Student '" + studentId + "' is not enrolled in course '" + courseName + "'.");
            }
        } else {
            System.out.println("Error: Course '" + courseName + "' does not exist.");
        }
    }

    public void listGrades(T courseName) {
        if (courses.containsKey(courseName)) {
            Map<S, G> studentsInCourse = courses.get(courseName);
            if (studentsInCourse.isEmpty()) {
                System.out.println("No students enrolled in course '" + courseName + "'.");
            } else {
                for (Map.Entry<S, G> entry : studentsInCourse.entrySet()) {
                    System.out.println("Student ID: " + entry.getKey() + ", Grade: " + (entry.getValue() == null ? "Not Assigned" : entry.getValue()));
                }
            }
        } else {
            System.out.println("Error: Course '" + courseName + "' does not exist.");
        }
    }


    public void listCourses() {
        if (courses.isEmpty()) {
            System.out.println("No courses available.");
        } else {
            System.out.println("Courses offered:");
            for (T courseName : courses.keySet()) {
                System.out.println("- " + courseName);
            }
        }
    }

    public void reportUniqueCourses() {
        listCourses();
    }

    public void reportUniqueStudents() {
        if (uniqueStudents.isEmpty()) {
            System.out.println("No students enrolled in any course.");
        } else {
            System.out.println("Unique students enrolled across all courses:");
            for (S studentId : uniqueStudents) {
                System.out.println("- " + studentId);
            }
        }
    }

    public void reportAverageScore(T courseName) {
        if (courses.containsKey(courseName)) {
            Map<S, G> studentsInCourse = courses.get(courseName);
            if (studentsInCourse.isEmpty()) {
                System.out.println("No students enrolled in course '" + courseName + "'.");
            } else {
                double sum = 0;
                int count = 0;
                for (G grade : studentsInCourse.values()) {
                    if (grade != null) {
                        sum += grade.doubleValue();
                        count++;
                    }
                }
                if (count > 0) {
                    double average = sum / count;
                    System.out.println("Average score in course '" + courseName + "': " + average);
                } else {
                    System.out.println("No grades assigned in course '" + courseName + "'.");
                }
            }
        } else {
            System.out.println("Error: Course '" + courseName + "' does not exist.");
        }
    }

    public void reportCumulativeAverage(S studentId) {
        double sum = 0;
        int count = 0;
        for (Map<S, G> studentsInCourse : courses.values()) {
            if (studentsInCourse.containsKey(studentId)) {
                G grade = studentsInCourse.get(studentId);
                if (grade != null) {
                    sum += grade.doubleValue();
                    count++;
                }
            }
        }
        if (count > 0) {
            double average = sum / count;
            System.out.println("Cumulative average score for student '" + studentId + "': " + average);
        } else {
            System.out.println("No grades available for student '" + studentId + "'.");
        }
    }

}
