package problems.generics.school;

import problems.generics.course_book.Course;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SchoolOld<S, G extends Number> {
    //Map<S,G> studentToGradeMap;
    Map<String, Course<S, G>> coursesToGradeMAp = new HashMap<>();
//    String courseId;

    public void addCourse(String course) {
        coursesToGradeMAp.put(course, new Course<>());
    }

    public Set<String> listCourses() {
        Set<String> stringsSet = coursesToGradeMAp.keySet();
        return stringsSet;
    }

    public void enrollStudent(String courseNmae, S studentID) {
        Course<S, G> courseOne = coursesToGradeMAp.get(courseNmae);
        courseOne.enrollStudent(studentID);
    }

    public void assignGrade(String course, S s, G g) {
        Course<S, G> courseOne = coursesToGradeMAp.get(course);
        courseOne.assignGrade(s, g);
    }

    public void listGrade(String course) {
        Course<S, G> courseOne = coursesToGradeMAp.get(course);
        courseOne.listAllGrade();
    }

    public Set<String> reportUniqueCourses() {
        return coursesToGradeMAp.keySet();
    }

    public void reportUniqueStudents(S s) {
        Course<S, G> courseOne = coursesToGradeMAp.get(s);
    }

    public void reportAverageScore(String course, G g) {
        Course<S, G> courseOne = coursesToGradeMAp.get(g);
        System.out.println("Average score for course 'Math101': 85.5");
    }

    //    public void reportCumulativeAverage (S s){
//
//    }
    public void processCommand(String operation) {
        String input = "Nimanshu Patel";
        String[] op = input.split(" ");
        switch (op[0]) {
            case "add_course": {
                addCourse(op[1]);
                break;
            }
            case "list_courses": {
                listCourses();
                break;
            }
            case "enroll_student": {
                enrollStudent(op[1], (S) op[2]);
                break;
            }
            case "assign_grade": {
                assignGrade(op[1], (S) op[2], (G) Double.valueOf(op[3]));
                break;
            }
            case "list_grades": {
                listGrade(op[1]);
                break;
            }
            case "report_unique_courses": {
                reportUniqueCourses();
                break;
            }
            case "report_unique_students": {
                reportUniqueStudents((S) op[0]);
                break;
            }
            case "report_average_score": {
                reportAverageScore(op[1], (G) Double.valueOf(op[2]));
                break;
            }


        }
    }


//    "java-fall-2025" <-> course
//    "java-fall-2025" <-> course
//    "java-fall-2025" <-> course
//    "java-fall-2025" <-> course
//    "java-fall-2025" <-> course

//
//    public Map<String, Course<S, G>> getCourses() {
//        return courses;
//    }
//
//    public void setCourses(Map<String, Course<S, G>> courses) {
//        this.courses = courses;
//    }
}
