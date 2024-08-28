package generic_problems;

import java.util.*;

public class Course<S, G extends Number> {
    private S student;
    private G grade;
    private Map<S, G> studentGradeMap;
//    private String courseName;

    public Course() {
//        this.courseName=courseName;
        this.studentGradeMap = new HashMap<>();
    }
public void enrollStudent(S student){
    if (studentGradeMap.containsKey(student)){
        System.out.println(student + " is already enrolled");
    }else {
        studentGradeMap.put(student, grade);
        System.out.println(student + " is enrolled");
    }
}
    public Boolean isStudentEnrolled(S student) {
        boolean enrolled=false;
        if (studentGradeMap.containsKey(student)) {
            System.out.println(student + " is already enrolled");
            enrolled=true;
        } else {
            enrolled=false;
        }
        return enrolled;
    }

    public void assignGrade(S student,G grade) {
        if (studentGradeMap.containsKey(student)) {
            studentGradeMap.put(student, grade);
            System.out.println(student + "  grade is updated to "+ grade);
        }else System.out.println(student+ " doesn't exist");
    }

    public Optional<G> getGrade(S student) {
        G studentGrade;
        if (studentGradeMap.containsKey(student)) {
            return Optional.of(studentGradeMap.get(student));
        }else {
            System.out.println(student+ " doesn't exist");
            return Optional.empty();
        }
    }

    public static <G> Optional<G> toOptional(Collection<G> collection) {
        if (collection == null || collection.isEmpty()) {
            return Optional.empty();
        } else {
            Iterator<G> iterator = collection.iterator();
            if (iterator.hasNext()) {
                return Optional.of(iterator.next());
            } else {
                return Optional.empty();
            }
        }
    }

    public void listAllStudents(){
        if (studentGradeMap.isEmpty()){
            System.out.println("No Student enrolled in this course");
        }else {Set<Map.Entry<S, G>> entries = studentGradeMap.entrySet();
        for (Map.Entry<S, G> set: entries) {
            System.out.println("List of all Students");
            System.out.println("Student Name: "+set.getKey() + " " + "\nGrade: "+set.getValue());
        }
        }
    }

    public Map<S,G> getAllGrades() {
        if (studentGradeMap.isEmpty()){
            System.out.println("No Student enrolled in this course");
    }else {
            studentGradeMap.size();
        }
        return studentGradeMap;
    }
    public void listAllGrades() {
    }

    public static void main(String[] args) {
        Course<String,Double> java = new Course<>();
        java.isStudentEnrolled("Gurpreet");
        java.assignGrade("Gurpreet",90.0);
        java.getGrade("Gurpreet");
        java.listAllStudents();
    }

}