package problems.generics.school;

import problems.generics.course.Course;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class School<S, G extends Number> {
    Set<String> courseName;
    Map<String, Course<S, G>> courseToStudentGrade;

    Course<S, G> courseObj = new Course<>();

    public School() {
        this.courseName = new HashSet<>();
        this.courseToStudentGrade = new HashMap<>();
    }

    public School(Set<String> courseName, Map<String, Course<S, G>> courseToStudentGrade) {
        this.courseName = courseName;
        this.courseToStudentGrade = courseToStudentGrade;
    }

    public Map<String, Course<S, G>> add_Course(String name){
        courseToStudentGrade.put(name, null);
        return courseToStudentGrade;
    }

    public Map<String, Course<S, G>> enroll_Student(String name, Course course){
        courseToStudentGrade.put(name, course);
        return courseToStudentGrade;
    }

    public Map<String, Course<S, G>> enrollStudent(String courseName, S student, G grade) {
        Course<S, G> course = courseToStudentGrade.get(courseName);
        if (course != null) {
            course.assignGrade(student, grade);
        } else {
            System.out.println("Course not found.");
        }
        return courseToStudentGrade;
    }

    public void processCommand(String command) {
        String[] array= command.split(" ");
        if("add_course".equals(array[0])){
            courseName.add(array[1]);
            courseToStudentGrade.put(array[1],new Course<>());
            System.out.println("Course '" + array[1] + "' added.");
        }

        if("list_courses".equals(array[0])){
            String[] arrayCourseName = courseName.toArray(new String[courseName.size()]);
            System.out.println("\n" + "Courses offered:");
            for (String s : arrayCourseName) {
                System.out.println(s);
            }
        }

        if("enroll_student".equals(array[0])) {
            String courseName = array[1];
            S student = (S) array[2];
            G grade = null;

            if (!courseToStudentGrade.containsKey(courseName)) {
                System.out.println("Error: Cannot enroll student. Course '" + array[1]+ "' does not exist.");
            } else {
                Course<S,G> course = courseToStudentGrade.get(courseName);
                course.enrollStudent(student);
//                courseToStudentGrade.put(array[1], courseObj);
                System.out.println("Student '" + array[2] + "' enrolled in course '" + array[1] + "'.");
            }
        }

        if("assign_grade".equals(array[0])) {
            String courseName = array[1];
            S student =  (S) array[2];
            Double grade = Double.parseDouble(array[3]);
            Course<S,G> course = courseToStudentGrade.get(courseName);
            if (course!=null && course.isStudentEnrolled(student)) {
                course.assignGrade(student, (G) grade);
              //  courseToStudentGrade.put(array[1], courseObj);
                System.out.println("Grade '" + grade + "' assigned to student '" + array[2] + "' in course '" + array[1] + "'.");
            } else {
                System.out.println("Error: Cannot assign grade. Student '" + array[2] + "' is not enrolled in course '" + array[1] + "'.");
            }
        }

        if("list_grades".equals(array[0])){
            if (courseToStudentGrade.containsKey(array[1])){
                Course <S,G> course = courseToStudentGrade.get(array[1]);
                Map<S,G> stutoGrade = course.getAllGrades();
                for(Map.Entry<S,G> entry:stutoGrade.entrySet() ){
                    S student = entry.getKey();
                    G grade = entry.getValue();
                    System.out.println("Student: " + student + ", Grade: " + grade);
                }
            }
        }

        if("report_unique_courses".equals(array[0])){
            System.out.println("\n" + "Courses offered:");
            for (Map.Entry<String, Course<S, G>> entry : courseToStudentGrade.entrySet()){
                System.out.println(entry.getKey());
            }
        }

        if("report_unique_students".equals(array[0])){
            System.out.println("\n" + "Unique students enrolled:");
            Set<S> uniqueStudent = new HashSet<>();
            for(Course<S,G> course: courseToStudentGrade.values() ){
                uniqueStudent.addAll(course.getAllGrades().keySet());
            }
            System.out.println(uniqueStudent);
        }

        if("report_average_score".equals(array[0])){
            double value = 0.0;
           if(courseToStudentGrade.containsKey(array[1])){
               Course<S,G> course = courseToStudentGrade.get(array[1]);
               Map<S,G> studentToGrade = course.getAllGrades();
               double sum=0;
               for(G grade : studentToGrade.values()){
                   sum+=grade.doubleValue();
               }
               System.out.println("Average score for course '" + array[1] + "': " + sum);
           }
        }

        if("report_cumulative_average".equals(array[0])){
            String stuId = array[1];
            double value = 0.0;
                double sum=0;
                int count=0;
                double cgpa;
                for(Course<S,G> course :courseToStudentGrade.values()) {
                    G grade = course.getStudentGrade((S) stuId);
                    count++;
                    sum= sum+ grade.doubleValue();
                }
                cgpa= sum/count;
                System.out.println("Cumulative average score for student '" + array[1] + "': " + cgpa);
        }

        if("unknown_command".equals(array[0])){
            System.out.println("Error: Unknown command 'unknown_command'. Please use a valid command.");
        }
    }
}