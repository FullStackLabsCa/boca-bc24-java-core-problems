package generics.school;

import java.util.*;

import static org.junit.Assert.assertTrue;

public class School<S, G extends Number> {
    HashMap<String, Course<S, G>> school;

    public School() {
        this.school = new HashMap<>();
    }

    public void addCourse(String course) {
        school.put(course, new Course<>());
        System.out.println("Course '" + course + "' added.");
    }

    public void listCourses() {
        System.out.println("Courses offered:");
        for (Map.Entry<String, Course<S, G>> current : school.entrySet()) {
            System.out.println(current.getKey());
        }
    }

    public void enrollStudent(String courseName, S studentId) {
        if (school.containsKey(courseName)) {
            school.get(courseName).addStudent(studentId);
            System.out.println("Student '" + studentId +"' enrolled in course '"+courseName+"'.");
        }else{
            System.out.println("Error: Cannot enroll student. Course 'Physics103' does not exist.\"");
        }
    }

    public void assignGrade(String courseName, S studentId, G grade) {
       if(school.get(courseName).containstStudent(studentId)){
           school.get(courseName).addGrade(studentId, grade);
           System.out.println("Grade '"+grade+"' assigned to student '"+studentId+"' in course '"+courseName+"'.");
       }else{
           System.out.println("Error: Cannot assign grade. Student '"+studentId+"' is not enrolled in course '"+courseName+"'.");

       }
          }

    public void listAllStudents() {
        System.out.println("Unique students enrolled:");
        for (Map.Entry<String, Course<S, G>> current : school.entrySet()) {
            Map<S, G> student = current.getValue().students;
            for (Map.Entry<S, G> stu : student.entrySet()) {
                System.out.println(stu.getKey());
            }
        }
    }

    public void reportCourseAverage(String courseName) {
        double average = school.get(courseName).getAverageGrade();
        System.out.println("Average score for course '"+courseName+"': "+average);
    }

    public void reportCumulativeScore(S studentId) {
        double average = 0;
        double count = 0;

        for (Map.Entry<String, Course<S, G>> current : school.entrySet()) {
            Map<S, G> student = current.getValue().students;
            for (Map.Entry<S, G> stu : student.entrySet()) {
                if (stu.getKey().equals(studentId)) {
                    System.out.println(stu.getValue());
                    average += stu.getValue().doubleValue();
                    count++;
                }
            }
        }
        System.out.println(count);
        System.out.println("Cumulative average score for student '"+studentId+"': "+(average / count));
    }

    public void listGradesForCourse(String courseName){
        school.get(courseName).getAllStudents();
    }
    public void processCommand(String inputCommand) {
        String[] input = inputCommand.split(" ");
        String command= input[0];

        switch (command){
            case "add_course":
                addCourse(input[1]);
                break;
                case "list_courses":
                    listCourses();
                break;
                case "enroll_student":
                    enrollStudent(input[1], (S) input[2]);
                break;
                case "assign_grade":
                    assignGrade(input[1], (S)input[2], (G)Double.valueOf(input[3]));
                break;
                case "list_grades":
                    listGradesForCourse(input[1]);
                break;
                case "report_unique_courses":
                    listCourses();
                break;
                case "report_unique_students":
                    listAllStudents();
                break;
                case "report_average_score":
                    reportCourseAverage(input[1]);
                break;
                case "report_cumulative_average":
                    reportCumulativeScore((S) input[1]);
                break;
            default:
                System.out.println("Error: Unknown command 'unknown_command'. Please use a valid command.");

        }
    }
}





