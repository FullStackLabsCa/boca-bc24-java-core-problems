package generics;

import java.util.HashMap;
import java.util.Map;

public class Course<S, G extends Number>{
    Map<S, G> students;

    public Course() {
        this.students = new HashMap<>();
    }

    public void addStudent(S studentId){
        students.put(studentId, null);
    }

    public void addGrade(S studentId, G grade){
        students.put(studentId, grade);
    }

    public void getAllStudents(){
        for (Map.Entry<S, G> student:   students.entrySet() ){
            System.out.println(student.getKey() +" "+ student.getValue());
        }
    }

}
