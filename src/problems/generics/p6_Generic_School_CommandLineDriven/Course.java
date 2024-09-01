package problems.generics.p6_Generic_School_CommandLineDriven;

import java.util.*;

public class Course<S, G extends Number> {

    Map<S, G> course = new HashMap<>();

    // Constructor : 1.) without parametere 2.) With Parameter --------------------
    public Course() {
    }

    public Course(Map<S, G> course) {
        this.course = course;
    }

    // Getter and Setter : for Course --------------------------
    public Map<S, G> getCourse() {
        return course;
    }

    public void setCourse(Map<S, G> course) {
        this.course = course;
    }

    // Add Student into course : course<studentId, Grade> --------------------
    public S enrollStudent(S student) {
        this.course.put(student, null);
        return student;
    }

    // Give-assign grade to particular student --------------------
    public G assignGrade(S studentId, G grade) {
        if (this.course.containsKey(studentId)) {
            return this.course.put(studentId, grade);
        }
        return grade;
    }

    public Optional<G> getGrade(S identifier){
        if(course.containsKey(identifier)){
//            return this.course.get(identifier);
            return Optional.of(this.course.get(identifier));
        }
        return Optional.empty();
    }

    // Return and print all student with grades
    public void allStudentWithGrade(){
        course.forEach((k, v) -> {
            System.out.println("Student: " + k + ", " + "Grade: " + v);
        });
    }

    // Return all Grades as a List
    public List<G> listAllGrades(){
        Set<S> allKeys = this.course.keySet();
        List<G> lisofGrades = new ArrayList<>();
        for(S k : allKeys){
            lisofGrades.add(this.course.get(k));
        }
        return lisofGrades;
    }

    // Return all Students as a List
    public List<S> listAllStudents(){
        Set<S> allKeys = this.course.keySet();
        List<S> lisofStudents = new ArrayList<>();
        lisofStudents.addAll(allKeys);
        return lisofStudents;
    }


    public Map<S, G> getAllGrades() {
        return this.course;
    }

    public boolean isStudentEnrolled(S studentId) {
        return this.course.containsKey(studentId);
    }

    // for checking the methods: add student,grade and list of all students
    public static void main(String[] args) {
        Course<String, Double> stringDoubleCourse = new Course<>();

        stringDoubleCourse.enrollStudent("Dwarkesh");
        stringDoubleCourse.assignGrade("Dwarkesh", 100.00);
        stringDoubleCourse.getGrade("Dwarkesh");

        stringDoubleCourse.enrollStudent("Jay");
        stringDoubleCourse.assignGrade("Jay", 95.00);
        stringDoubleCourse.getGrade("Jay");

        stringDoubleCourse.allStudentWithGrade();

        System.out.println(stringDoubleCourse.listAllGrades());
        System.out.println(stringDoubleCourse.listAllStudents());
    }



}
