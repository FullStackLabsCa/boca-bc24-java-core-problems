package generic_problems.school;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class School<S,G extends Number> {
    private String courseName;
    Map<String,Course<S, G>> courseNameToCourseObjectMap;

    public School() {
        this.courseNameToCourseObjectMap = new HashMap<>();
    }

    public void addCourse(String courseName) {
        if (courseNameToCourseObjectMap.containsKey(courseName)) {
            System.out.println("Course already exist");
        } else {
            courseNameToCourseObjectMap.put(courseName,new Course<>());
        }
    }

    public void enrollStudent(String courseName,S student) {
        if (courseNameToCourseObjectMap.containsKey(courseName)) {
            if (courseNameToCourseObjectMap.get(courseName).enrollStudentToCourse(student)) {
                System.out.println(student + " is enrolled in " + courseName);
            }else System.out.println(student + " is already enrolled in " + courseName);
        } else {
            System.out.println("Course Doesn't Exist");
        }
    }


    public void assignGrade(String courseName, S student, G grade ) {
        if (courseNameToCourseObjectMap.containsKey(courseName)) {
            courseNameToCourseObjectMap.get(courseName).assignGrade(student, grade);
        } else {
            System.out.println("Course Doesn't Exist");
        }
    }


    public void listOfCourses() {
        if (courseNameToCourseObjectMap.isEmpty()) {
            System.out.println("There is no Course available");
        } else {
            System.out.println("List of Courses = " + courseNameToCourseObjectMap.keySet());
        }
    }


    public void listOfStudents() {
        if (courseNameToCourseObjectMap.isEmpty()) {
            System.out.println("Course Doesn't Exist");
        } else {
            Set<S> ListOfStudent = new HashSet<>();
            Set<Map.Entry<String, Course<S, G>>> studentsListEntrySet = courseNameToCourseObjectMap.entrySet();
            for (Map.Entry<String, Course<S, G>> courseNameObjectEntrySet : studentsListEntrySet) {
                String key = courseNameObjectEntrySet.getKey();
                ListOfStudent.addAll(courseNameToCourseObjectMap.get(key).getStudents());
            }
            System.out.println("studentsList = " + ListOfStudent);
        }
    }

    public void averageScoreInCourse(String courseName) {
        if (courseNameToCourseObjectMap.isEmpty()) {
            System.out.println("Course Doesn't Exist");
        } else {
            double courseAverage = courseNameToCourseObjectMap.get(courseName).averageScore();
                System.out.println("Avergae score in "+ courseName +" is "+courseAverage);
            }
        }

    public void studentAverageGrade(S student) {
        double totalMarks = 0, studentAvg=0;
        int count = 0;
        if (courseNameToCourseObjectMap.isEmpty()) {
            System.out.println("Course Doesn't Exist");
        } else {
            Set<Map.Entry<String, Course<S, G>>> studentGradeAverage = courseNameToCourseObjectMap.entrySet();
            for (Map.Entry<String, Course<S, G>> courseNameObjectEntrySet : studentGradeAverage) {
                String key = courseNameObjectEntrySet.getKey();
                double courseMarks = courseNameToCourseObjectMap.get(key).retrieveGrade(student);
                totalMarks += courseMarks;
                count++;
            }
            studentAvg = totalMarks / count;
        }
        System.out.println("Average student : "+studentAvg);
    }
}
