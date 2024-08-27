package generics;

import java.util.*;

public class School<S , G extends Number> {

    //Mapping course-name, and course-object
    Map<String, Course<S,G>> schoolMap;
    //Use a Map because Time Complexity of grabbing the nth key is O(1)
    //In the set the time complexity is O(n) because Set does not allow search like HashMap even though the underlying data type is a HashMap

    //Initialize the set for school
    public School() {
        schoolMap = new HashMap<>();
    }

    //Add Course
    public boolean addCourse(String courseName){
        if(!schoolMap.containsKey(courseName)){
            schoolMap.put(courseName, new Course<>());
            return true;
        } else {
            System.out.println("Course Already Exists!");
            return false;
        }
    }

    //Get the list of all Courses in the School
    public void listAllCourses(){
        if(!schoolMap.isEmpty()) {
            Set<String> listOfCourses = schoolMap.keySet();
            System.out.println(listOfCourses);
        } else {
            System.out.println("School does not contain any courses right now.");
        }
    }

    //Enroll Student to a course
    public boolean enrollStudentToCourse(S studentIdentifier, String courseName){
        //Check if course Exists in the School
        if(schoolMap.containsKey(courseName)){
            Course<S, G> course = schoolMap.get(courseName);
            course.enrollStudent(studentIdentifier);
            System.out.println("Student " + studentIdentifier + " enrolled.");
            return true;
        } else {
            System.out.println("Course is not available in this school.");
            return false;
        }
    }

    //Assign grade to a student
    public boolean assignGradeToStudentInCourse(S studentIdentifier, G grade, String courseName){
        if(schoolMap.containsKey(courseName)){
            Course<S, G> course = schoolMap.get(courseName);
            course.assignGrade(studentIdentifier, grade);
            System.out.println(grade + " assigned to " + studentIdentifier);
            return true;
        } else {
            System.out.println("Course is not available in this school.");
            return false;
        }
    }

    //List all unique students in the school
    public void listAllStudentsInSchool(){
        Set<S> listOfStudents = new HashSet<>();

        Collection<Course<S, G>> courses = schoolMap.values();

        for(Course<S,G> course : courses){
            Collection<S> studentsInCourse = course.getStudents();
            listOfStudents.addAll(studentsInCourse);
        }

        if(!listOfStudents.isEmpty()){
            for (S student : listOfStudents) {
                System.out.println(student);
            }
        } else {
            System.out.println("School does not have any students yet!");
        }

        //Can return listOfStudents
    }

    //Course's Average Grade
    public double calculateAverageGradeOfCourse(String courseName){
        double average;
        if(schoolMap.containsKey(courseName)){
            Course<S, G> course = schoolMap.get(courseName);
            average = course.calculateAverageGrade();
        } else {
            System.out.println("Course is not available in this school.");
            average = 0;
        }

        return average;
    }

    //Average grade of student amongst all enrolled courses
    public double getStudentCGPA(S studentIdentifier){
        double sumOfGPA = 0;
        int coursesParticipated = 0;
        Collection<Course<S, G>> courses = schoolMap.values();

        for(Course<S,G> course : courses){
            Collection<S> students = course.getStudents();

            if(students.contains(studentIdentifier)){
                sumOfGPA += course.retrieveGrade(studentIdentifier);
                coursesParticipated++;
            }
        }

        return (sumOfGPA / coursesParticipated);
    }


    public static void main(String[] args) {

        //Create 2 school object Instances
        School<String, Double> delhiPublicSchool = new School<>();
        School<Integer, Double> convent = new School<>();

        //Testing listAllCourses for Empty School
        delhiPublicSchool.listAllCourses();

        //Test AddCourse
        delhiPublicSchool.addCourse("English-2024");
        delhiPublicSchool.addCourse("Math-2024");
        delhiPublicSchool.addCourse("Science-2024");
        delhiPublicSchool.addCourse("Games-2024");
        delhiPublicSchool.addCourse("Aptitude-2024");

        convent.addCourse("math-24-25");
        convent.addCourse("science-24-25");
        convent.addCourse("computer-24-25");
        convent.addCourse("sports-24-25");
        convent.addCourse("dance-24-25");

        //List all Courses
        delhiPublicSchool.listAllCourses();
        convent.listAllCourses();

        //Retrieve students in a course
        delhiPublicSchool.listAllStudentsInSchool(); //Should say No students in the course.

        //Enrolling student to a Course
        
    }

}
