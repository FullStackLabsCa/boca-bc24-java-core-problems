package problems.generics.schoolproblem;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class School<T, S, G > {
    private Map<S, G> studentGrades;
    private Set <T> courseSet;

    private Map<Set<T>,Map<S, G>>courseToStudent;
    public School() {
        this.studentGrades = new HashMap<>();
        this.courseSet = new HashSet<>();

    }
    public void addCourse(T elements){
        courseSet.add(elements);
    }

    public Set<T> getCourses() {
        return (courseSet);
    }
    public void enrollStudent(S studentId, G initialGrade) {
        studentGrades.put(studentId, initialGrade);
    }


}
