package generic_problems.school;

import java.util.*;


public class School<S, G extends Number> {
    private String courseName;
    Map<String, Course<S, G>> courseNameToCourseObjectMap;

    public School() {
        courseNameToCourseObjectMap = new HashMap<>();
    }

    public void addCourse(String courseName) {
        if (courseNameToCourseObjectMap.containsKey(courseName)) {
            System.out.println("Course already exist");
        } else {
            courseNameToCourseObjectMap.put(courseName, new Course<>());
            System.out.println("Course '" + courseName + "' added.");
        }
    }

    public void enrollStudent(String courseName, S student) {
        String output = null;
        if (courseNameToCourseObjectMap.containsKey(courseName)) {
            if (courseNameToCourseObjectMap.get(courseName).enrollStudentToCourse(student)) {
                System.out.println("Student '" + student + "' enrolled in course '" + courseName + "'.");
            }
        } else {
            System.out.println("Error: Cannot enroll student. Course '" + courseName + "' does not exist.");
        }
    }


    public void assignGrade(String courseName, S student, G grade) {
        if (courseNameToCourseObjectMap.containsKey(courseName)) {
            if (courseNameToCourseObjectMap.get(courseName).assignGrade(student, grade)) {
                System.out.println("Grade '" + grade + "' assigned to student '" + student + "' in course '" + courseName + "'.");
            } else
                System.out.println("\"Error: Cannot assign grade. Student '" + student + "' is not enrolled in course '" + courseName + "'.");

        } else {
            System.out.println("Course Doesn't Exist");
        }
    }


    public void listOfCourses() {
        if (courseNameToCourseObjectMap.isEmpty()) {
            System.out.println("There is no Course available");
        } else {
            System.out.println("Courses offered:" + courseNameToCourseObjectMap.keySet());
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
            System.out.println("Unique students enrolled:" + ListOfStudent);
        }
    }

    public void averageScoreInCourse(String courseName) {
        if (courseNameToCourseObjectMap.isEmpty()) {
            System.out.println("Course Doesn't Exist");
        } else {
            double courseAverage = courseNameToCourseObjectMap.get(courseName).averageScore();
            System.out.println("Average score for course '" + courseName + "': " + courseAverage);
        }
    }

    public void studentAverageGrade(S student) {
        double totalMarks = 0, studentAvg = 0;
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
        System.out.println("Cumulative average score for student '" + student + "': " + studentAvg);
    }

    public void gradesPerCourse(String courseName) {
        System.out.println(courseNameToCourseObjectMap.get(courseName).listAllGrades());
    }

    public void processCommand(String userCommand) {
        String[] spiltUserCommand = userCommand.split("\\s");
        switch (spiltUserCommand[0]) {
            case "add_course":
                addCourse(spiltUserCommand[1]);
                break;
            case "list_courses":
                listOfCourses();
                break;
            case "enroll_student":
                enrollStudent(spiltUserCommand[1], (S) spiltUserCommand[2]);
                break;
            case "assign_grade":
                assignGrade(spiltUserCommand[1], (S) spiltUserCommand[2], (G) Double.valueOf(spiltUserCommand[3]));
                break;
            case "list_grades":
                gradesPerCourse(spiltUserCommand[1]);
                break;
            case "report_unique_courses":
                listOfCourses();
                break;
            case "report_unique_students":
                listOfStudents();
                break;
            case "report_average_score":
                averageScoreInCourse(spiltUserCommand[1]);
                break;
            case "report_cumulative_average":
                studentAverageGrade((S) spiltUserCommand[1]);
                break;
            case "unknown_command":
                System.out.println("Error: Unknown command '" + spiltUserCommand[0] + "'. Please use a valid command.");
        }
    }

}

