package generics.course;

import java.util.HashMap;
import java.util.Map;

public class Course<S, G extends Number> {
    private S studentIdentifier;
    private S course;
    private G grade;

    Map<S, Map<S, G>> studentToCourseMap = new HashMap<>();

    public S getCourse() {
        return course;
    }

    public void setCourse(S course, S studentIdentifier) {
        studentToCourseMap.get(studentIdentifier).put(course, null);
    }

//    Course(S studentIdentifier) {
//        this.studentIdentifier = studentIdentifier;
//    }

    public void enrollStudent(S studentIdentifier) {
        studentToCourseMap.put(studentIdentifier, null);
    }

    public void assignGrade(S studentIdentifier, S courseName) {

    }

    public void retrieveGrade(S studentIdentifier, S courseName) {

    }

    public void listGrades(S studentIdentifier) {

    }
}
