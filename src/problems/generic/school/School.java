package problems.generic.school;//package problems.generic.school;
//
///*
//school-problem-generics
//
//this includes error handling with string returns
//includes collection data from the command line which you know you can do with System.in
//rest all work should be done based on the knowledge you have gained so far on generics, collections, and OOPS methodologies.
//keep on eye on the object model while making the class and object names.
//make sure you follow the correct naming conventions to the "T" for the entire codebase. java-naming-conventions
//*/

import java.util.*;

public class School<S, G extends Number> {

    public Map<S, Course<S, G>> studentWithCourse;

    public School() {
         studentWithCourse = new HashMap<>();
    }

    public Course<S, G> addCourse(S courseName) {
        System.out.println("Course '" + courseName + "' added.");
        return studentWithCourse.put(courseName, new Course<>());
    }

    public void enrollStudent(S courseName, S studentId) {
        if (!studentWithCourse.containsKey(courseName)) {
            System.out.println("Error: Cannot enroll student. Course '" + courseName + "' does not exist.");
            return;
        }
        studentWithCourse.get(courseName).enrollStudent(studentId);
        System.out.println("Student '" +  studentId + "' enrolled in course '" + courseName + "'.");
    }

    public Course<S, G> assignGrade(S courseName, S studentId, G grade) {
        Collection<Course<S, G>> values = studentWithCourse.values();
        values.forEach((k) -> {
            String s = k.toString().split("=")[0];
            String checkElement = s.substring(1);
            if (!checkElement.equals(studentId)) {
                System.out.println("Error: Cannot assign grade. Student '" + studentId + "' is not enrolled in course '" + courseName + "'.");
                return;
            }
        });
        if (studentWithCourse.containsKey(courseName)) {
            studentWithCourse.get(courseName).assignGrade(studentId, grade);
        }
        System.out.println("Grade '" + grade + "' assigned to student '" + studentId + "' in course '" + courseName + "'.");
        return this.studentWithCourse.get(courseName);
    }

    public void listGrades(S courseName) {
        Course<S, G> sgCourse = studentWithCourse.get(courseName);
        sgCourse.listStudentWithGrade();
    }

    public void listAllCourses() {
        Set<S> s = studentWithCourse.keySet();
        System.out.println("Courses offered:");
        s.forEach(System.out::println);
    }

    public void listUniqueStudents() {
        System.out.println("Unique students enrolled:");
        Collection<Course<S, G>> values = studentWithCourse.values();
        values.forEach((k) -> {
            String s = k.toString().split("=")[0];
            String substring = s.substring(1);
            System.out.println(substring);
        });
    }

    public double reportAverageScore(S courseName) {
        double sum1 = 0;
        Course<S, G> sgCourse = studentWithCourse.get(courseName);
        List<G> grades = sgCourse.listAllGrades();
        for (G grade : grades) {
            sum1 = Double.sum(sum1, (Double) grade);
        }
        Double average = Double.valueOf(sum1 / (double) grades.size());
        System.out.println("Average score for course '" + courseName + "': " + average);
        return average;
    }

    public double reportCumulativeAverage(S studentId) {
        Collection<Course<S, G>> values = studentWithCourse.values();
        double sum = 0.0;
        double average = 0.0;
        for (Course<S, G> k : values) {
            String s = k.toString().split("=")[0];
            String checkElement = s.substring(1);
            String original = k.toString().split("=")[1];
            double grade = Double.parseDouble(original.substring(0, original.length() - 1));
            if (checkElement.equals(studentId)) {
                sum = Double.sum(sum, grade);
            }
            average = sum / Double.valueOf(values.size());
        }

        System.out.println("Cumulative average score for student '" + studentId + "': " + average);
        return average;
    }

    public static void main(String[] args) {
        School<String, Double> school = new School<>();

        System.out.println("Enter the listed option" + "\n" +
                "add_course" + "\n" +
                "enroll_student" + "\n" +
                "assign_grade" + "\n" +
                "list_grades" + "\n" +
                "list_courses" + "\n" +
                "report_unique_courses" + "\n" +
                "report_unique_students" + "\n" +
                "report_average_score" + "\n" +
                "report_cumulative_average"

        );
        Scanner scanner = new Scanner(System.in);
        String inputCommand = scanner.nextLine();
        school.processCommand(inputCommand);

    }

    public void processCommand(String inputCommand) {
        String[] splittedInput = {};
        if (inputCommand.contains(" ")) {
            splittedInput = inputCommand.split(" ");
        }

        switch (inputCommand.contains(" ") ? splittedInput[0] : inputCommand) {
            case "add_course":
                System.out.println();
                addCourse((S) splittedInput[1]);
                break;
            case "enroll_student":
                enrollStudent((S) splittedInput[1],(S) splittedInput[2]);
                break;
            case "assign_grade":
                assignGrade((S) splittedInput[1], (S) splittedInput[2], (G) Double.valueOf(splittedInput[3]));
                break;
            case "list_grades":
                listGrades((S) splittedInput[1]);
                break;
            case "list_courses":
                listAllCourses();
                break;
            case "report_unique_courses":
                listAllCourses();
                break;
            case "report_unique_students":
                listUniqueStudents();
                break;
            case "report_average_score":
                reportAverageScore((S) splittedInput[1]);
                break;
            case "report_cumulative_average":
                reportCumulativeAverage((S) splittedInput[1]);
            default:
                System.out.println("Error: Unknown command 'unknown_command'. Please use a valid command.");
                break;

        }
    }
}


