package io.reacticestax.genericsproblems.schoolGenerics;

import java.util.*;

public class School<S, G extends Number> {

    Map<String, Course<S, G>> courseIdToCourseMap = new HashMap<>();

    private final String ADD_COURSE = "add_course";
    private final String ENROLL_STUDENT = "enroll_student";
    private final String ASSIGN_GRADE = "assign_grade";
    private final String LIST_GRADES = "list_grades";
    private final String LIST_COURSES = "list_courses";
    private final String REPORT_UNIQUE_COURSES = "report_unique_courses";
    private final String REPORT_UNIQUE_STUDENTS = "report_unique_students";
    private final String REPORT_AVERAGE_SCORE = "report_average_score";
    private final String REPORT_CUMULATIVE_AVERAGE = "report_cumulative_average";

    private final List<String> LIST_OF_COMMANDS = Arrays.asList(ADD_COURSE, ENROLL_STUDENT, ASSIGN_GRADE, LIST_GRADES, LIST_COURSES,
            REPORT_UNIQUE_COURSES, REPORT_UNIQUE_STUDENTS, REPORT_AVERAGE_SCORE, REPORT_CUMULATIVE_AVERAGE);

    public void addCourse(String courseIdentifier) {
        courseIdToCourseMap.put(courseIdentifier, new Course<>());
        System.out.println("Course '" + courseIdentifier + "' added.");
    }

    public void enrollStudent(String courseIdentifier, S student) {
        if (this.courseIdToCourseMap.containsKey(courseIdentifier)) {
            Course<S, G> studentToGrade = this.courseIdToCourseMap.get(courseIdentifier);
            studentToGrade.enrollStudent(student);
            System.out.println("Student '" + student + "' enrolled in course '" + courseIdentifier + "'.");
        } else {
            System.out.println("Error: Cannot enroll student. Course '" + courseIdentifier + "' does not exist.");

        }
    }

    public void assignGrade(String courseIdentifier, S student, G grade) {
        Course<S, G> studentToGrade = this.courseIdToCourseMap.get(courseIdentifier);
        if (studentToGrade.getStudentToGrade().containsKey(student)) {
            studentToGrade.assignGrade(student, grade);
            System.out.println("Grade '" + grade + "' assigned to student '" + student + "' in course '" + courseIdentifier+ "'.");
        } else {
            System.out.println("Error: Cannot assign grade. Student '" + student + "' is not enrolled in course '" + courseIdentifier + "'.");
        }
    }


    public void listGrades(String courseIdentifier) {
        Course<S, G> course = this.courseIdToCourseMap.get(courseIdentifier);
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        Map<S, G> studentToGrade = course.getStudentToGrade();
        if (studentToGrade.isEmpty()) {
            System.out.println("No grades available.");
        } else {
            for (Map.Entry<S, G> entry : studentToGrade.entrySet()) {
                System.out.println("Student: " + entry.getKey() + ", Grade: " + entry.getValue());
            }
        }
    }


    public void listCourses() {
        if (this.courseIdToCourseMap.isEmpty()) {
            System.out.println("No courses available.");
        } else {
            System.out.println("Courses offered:");
            for (String courseIdentifier : this.courseIdToCourseMap.keySet()) {
                System.out.println(courseIdentifier);
            }
        }
    }




    public void reportUniqueCourses() {
        Set<String> courses = this.courseIdToCourseMap.keySet();
        System.out.println("Courses offered:");
        for (String course : courses) {
            System.out.println(course);
        }
    }

    public void reportUniqueStudents() {
        Collection<Course<S, G>> students = this.courseIdToCourseMap.values();
        Set<S> uniqueStudentSet = new HashSet<>();
        for (Course<S, G> course : students) {
            uniqueStudentSet.addAll(course.getStudentToGrade().keySet());
        }
        System.out.println("Unique students enrolled:");
        for (S student : uniqueStudentSet) {
            System.out.println(student);
        }
    }

    public void reportAverageScore(String courseIdentifier) {
        Course<S, G> course = courseIdToCourseMap.get(courseIdentifier);
        System.out.println("Average score for course '" + courseIdentifier + "': " + course.averageGrade());
    }

    public void reportCumulativeAverage(S studentName) {
        Collection<Course<S, G>> students = this.courseIdToCourseMap.values();
        double total = 0.0;
        int count = 0;
        for (Course<S, G> student : students) {
            if (student.getStudentToGrade().containsKey(studentName)) {
                G grade = student.getStudentToGrade().get(studentName);
                total += grade.doubleValue();
                count++;
            }
        }
        System.out.println("Cumulative average score for student '" + studentName + "': " + String.format("%.1f", total / count));
    }

    public List<String> getLIST_OF_COMMANDS() {
        return LIST_OF_COMMANDS;
    }


    public void useCommands() {
        System.out.println("Available commands:");
        for (String command : LIST_OF_COMMANDS) {
            System.out.println(command);
        }
    }

    public void processCommand(String command) {
        String[] splitInput = command.split(" ");
        if (LIST_OF_COMMANDS.contains(splitInput[0])) {
            switch (splitInput[0]) {
                case ADD_COURSE:
                    this.addCourse(splitInput[1]);
                    break;
                case ENROLL_STUDENT:
                    this.enrollStudent(splitInput[1], (S) splitInput[2]);
                    break;
                case ASSIGN_GRADE:
                    this.assignGrade(splitInput[1], (S) splitInput[2], (G) Double.valueOf(splitInput[3]));
                    break;
                case LIST_GRADES:
                    this.listGrades(splitInput[1]);
                    break;
                case LIST_COURSES:
                    this.listCourses();
                    break;
                case REPORT_UNIQUE_COURSES:
                    this.reportUniqueCourses();
                    break;
                case REPORT_UNIQUE_STUDENTS:
                    this.reportUniqueStudents();
                    break;
                case REPORT_AVERAGE_SCORE:
                    this.reportAverageScore(splitInput[1]);
                    break;
                case REPORT_CUMULATIVE_AVERAGE:
                    this.reportCumulativeAverage((S) splitInput[1]);
                    break;
                default:
                    System.out.println("Error: Unknown command '" + splitInput[0] + "'. Please use a valid command.");
                    break;
            }
        } else {

            System.out.println("Error: Unknown command '" + splitInput[0] + "'. Please use a valid command.");
        }
    }





    public static void main(String[] args) {
        School<Integer, Double> school = new School<>();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to School:");
        school.useCommands();

        while (true) {

            String input = scanner.nextLine();
            school.processCommand(input);
        }
    }
}
