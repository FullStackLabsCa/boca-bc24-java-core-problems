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
//            System.out.println("Added " + courseName + " to school.");
            return true;
        } else {
            System.out.println("Course " + courseName + " Already Exists!");
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
    public boolean enrollStudentToCourse(String courseName, S studentIdentifier){
        //Check if course Exists in the School
        if(schoolMap.containsKey(courseName)){
            Course<S, G> course = schoolMap.get(courseName);
            if(course.enrollStudent(studentIdentifier)) {
                System.out.println("Student " + studentIdentifier + " enrolled in " + courseName);
            }
            return true;
        } else {
            System.out.println("Course " + courseName + " is not available in this school.");
            return false;
        }
    }

    //Assign grade to a student
    public boolean assignGradeToStudentInCourse(String courseName, S studentIdentifier, G grade){
        if(schoolMap.containsKey(courseName)){
            Course<S, G> course = schoolMap.get(courseName);
            course.assignGrade(studentIdentifier, grade);
            System.out.println(grade + " assigned to " + studentIdentifier + " for course " + courseName);
            return true;
        } else {
            System.out.println("Course is not available in this school.");
            return false;
        }
    }

    public void listGrades(String courseName){
        Course<S, G> course;
        if(schoolMap.containsKey(courseName)) {
            course = schoolMap.get(courseName);
        } else return;

        Map<S, G> students = course.getAllGrades();
        System.out.println("Grades for all students of " + courseName + ":\n" + students);

    }

    //List all unique students in the school
    public void listAllStudentsInSchool(){
        Set<S> listOfStudents = new TreeSet<>();

        Collection<Course<S, G>> courses = schoolMap.values();

        for(Course<S,G> course : courses){
            Collection<S> studentsInCourse = course.getStudents();
            listOfStudents.addAll(studentsInCourse);
        }

        if(!listOfStudents.isEmpty()){
            System.out.println("listOfStudents = " + listOfStudents);
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
                coursesParticipated++;
                if(course.getGrade(studentIdentifier).isPresent()) {
                    sumOfGPA += course.getGrade(studentIdentifier).get();
                }
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
        delhiPublicSchool.enrollStudentToCourse("English-2024", "Akshat-Singla");
        delhiPublicSchool.enrollStudentToCourse("Math-2024", "Akshat-Singla");
        delhiPublicSchool.enrollStudentToCourse("Science-2024", "Akshat-Singla");
        delhiPublicSchool.enrollStudentToCourse("Math-2024", "Satwik-Singla");
        delhiPublicSchool.enrollStudentToCourse("English-2024", "Satwik-Singla");
        convent.enrollStudentToCourse("math-24-25", 1);
        convent.enrollStudentToCourse("math-24-24", 332);
        convent.enrollStudentToCourse("math-24-25", 332);

        delhiPublicSchool.listAllStudentsInSchool();
        convent.listAllStudentsInSchool();

        delhiPublicSchool.assignGradeToStudentInCourse("English-2024", "Akshat-Singla", 3.4);
        delhiPublicSchool.assignGradeToStudentInCourse("English-2024", "Akshat-Singla", 2.4);
        delhiPublicSchool.assignGradeToStudentInCourse("Math-2024", "Akshat-Singla", 3.0);
//        delhiPublicSchool.assignGradeToStudentInCourse("Akshat-Singla", 0.0, "Science-2024");
        delhiPublicSchool.assignGradeToStudentInCourse("English-2024", "Satwik-Singla", 3.7);


        delhiPublicSchool.listGrades("English-2024");
        System.out.println(delhiPublicSchool.calculateAverageGradeOfCourse("English-2024"));
        System.out.println(delhiPublicSchool.getStudentCGPA("Akshat-Singla") + " average Grade");

    }

}
