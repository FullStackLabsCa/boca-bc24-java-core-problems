package problems.generics.school;

import java.util.*;


public class School<S, G extends Number> {
    private Map<String, Course<S, G>> schoolMap;
    S studentID;
    G grade;
    String courseID;


    public School() {
        schoolMap = new HashMap<>();
    }

    public void processCommand(String userInput) {
        String[] userStringAfterSplit = userInput.split(" ");

        switch (userStringAfterSplit[0]) {
            case "add_course":
                addCourse(userStringAfterSplit[1]);
                break;
            case "list_courses":
                listCourses();
                break;
            case "enroll_student":
                enrollStudents(userStringAfterSplit[1], (S) userStringAfterSplit[2]);
                break;
            case "assign_grade":
                assignGrade(userStringAfterSplit[1], (S) userStringAfterSplit[2], (G) Double.valueOf(userStringAfterSplit[3]));
                break;
            case "list_grades":
                listGrades(userStringAfterSplit[1]);
                break;

            case "report_unique_courses":
                reportUniqueCourses();
                break;

            case "report_unique_students":
                reportUniqueStudents();
                break;

            case "report_average_score":
                reportAverageScore(userStringAfterSplit[1]);
                break;

            case "report_cumulative_average":
                reportCumulativeAverage((S) userStringAfterSplit[1]);
                break;

            default:
                System.out.println("Error: Unknown command 'unknown_command'. Please use a valid command.");


        }

    }

    public void addCourse(String courseID) {
        schoolMap.put(courseID, new Course<>());
        System.out.println("Course '" + courseID + "' added.");
    }

    public void enrollStudents(String courseID, S studentID) {
        if (schoolMap.containsKey(courseID)) {
            schoolMap.get(courseID).enrollStudent(studentID);
            System.out.println("Student '" + studentID + "' enrolled in course '" + courseID + "'.");
        } else
            System.out.println("Error: Cannot enroll student. Course '" + courseID + "' does not exist.");

    }

    public boolean isStudentEnrolled(String courseID) {
        if (schoolMap.containsKey(courseID)) {
            return true;
        } else {
            return false;
        }
    }

    public void assignGrade(String courseID, S studentID, G grade) {
        if (courseID == null && isStudentEnrolled(courseID)) {
            System.out.println("Error: Cannot enroll student. Course '" + courseID + "' does not exist.");
        }
        if (studentID == null || !schoolMap.get(courseID).isStudentEnrolled(studentID)) {
            System.out.println("Error: Cannot assign grade. Student '" + studentID + "' is not enrolled in course '" + courseID + "'.");
        } else {
            schoolMap.get(courseID).assignGrade(studentID, grade);
            System.out.println("Grade '" + grade + "' assigned to student '" + studentID + "' in course '" + courseID + "'.");
        }

    }

    public void listGrades(String courseID) {
        schoolMap.get(courseID).listAllGrades();
    }

    public void listCourses() {
        schoolMap.keySet();
        System.out.println("Courses offered:" + schoolMap.keySet());
    }

    public void reportUniqueCourses() {
        schoolMap.keySet();
        System.out.println("Courses offered:" + schoolMap.keySet());
    }

    public void reportUniqueStudents() {
        Set<S> studentSet = new TreeSet<>();

        for (Course<S, G> courses : schoolMap.values()) {
            studentSet.addAll(courses.getStudent(studentID));
        }
        System.out.println("Unique students enrolled:" + studentSet);
    }

    public void reportAverageScore(String courseID) {
        Course<S, G> course = schoolMap.get(courseID);
        Collection<G> gradeValues = course.getAllGrades().values();
        double count = 0;
        double averageScoreOfCourse = 0;
        for (G grade : gradeValues) {
            count += grade.doubleValue();
            averageScoreOfCourse = count / gradeValues.size();
            System.out.println("Average score for course '" + courseID + "': " + averageScoreOfCourse);
        }

    }

    public void reportCumulativeAverage(S studentID) {
        double add = 0;
        int count = 0;
        double average;
        for (Course<S, G> collection : schoolMap.values()) {
            G grade = collection.getGrade(studentID);
            if (grade != null) {
                add += grade.doubleValue();
                count++;
            }

        }
        average = add / count;
        System.out.println("Cumulative average score for student '" + studentID + "': " + average);
    }

    public static void main(String[] args) {

    }
}



