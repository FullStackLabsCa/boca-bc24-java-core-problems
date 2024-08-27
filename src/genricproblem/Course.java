package genricproblem;

import java.util.HashMap;
import java.util.Map;

public class Course<S,G> {
   Map<S,G> courseGpa= new HashMap<>();


    //enroll the student
    public void enrollStudent(S studentID){
        this.courseGpa.put(studentID,null);
    }
    // assigning grade
    public void assignGrade(S studentID, G grade){
        if(this.courseGpa.containsKey(studentID)){
            this.courseGpa.replace(studentID, grade);
        }
    }

    // retrieve grade

    public void retrieveGrade(S studentID, G grade){
        if(this.courseGpa.containsKey(studentID)){
            this.courseGpa.get(grade);
        }
    }
    //list all the grade
    public void listAllGrade(S studentID, G grade){



    }

}
