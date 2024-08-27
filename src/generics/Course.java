package generics;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public class Course <S, G extends Number> {

//    private S studentInformation; //Could be String or Integer being student ID
//    private G grade; //Have to be something Number

    //Maps will be a good choice because we have a unique student with some grade in the course.
    private Map<S,G> courseMapping;

    public Course() {
         courseMapping = new HashMap<>();
    }

    //Enroll Students
    public boolean enrollStudent(S student){
        //ValidateStudent
        //.
        if(!courseMapping.containsKey(student)){
            courseMapping.put(student,null);
//            System.out.println("student enrolled : " + student);
            return true;
        } else {
            System.out.println("Student Already enrolled in the course.");
            return false;
        }
    }

    //Assign Grades
    public boolean assignGrade(S student, G grade){
        if(courseMapping.containsKey(student)){
             courseMapping.put(student, grade);
             return true;
        } else {
            System.out.println("No such Student is enrolled in the course.");
            return false;
        }
    }

    //Retrieve Grades
    public Optional<Double> getGrade(S studentID){
        if(courseMapping.containsKey(studentID) && courseMapping.get(studentID)  != null){
            return Optional.of(courseMapping.get(studentID).doubleValue());
        } else {
            System.out.println("No such Student is enrolled in the course or No Grade assigned.");
            return Optional.empty();
        }
    }

    //List all Grades
    public void listAllGrades(){
        if(!courseMapping.isEmpty()) {
            for (S student : courseMapping.keySet()) {
                System.out.println("Student: " + student.toString() + " - Grade: " + courseMapping.get(student));
            }
            //OR
//            Set<Map.Entry<S, G>> entries = courseMapping.entrySet();
//            System.out.println("entries : " + entries);
        } else {
            System.out.println("EMPTY COURSE!!! No students currently enrolled within the course.");
        }
    }

    public Collection<S> getStudents(){
        return courseMapping.keySet();
    }


    public double calculateAverageGrade(){
        Collection<G> grades = courseMapping.values();
        double sum = 0;

        for (G grade : grades){
            sum += grade.doubleValue();
        }

        return (sum / grades.size());
    }

    public static void main(String[] args) {

        Course<String, Double> javaProgramming  = new Course<>();

        javaProgramming.getGrade("Akshat");
        javaProgramming.listAllGrades();

        javaProgramming.enrollStudent("Akshat");
        javaProgramming.assignGrade("Akshat", 10.0);

        System.out.println(javaProgramming.getGrade("Akshat"));

        javaProgramming.enrollStudent("Satwik");
        javaProgramming.assignGrade("Satwik", 9.9);

        javaProgramming.listAllGrades();

    }

    public boolean isStudentEnrolled(S studentIdentifier) {
        if(courseMapping.containsKey(studentIdentifier)){
            return true;
        } else return false;
    }

    public Map<S, G> getAllGrades() {
        return courseMapping;
    }

}
