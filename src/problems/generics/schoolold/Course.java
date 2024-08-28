package generics.schoolold;

import java.util.HashMap;
import java.util.Map;

public class Course<S, G extends Number> {
//    private S studentIdentifier;
//    private S course;
//    private G grade;

    Map<S, Map<S, G>> studentToCourseMap = new HashMap<>();

//    public S getCourse() {
//        return course;
//    }

//    public void setCourse(S course, S studentIdentifier) {
//        studentToCourseMap.computeIfAbsent(studentIdentifier, k -> new HashMap<>()).put(course, null);
//    }

//    Course(S studentIdentifier) {
//        this.studentIdentifier = studentIdentifier;
//    }

    public void enrollStudent(S studentIdentifier) {
        studentToCourseMap.putIfAbsent(studentIdentifier, new HashMap<>());
    }

    public void assignCourse(S studentIdentifier, S course) {
        Map<S, G> courseMap = studentToCourseMap.get(studentIdentifier);
        if (courseMap != null) {
            courseMap.put(course, null);
        } else {
            System.out.println("Student with this course doesn't exist in a map.!");
        }
    }

    public void assignGrade(S studentIdentifier, S course, G grade) {
        Map<S, G> courseMap = studentToCourseMap.get(studentIdentifier);
        if (courseMap != null) {
            courseMap.put(course, grade);
        } else {
            System.out.println("Student with name doesn't exist");
        }
    }

    public G retrieveGrade(S studentIdentifier, S course) {
        Map<S, G> courseMap = studentToCourseMap.get(studentIdentifier);
        if (courseMap != null) {
            return courseMap.get(course);
        } else {
            System.out.println("Student with this identifier doesn't exist.");
            return null;
        }
    }

    public void listGrades() {
        for (Map.Entry<S, Map<S, G>> student: studentToCourseMap.entrySet()) {
            S studentName = student.getKey();  // what if student identifier is number
            Map<S, G> corseToGradeMap = student.getValue();
            System.out.println("\n****************************");
            System.out.println("Student ::" + studentName);
            for (Map.Entry<S, G> courseGrade: corseToGradeMap.entrySet()) {
                S courseName = courseGrade.getKey();
                G grade = courseGrade.getValue();
                System.out.println("Course Name: " + courseName + "\nGrade: " + grade);
            }
        }
//        System.out.println("List of grade===" + studentToCourseMap);
    }
}
