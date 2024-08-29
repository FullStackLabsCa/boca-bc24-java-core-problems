package problems.generics;

import java.util.*;


public class School<S, G extends Number> {

    private static final String ADD_COURSE = "add_course";
    private static final String ENROLL_STUDENT = "enroll_student";
    private static final String ASSIGN_GRADE = "assign_grade";
    private static final String LIST_GRADES = "list_grades";
    private static final String LIST_COURSES = "list_courses";
    private static final String REPORT_UNIQUE_COURSES = "report_unique_courses";
    private static final String REPORT_UNIQUE_STUDENTS = "report_unique_students";
    private static final String REPORT_AVERAGE_SCORE = "report_average_score";
    private static final String REPORT_CUMULATIVE_AVERAGE = "report_cumulative_average";
    private static final String EXIT = "exit";

    private final List<String> LIST_OF_COMMANDS = Arrays.asList(ADD_COURSE, ENROLL_STUDENT, ASSIGN_GRADE, LIST_GRADES, LIST_COURSES,
            REPORT_UNIQUE_COURSES, REPORT_UNIQUE_STUDENTS, REPORT_AVERAGE_SCORE, REPORT_CUMULATIVE_AVERAGE, EXIT);

    Map<String, Course<S, G>> schoolToCourseMap;


    public School() {
        schoolToCourseMap = new HashMap<>();
    }

    public void addCourse(String courseName) {
        this.schoolToCourseMap.put(courseName, new Course<>());
        System.out.println("Course '" + courseName + "' added.");
    }


    public void enrollStudent(String courseName, S studentId) {
        if (schoolToCourseMap.containsKey(courseName)) {
            schoolToCourseMap.get(courseName).enrollStudent(studentId);
            System.out.println("Student '" + studentId + "' enrolled in course '" + courseName + "'.");
        } else {
            System.out.println("Error: Cannot enroll student. Course '" + courseName + "' does not exist.");
        }
    }

    public void assignGrade(String courseName, S student_id, G grade) {
        if (schoolToCourseMap.containsKey(courseName)) {
            Course<S, G> course = schoolToCourseMap.get(courseName);
            if (course.getAllGrades().containsKey(student_id)) {
                course.assignGrade(student_id, grade);
                System.out.println("Grade '" + grade + "' assigned to student '" + student_id + "' in course '" + courseName + "'.");
            } else {
                System.out.println("Error: Cannot assign grade. Student '" + student_id + "' is not enrolled in course '" + courseName + "'.");
            }
        } else {
            System.out.println("Course " + courseName + " doesnot exist.");
        }
    }

    public Map<S, G> listGrades(String courseName) {
        if (schoolToCourseMap.containsKey(courseName)) {
            Course<S, G> course = schoolToCourseMap.get(courseName);
            Map<S, G> grades = course.listAllGrades();

            if (grades.isEmpty()) {
                System.out.println("No grades available for course '" + courseName + "'.");
            } else {
                for (Map.Entry<S, G> entry : grades.entrySet()) {
                    System.out.println("Student: " + entry.getKey() + ", Grade: " + entry.getValue());
                }
            }
            return grades;
        } else {
            System.out.println("Course " + courseName + " does not exist.");
            return Collections.emptyMap();
        }
    }

    public void listCourses() {
        System.out.println("Courses offered:");
        Set<String> courseNames = schoolToCourseMap.keySet();
        for (String course : courseNames) {
            System.out.println(course);
        }
    }

    @Override
    public String toString() {
        return "School{" +
                "schoolToCourseMap=" + schoolToCourseMap +
                '}';
    }


    public void processCommand(String commandString) {
        String[] command = commandString.split(" ");
//        System.out.println(">>>>>>>>> C O M M A N D <<<<<<<<<<<"+commandString);
        if (LIST_OF_COMMANDS.contains(command[0])) {
            switch (command[0]) {
                case ADD_COURSE:
                    this.addCourse(command[1]);
                    break;
                case ENROLL_STUDENT:
                    this.enrollStudent(command[1], (S) command[2]);
                    break;
                case ASSIGN_GRADE:
                    this.assignGrade(command[1], (S) command[2], (G) Double.valueOf(command[3]));
                    break;
                case LIST_GRADES:
                    this.listGrades(command[1]);
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
                    this.reportAverageScore(command[1]);
                    break;
                case REPORT_CUMULATIVE_AVERAGE:
                    this.reportCumulativeAverage((S) command[1]);
                    break;
            }
        } else
            System.out.println("Error: Unknown command 'unknown_command'. Please use a valid command.");
    }

    public void reportCumulativeAverage(S studentId) {
        Collection<Course<S, G>> courses = this.schoolToCourseMap.values();
        double total = 0.0;
        int count = 0;

        for (Course<S, G> course : courses) {
            Optional<G> optionalGrade = course.getGrade(studentId);
            if (optionalGrade.isPresent()) {
                total += optionalGrade.get().doubleValue();
                count++;
            }
        }
        if (count != 0) {
            double average = total / count;
            System.out.println("Cumulative average score for student '" + studentId + "': " + String.format("%.1f", average));
        }
    }

    public void reportAverageScore(String courseName) {

        double average = schoolToCourseMap.get(courseName).averageOfGrades();
        System.out.println("Average score for course '" + courseName + "': " + average);

    }

    public void reportUniqueStudents() {
        Collection<Course<S, G>> students = schoolToCourseMap.values();
        Set<S> studentIdentifiers = new HashSet<>();
        for (Course<S, G> course : students) {
            studentIdentifiers.addAll(course.getAllGrades().keySet());
        }
        System.out.println("Unique students enrolled:");
        for (S studentIdentifier : studentIdentifiers) {
            System.out.println(studentIdentifier);
        }
    }

    public void reportUniqueCourses() {

        Set<String> courses = this.schoolToCourseMap.keySet();
        System.out.println("Courses offered:");
        for (String course : courses) {
            System.out.println(course);
        }
    }

    public List<String> getLIST_OF_COMMANDS() {
        return LIST_OF_COMMANDS;
    }
}
