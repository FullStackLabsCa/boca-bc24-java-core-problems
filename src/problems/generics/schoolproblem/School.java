package problems.generics.schoolproblem;

import problems.generics.course.Course;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class School<S, G extends Number> {
    private Map<String, Map<S, G>> courses;
    private Set<S> uniqueStudents;

    public School() {
        this.courses = new HashMap<>();
        this.uniqueStudents = new HashSet<>();
    }

    String inputCommand;
    public void processCommand(String inputCommand) {
    String[] splitCommand = inputCommand.split(" ");

    switch(splitCommand[0]) {
        case "add_course":
            if (splitCommand.length == 2) {
                addCourse(splitCommand[1]);
            } else {
                System.out.println("Error: Missing course name.");
            }
            break;

        case "enroll_student":

            if (splitCommand.length == 3) {
                enrollStudent(splitCommand[1], (S) splitCommand[2]);
            } else {
                System.out.println("Error: Missing course name or student ID.");
            }
            break;

        case "assign_grade":
            if (splitCommand.length == 4) {
                G grade = (G) Double.valueOf(splitCommand[3]);
                assignGrade(splitCommand[1], (S) splitCommand[2], grade);
            } else {
                System.out.println("Error: Cannot assign grade. Student '54321' is not enrolled in course 'Math101'.");
            }
            break;

        case "list_courses":
            listCourses();

        case "list_grades":
            if (splitCommand.length == 2) {
                listGrades(splitCommand[1]);
            } else {
                System.out.println("Error: Missing course name.");
            }
            break;

        case "report_unique_courses":
            reportUniqueCourses();
            break;
        case "report_unique_students":
            reportUniqueStudents();
            break;
        case "report_average_score":
            if (splitCommand.length == 2) {
                reportAverageScore(splitCommand[1]);
            } else {
                System.out.println("Error: Missing course name.");
            }
            break;
        case "report_cumulative_average":
            if (splitCommand.length == 2) {
                reportCumulativeAverage((S) splitCommand[1]);
            } else {
                System.out.println("Error: Missing student ID.");
            }
            break;

        default:
            System.out.println("Error: Unknown command 'unknown_command'. Please use a valid command.");
            break;
    }

    }
    public void addCourse(String courseName) {
        if (!courses.containsKey(courseName)) {
            courses.put(courseName, new HashMap<>());
            System.out.println("Course '" + courseName + "' added.");
        } else {
            System.out.println("Error: Course " + courseName + " already exists.");
        }
    }

    public void enrollStudent(String courseName, S studentId) {
        if (courses.containsKey(courseName)) {
            Map<S, G> studentsInCourse = courses.get(courseName);
            if (!studentsInCourse.containsKey(studentId)) {
                studentsInCourse.put(studentId, null);
                uniqueStudents.add(studentId);
                System.out.println("Student '" + studentId + "' enrolled in course '" + courseName + "'.");
            } else {
                System.out.println("Error: Student '" + studentId + "' is already enrolled in course '" + courseName + "'.");
            }
        } else {
            System.out.println("Error: Cannot enroll student. Course '" + courseName + "' does not exist.");
        }
    }

    public void assignGrade(String courseName, S studentId, G grade) {
        if (courses.containsKey(courseName)) {
            Map<S, G> studentsInCourse = courses.get(courseName);
            if (studentsInCourse.containsKey(studentId)) {
                studentsInCourse.put(studentId, grade);
                System.out.println("Grade '" + grade + "' assigned to student '" + studentId + "' in course '" + courseName + "'.");
            } else {
                System.out.println("Error: Cannot assign grade. Student '" + studentId + "' is not enrolled in course '" + courseName + "'.");
            }
        } else {
            System.out.println("Error: Course '" + courseName + "' does not exist.");
        }
    }
    public void listGrades(String courseName) {
        if (courses.containsKey(courseName)) {
            Map<S, G> studentsInCourse = courses.get(courseName);
            if (studentsInCourse.isEmpty()) {
                System.out.println("No students enrolled in course '" + courseName + "'.");
            } else {
                for (Map.Entry<S, G> entry : studentsInCourse.entrySet()) {
                    System.out.println("Student: " + entry.getKey() + ", Grade: " + (entry.getValue() == null ? "Not Assigned" : entry.getValue()));
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
            for (String courseName : courses.keySet()) {
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
            System.out.println("Unique students enrolled:");
            for (S studentId : uniqueStudents) {
                System.out.println("- " + studentId);
            }
        }
    }

    public void reportAverageScore(String courseName) {
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
                    System.out.println("Average score for course '" + courseName + "': " + average);
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
