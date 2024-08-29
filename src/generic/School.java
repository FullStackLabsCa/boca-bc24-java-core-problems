package generic;

import java.util.*;

public class School<S, G extends Number> {

    Map<String, Course<S, G>> courseToStudentGradeMap;

    public School() {
        this.courseToStudentGradeMap = new HashMap<>();
    }

    public void addCourse(String course) {
        courseToStudentGradeMap.put(course, new Course<>());
        System.out.println("Course '" + course + "' added.");
    }

    public void removeCourse(String course) {
        courseToStudentGradeMap.remove(course);
    }

    public void listCourse() {
        Set<String> courseList = courseToStudentGradeMap.keySet();
        System.out.println("Courses offered:");
        for (String c : courseList) {
            System.out.println(c);
        }
    }

    public boolean isCourseAdded(String course) {
        return courseToStudentGradeMap.containsKey(course);
    }

    public void enrollStudents(String course, S s) {
        if (isCourseAdded(course)) {
            Course<S, G> courseOne = courseToStudentGradeMap.get(course);
            courseOne.enrollStudents(s);
            System.out.println("Student '" + s + "' enrolled in course '" + course + "'.");
        } else {
            System.out.println("Error: Cannot enroll student. Course '" + course + "' does not exist.");
        }
    }

    public void assignGrade(String course, S s, G g) {
        Course<S, G> courseOne = courseToStudentGradeMap.get(course);
        if (courseOne.isStudentEnrolled(s) == true) {
            courseOne.assignGrades(s, g);
            System.out.println("Grade '" + g + "' assigned to student '" + s + "' in course '" + course + "'.");
        } else {
            System.out.println("Error: Cannot assign grade. Student '" + s + "' is not enrolled in course '" + course + "'.");
        }
    }

    public void listGrades(String course) {
        List<G> gradeList = new ArrayList<>();
        Course<S, G> courseOne = courseToStudentGradeMap.get(course);
        courseOne.listGrades();
    }

    public void listUniqueCourses() {

        Set<String> courseList = courseToStudentGradeMap.keySet();
        System.out.println("Courses offered:");
        System.out.println(courseList);
        for (String c : courseList) {
            System.out.println(c);
        }
    }

    public Set<S> listUniqueStudents() {
        Set<String> courseSet = courseToStudentGradeMap.keySet();
        Set<S> uniqueStudents = new HashSet<>();
        for (String c : courseSet) {
            uniqueStudents.addAll(courseToStudentGradeMap.get(c).listStudents());
        }
        System.out.println("Unique students enrolled:");
        for (S s : uniqueStudents) {
            System.out.println(s);
        }
        return uniqueStudents;
    }

    public void reportAverageScore(String course) {
        Course<S, G> courseOne = courseToStudentGradeMap.get(course);
        System.out.println("Average score for course '" + course + "': " + courseOne.averageScore());
    }

    public double reportCumulativeAverage(S s) {
        Set<String> courseSet = courseToStudentGradeMap.keySet();
        double sum = 0;
        int count = 0;
        for (String c : courseSet) {
            Course<S, G> courseOne = courseToStudentGradeMap.get(c);
            Set<S> students = courseOne.listStudents();
            for (S student : students) {
                if (student.equals(s)) {
                    sum += courseOne.getGrade(s).doubleValue();
                    count++;
                }
            }
        }
        double average = sum / count;

        System.out.println("Cumulative average score for student '" + s + "': " + average);
        return average;
    }

    public void processCommand(String s) {

        String[] operands = s.split(" ");

        switch (operands[0]) {
            case "add_course" -> {
                addCourse(operands[1]);
            }
            case "list_courses" -> {
                listCourse();
            }
            case "enroll_student" -> {
                enrollStudents(operands[1], (S) operands[2]);
            }
            case "assign_grade" -> {
                assignGrade(operands[1], (S) operands[2], (G) Double.valueOf(operands[3]));
            }
            case "list_grades" -> {
                listGrades(operands[1]);
            }
            case "report_unique_courses" -> {
                listUniqueCourses();
            }
            case "report_unique_students" -> {
                listUniqueStudents();
            }
            case "report_average_score" -> {
                reportAverageScore(operands[1]);
            }
            case "report_cumulative_average" -> {
                reportCumulativeAverage((S) operands[1]);
            }
            default -> {
                System.out.println("Error: Unknown command 'unknown_command'. Please use a valid command.");
            }
        }
    }

    public static void main(String[] args) {
        School<Integer, Integer> school = new School<>();
        school.processCommand("add_course Math101");
        school.processCommand("add_course Physics102");
        school.processCommand("enroll_student Math101 12345");
        school.processCommand("enroll_student Physics102 12345");
        school.processCommand("assign_grade Math101 12345 85.0");
        school.processCommand("assign_grade Physics102 12345 90.0");
        school.processCommand("report_cumulative_average 12345");
    }
}
