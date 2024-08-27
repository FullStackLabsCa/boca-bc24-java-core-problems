package course;

import java.util.HashMap;
import java.util.Map;

public class Course <S,G extends Number>{
    private Student student;
    private Grade grade;
    private Map<Student, Grade> course = new HashMap<Student, Grade>();

    public Course(Student student, Grade grade) {
        this.student = student;
        this.grade = grade;
    }

    public Course() {

    }


    public Map<Student, Grade> enrollStudent(S Student, G Grade){
        course.put(student, grade);
        return course;
    }

}
