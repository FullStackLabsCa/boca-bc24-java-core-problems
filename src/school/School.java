package school;

import course.Course;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class School <S,G extends Number> extends Course<S,G>{
    private Set<String> courseName;
    private Map<String, Course<S,G>> courseToStudentGrade;
//    Course course = new Course();

    public Set<String> getCourseName() {
        return courseName;
    }
    public School() {
        this.courseName = new HashSet<>();
        this.courseToStudentGrade = new HashMap<>();
    }
    public Map<String, Course<S, G>> getCourseToStudentGrade() {
        return courseToStudentGrade;
    }

    public Map<String, Course<S,G>> add_Course(String name){
       courseToStudentGrade.put(name, null);
       return courseToStudentGrade;
    }
    public Map<String,Course<S,G>> enroll_Student(String name, Course course){
        courseToStudentGrade.put(name,course);
        return courseToStudentGrade;
    }
    public void processCommand(String command) {
//        Map<S, G> studentToGrade = course.getAllGrades();
        String[] parts = null;
        parts = command.split(" ");
        if (command.startsWith("add_course")) {
            if (parts.length == 2) {
                String name = parts[1];
                courseName.add(name);
                courseToStudentGrade.put(name, new Course<>());
                System.out.println("Course '" + name + "' added.");
            } else {
                System.out.println("Invalid command format.");
            }
        } else if (command.startsWith("list_courses") || command.startsWith("report_unique_courses")) {
            System.out.println("Courses offered:");
            for (String course : courseName) {
                System.out.println(course);
            }
        } else if (command.startsWith("enroll_student")) {
            //enroll_student Math101 12345
            if (parts.length == 3) {
                String courseName = parts[1];
                S studentId = (S) parts[2];

                if (courseToStudentGrade.containsKey(courseName)) {
                    Course<S, G> course = courseToStudentGrade.get(courseName);
                    course.enrollStudent(studentId);
                    System.out.println("Student '" + studentId + "' enrolled in course '" + courseName + "'.");
                } else {
                    System.out.println("Error: Cannot enroll student. Course '" + courseName + "' does not exist.");
                }
            } else {
                System.out.println("Invalid command format.");
            }
//            if (courseToStudentGrade.containsKey(parts[1])) {
//                course.enrollStudent(parts[2]);
//                courseToStudentGrade.put(parts[1], course);
//                System.out.println("Student '" + parts[2] + "' enrolled in course '" + parts[1] + "'.");
//            } else {
//                System.out.println("Error: Cannot enroll student. Course '" + parts[1] + "' does not exist.");
//            }
        } else if (command.startsWith("assign_grade")) {
            if (parts.length == 4) {
                String courseName = parts[1];
                S studentId = (S)parts[2];
                G grade = (G) Double.valueOf(parts[3]); // Cast to G (Number)

                Course<S, G> course = courseToStudentGrade.get(courseName);
                if (course != null && course.isStudentEnrolled(studentId)) {
                    course.assignGrade(studentId, grade);
                    System.out.println("Grade '" + grade + "' assigned to student '" + studentId + "' in course '" + courseName + "'.");
                } else {
                    System.out.println("Error: Cannot assign grade. Student '" + studentId + "' is not enrolled in course '" + courseName + "'.");
                }
            }
        }else if (command.startsWith("list_grades")) {
                //list_grades Math101

                if (courseToStudentGrade.containsKey(parts[1])) {
                    Course<S, G> course = courseToStudentGrade.get(parts[1]);
                    Map<S, G> studentToGrade = course.getAllGrades();
                    for (Map.Entry<S, G> entry : studentToGrade.entrySet()) {
                        S key = entry.getKey();
                        G value = entry.getValue();
                        System.out.println("Student: " + key + ", Grade: " + value);
                    }

                }
            } else if (command.startsWith("report_unique_students")) {
            Set<S> uniqueStudents = new HashSet<>();
            for (Course<S, G> course : courseToStudentGrade.values()) {
                uniqueStudents.addAll(course.getAllGrades().keySet());
            }

            System.out.println("Unique students enrolled:");
            for (S student : uniqueStudents) {
                System.out.println(student);
            }
            } else if (command.startsWith("report_average_score")) {
                //report_average_score Math101
                if (courseToStudentGrade.containsKey(parts[1])) {
                    Course<S, G> course = courseToStudentGrade.get(parts[1]);
                    Map<S, G> studentToGrade = course.getAllGrades();
                    double total = 0.0;
                    int count = 0;
                    for (G grade : studentToGrade.values()) {
                        if (grade != null) {
                            total += grade.doubleValue();
                            count++;
                        }
                    }
                    double average = (count > 0) ? (total / count) : 0.0;

                    System.out.println("Average score for course '" + parts[1] + "': " + average);

                } else {
                    System.out.println("Error: Course '" + parts[1] + "' does not exist.");
                }
            } else if (command.startsWith("report_cumulative_average")) {
            String studentId = parts[1];
            double total = 0.0;
            int count = 0;

            // Loop through all courses to find the student's grades
            for (Course<S, G> course : courseToStudentGrade.values()) {
                G grade = course.getStudentGrade((S) studentId);
                if (grade != null) {
                    total += grade.doubleValue();
                    count++;
                }
            }
//        assertTrue(systemOutRule.getLog().contains("Cumulative average score for student '12345': 87.5"));
                double cumulativeAverage = (count > 0) ? (total / count) : 0.0;
                System.out.println("Cumulative average score for student '" + studentId + "': " + cumulativeAverage);
            }
        else {
                System.out.println("Error: Unknown command 'unknown_command'. Please use a valid command.\"");
            }

    }
}
