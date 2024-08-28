package genricexampleswithouttest;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
public class CourseGeneric<S, G> {
    private G grade;
    Map<S, G> courseGpa = new HashMap<>();
    public CourseGeneric() {// constructor
        this.courseGpa = new HashMap<>();
    }
    public void enrollStudent(S studentid) {// enrolling students
        if (!courseGpa.containsKey(studentid)){
            G put = courseGpa.put(studentid, grade);
            System.out.println(studentid +" Student is enrolled");
        }
    }
    public void assigningGrades(S studentid, G grade) {// assigning a grade

        courseGpa.containsKey(studentid);
        G put = this.courseGpa.put(studentid, grade);
        System.out.println("Student id :"+studentid +"with "+ grade+" grades");

    }
    public G retrivingGrades(S studentid) {// retriving grades
        return this.courseGpa.get(studentid);

    }
    public void getlist(){
        Set<Map.Entry<S, G>> entries = courseGpa.entrySet();
        System.out.println("List of students with grades");
        for (Map.Entry<S, G> s:entries){
            System.out.println("Student id:"+ s.getKey()+" grades: "+ s.getValue());
        }

    }
}
