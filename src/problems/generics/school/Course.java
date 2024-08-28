package generics.school;

import java.util.*;

public class Course<S, G> {
    Map<String, Map<S, G>> studentToGrade = new HashMap<>();
    public void addCourse(String course) {
        if (!studentToGrade.containsKey(course)) {
            studentToGrade.putIfAbsent(course, new HashMap<>());
            System.out.println("Course " + course + " added.\n");
        } else {
            System.out.println("Course " + course + " already exists.\n");
        }
    }

    public void removeCourse(String course) {
        if (studentToGrade.containsKey(course)) {
            studentToGrade.remove(course);
        } else {
            System.out.println("Student with this identifier doesn't exist in a map.!");
        }
    }

    public void getCourseList() {
        ArrayList<String> courseList = new ArrayList<>();
        for (Map.Entry<String, Map<S, G>> courseKeyList: studentToGrade.entrySet()) {
            courseList.add(courseKeyList.getKey());
        }
        System.out.println("Courses offered:\n" + courseList);
    }

    public void enrollStudent(String course, S studentIdentifier) {
        if (studentToGrade.containsKey(course) && studentToGrade.get(course) != null) {
            studentToGrade.get(course).put(studentIdentifier, null);
            System.out.println("enroll_student " + course + " " + studentIdentifier);
        } else {
            System.out.println("Error: Cannot enroll student. Course '" + course + "' does not exist.");
        }
    }

    public void assignGrade(String course, S studentIdentifier, G grade) {
        if (studentToGrade.get(course).containsKey(studentIdentifier)) {
            studentToGrade.get(course).put(studentIdentifier, grade);
            System.out.println("Grade '" + grade + "' assigned to student '" + studentIdentifier + "' in course '" + course + "'.\n");
        } else {
            System.out.println("Error: Cannot assign grade. Student '" + studentIdentifier + "' is not enrolled in course '" + course + "'.");
        }
    }

    public void listGrades(String course) {
        if (studentToGrade.containsKey(course)) {
            System.out.println("Grades for course '" + course + "':");
            for(Map.Entry<S, G> studentGradeEntry: studentToGrade.get(course).entrySet()) {
                System.out.println("Student: " + studentGradeEntry.getKey() + " - Grade: " + studentGradeEntry.getValue());
            }
        } else {
            System.out.println("Error: Course '" + course + "' does not exist.");
        }
    }

    public void reportUniqueStudents() {
        Set<S> students = new HashSet<>();
        for (Map<S, G> studentGrade: studentToGrade.values()) {
            students.addAll(studentGrade.keySet());
        }
        System.out.println("Unique students enrolled:\n" + students);
    }

    public void reportAverageScore() {

    }

    public void reportCumulativeAverage() {

    }

     public void printMap() {
         System.out.println("\nstudentToGrade===\n" + studentToGrade);
     }
}
