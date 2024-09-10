package genricproblem;
import java.util.*;

//import static org.junit.Assert.assertTrue;

public class School<S, G extends Number> {

    Map<String, Course<S, G>> courseHashMap = new HashMap<>();// creating a hashMap for student and grade in course class

    public School() {
    }

    public void addCourse(String courseName) {
        courseHashMap.put(courseName, new Course<>());
        System.out.println("Course '" + courseName + "' added.");
    }

    public boolean isCourseAdded(String courseName) {
        return courseHashMap.containsKey(courseName);
    }

    public void enrollStudent(String courseName, S studentId) {// enrolling studentid with coursename only
        if ((isCourseAdded(courseName))) {
            courseHashMap.get(courseName).enrollStudent(studentId);//Student '12345' enrolled in course 'Math101'.
            System.out.println("Student '" + studentId + "' enrolled in course '" + courseName + "'.");
        } else {
            System.out.println("Error: Cannot enroll student. Course '" + courseName + "' does not exist.");
        }
    }

    public void assignGrade(String courseName, S studentid, G grade) {
        if ((courseName == null) && (isCourseAdded(courseName))) {
            System.out.println("Error: Cannot enroll student. Course '" + courseName + "' does not exist.");
        }
        if ((studentid == null) && !courseHashMap.get(studentid).isStudentEnrolled(studentid)) {
            System.out.println("Error: Cannot assign grade. Student '" + studentid + "' is not enrolled in course '" + courseName + "'.");
        }
        courseHashMap.get(courseName).assignGrade(studentid, grade);
        System.out.println("Grade '" + grade + "' assigned to student '" + studentid + "' in course '" + courseName + "'.");
    }

    public void listAllGrades(String courseName) {
        if (courseName == null) {
            System.out.println("Course name cannot be null.");
        } else
            courseHashMap.get(courseName).listAllGrades();
    }

    public void listCourses() {

        List<String> uniqueCourseList = new ArrayList<>();
        Set<Map.Entry<String, Course<S, G>>> entries = courseHashMap.entrySet();
        for (Map.Entry<String, Course<S, G>> s : entries) {
            uniqueCourseList.add(s.getKey());
        }
        System.out.println("Courses offered: " + uniqueCourseList);
    }

    public void uniqueStudents() {
        Set<S> uniquestudentset = new TreeSet<>();
        for (Course<S, G> course1 : courseHashMap.values()) {
            uniquestudentset.addAll(course1.getStudents());
        }
        System.out.println("Unique studentsc enrolled:\n" + uniquestudentset);
    }

    public void uniqueCourses() {
        List<String> uniquecources = new ArrayList<>();
        for (Map.Entry<String, Course<S, G>> courseList : courseHashMap.entrySet()) {
            uniquecources.add(courseList.getKey());
            System.out.println("Courses offered:\n" + courseList);
        }
    }

    public void averageScore(String courseName) {
        Course<S, G> course = courseHashMap.get(courseName);
        Collection<G> values = course.getAllGrades().values();
        double sum = 0;
        double averagegrade = 0;
        for (G grades : values) {
            sum += grades.doubleValue();
            averagegrade = sum / values.size();
            System.out.println("Average score for course '" + courseName + "': " + averagegrade);
        }
    }
    public void cumulativeAverage(S student) {
        double sum = 0;
        int count = 0;
        Collection<Course<S, G>> courseCollection = courseHashMap.values();
        for (Course<S, G> course : courseCollection) {
            Collection<S> studentCollection = course.getStudents();

            if (studentCollection.contains(student)) {
                count++;
                if (course.getGrade(student).isPresent()) {
                    sum += course.getGrade(student).get().doubleValue();
                }
            }
        }
        double average = sum / count;
        System.out.println("Cumulative average score for student '" + student + "': " + average);
    }

    public void processCommand(String input) {

        String[] parts = input.trim().split(" ");
        String action = parts[0];
        switch (action) {
            case "add_course":
                addCourse(parts[1]);
                break;
            case "enroll_student":
                enrollStudent(parts[1], (S) parts[2]);
                break;
            case "assign_grade":
                assignGrade(parts[1], (S) parts[2], (G) Double.valueOf(parts[3]));
                break;
            case "list_grades":
                listAllGrades(parts[1]);

                break;
            case "list_courses":
                listCourses();
                break;
            case "report_unique_courses":
                uniqueCourses();
                break;
            case "report_unique_students":
                uniqueStudents();
                break;
            case "report_average_score":
                averageScore(parts[1]);
                break;
            case "report_cumulative_average":
                cumulativeAverage((S) parts[1]);

                break;
            case "unknown_command":
                System.out.println("Error: Unknown command 'unknown_command'. Please use a valid command.");
                break;
            default:
                System.out.println("Unknown command. Please use one of the following commands: add_course, enroll_student, assign_grade, list_grades, list_courses, report_unique_courses, report_unique_students, report_average_score, report_cumulative_average.");
        }
    }

    public static void main(String[] args) {

        School<Integer, Integer> schoolEntry = new School<>();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("Choose one option :");
                System.out.println("add_course ");
                System.out.println("enroll_student");
                System.out.println("assign_grade");
                System.out.println("list_grades");
                System.out.println("list_courses");
                System.out.println("report_unique_courses");
                System.out.println("report_unique_students");
                System.out.println("report_average_score");
                System.out.println("report_cumulative_average");
                String input = scanner.nextLine();
                schoolEntry.processCommand(input);
            } catch (Exception e) {
                System.out.println("Error: Unknown command 'unknown_command'. Please use one of the following commands: add_course, enroll_student, assign_grade, list_grades, list_courses, report_unique_courses, report_unique_students, report_average_score, report_cumulative_average.");
            }
        }
    }
}
