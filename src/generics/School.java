package generics;

import java.sql.SQLOutput;
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
    public void addCourse(String courseName){
        if(!schoolMap.containsKey(courseName)){
            schoolMap.put(courseName, new Course<>());
            System.out.println("Course '" + courseName + "' added.");
//            return true;
        } else {
            System.out.println("Error! Course " + courseName + " Already Exists!");
//            return false;
        }
    }

    //Get the list of all Courses in the School
    public void listAllCourses(){
        if(!schoolMap.isEmpty()) {
            Set<String> listOfCourses = schoolMap.keySet();
            System.out.println("Courses offered: \n" + listOfCourses);
        } else {
            System.out.println("School does not contain any courses right now.");
        }
    }

    //Enroll Student to a course
    public void enrollStudentToCourse(String courseName, S studentIdentifier){
        //Check if course Exists in the School
        if(schoolMap.containsKey(courseName)){
            Course<S, G> course = schoolMap.get(courseName);
            if(course.enrollStudent(studentIdentifier)) {
                System.out.println("Student '" + studentIdentifier + "' enrolled in course '" + courseName + "'.");
            }
        } else {
            System.out.println("Error: Cannot enroll student. Course '" + courseName + "' does not exist.");
        }
    }

    //Assign grade to a student
    public void assignGradeToStudentInCourse(String courseName, S studentIdentifier, G grade){
        if(schoolMap.containsKey(courseName)){
            Course<S, G> course = schoolMap.get(courseName);
            if(course.assignGrade(studentIdentifier, grade)) {
                System.out.println("Grade '" + grade + "' assigned to student '" + studentIdentifier + "' in course '" + courseName + "'.");
//                return true;
            } else{
                System.out.println("Error: Cannot assign grade. Student '" + studentIdentifier + "' is not enrolled in course '"+ courseName +"'.");
//                return false;
            }
        } else {
            System.out.println("Course is not available in this school.");
//            return false;
        }
    }

    //List Grades
    public void listGrades(String courseName){
        Course<S, G> course;
        if(schoolMap.containsKey(courseName)) {
            course = schoolMap.get(courseName);
        } else {
            System.out.println("Error! Course '"+ courseName+"' does not exist.");
            return;
        }

        System.out.println("List of Grades in Course '" + courseName + "':");
        course.listAllGrades();
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
            System.out.println("Unique students enrolled:\n" + listOfStudents);
        } else {
            System.out.println("School does not have any students yet!");
        }

        //Can return listOfStudents
    }

    //Course's Average Grade
    public void calculateAverageGradeOfCourse(String courseName){
        double average;
        if(schoolMap.containsKey(courseName)){
            Course<S, G> course = schoolMap.get(courseName);
            average = course.calculateAverageGrade();
        } else {
            System.out.println("Course is not available in this school.");
            average = 0;
        }

        System.out.println("Average score for course '"+courseName+"': "+average);
//        return average;
    }

    //Average grade of student amongst all enrolled courses
    public void getStudentCGPA(S studentIdentifier){
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
        double CGPA = (sumOfGPA / coursesParticipated);
        System.out.println("Cumulative average score for student '"+studentIdentifier+"': "+CGPA);
//        return (sumOfGPA / coursesParticipated);
    }


    //Process Commands
    public void processCommand(String inputCommand) {

        //Create an arguments array/collection which will store the arguments
        String[] arguments = inputCommand.trim().split(" ");

        //'0' the argument being the command itself on which the switch case will process the operations
        switch (arguments[0]){
            case "add_course":
                addCourse(arguments[1]);
                break;
            case "list_courses":
                listAllCourses();
                break;
            case "enroll_student":
                enrollStudentToCourse(arguments[1], (S) arguments[2]);
                break;
            case "assign_grade":
                assignGradeToStudentInCourse(arguments[1], (S) arguments[2], (G) Double.valueOf(arguments[3]));
                break;
            case "list_grades":
                listGrades(arguments[1]);
                break;
            case "report_unique_courses":
                listAllCourses();
                break;
            case "report_unique_students":
                listAllStudentsInSchool();
                break;
            case "report_average_score":
                calculateAverageGradeOfCourse(arguments[1]);
                break;
            case "report_cumulative_average":
                getStudentCGPA((S) arguments[1]);
                break;
            default:
                System.out.println("Error: Unknown command 'unknown_command'. Please use a valid command.");
        }

        //Other arguments will be used as arguments for method parameters when making method calls

    }

    public static void printHelperCommands(){
        String menu = """
                - add_course <course_name>    ->	Adds a new course to the school.
                - list_courses    ->	Lists all courses offered by the school.
                - enroll_student <course_name> <id>   ->	Enrolls a student in the specified course.
                - assign_grade <course_name> <id> <g> ->	Assigns a grade to the student in the specified course.
                - list_grades <course_name>   ->	Lists all students and their grades for the specified course.
                - report_unique_courses   ->	Lists all unique courses offered by the school.
                - report_unique_students  ->	Lists all unique students enrolled in the school.
                - report_average_score <course_name>  ->	Reports the average score of the specified course.
                - report_cumulative_average <id>  ->	Reports the cumulative average score for the specified student.
                """;
        String menu1 = """
                - add_course <course_name>
                - list_courses
                - enroll_student <course_name> <id>
                - assign_grade <course_name> <id> <g>
                - list_grades <course_name>
                - report_unique_courses
                - report_unique_students
                - report_average_score <course_name>
                - report_cumulative_average <id>
                """;
        System.out.println(menu1);
    }

    //Main method for testing the code:
    public static void main2(String[] args) {

        //Create 2 school object Instances
        School<String, Double> delhiPublicSchool = new School<>();
        School<Integer, Double> convent = new School<>();

        //Testing listAllCourses for Empty School
        delhiPublicSchool.listAllCourses();

        //Test AddCourse
//        delhiPublicSchool.addCourse("English-2024");
//        delhiPublicSchool.addCourse("Math-2024");
//        delhiPublicSchool.addCourse("Math-2024");
//        delhiPublicSchool.addCourse("Science-2024");
//        delhiPublicSchool.addCourse("Games-2024");
//        delhiPublicSchool.addCourse("Aptitude-2024");
        delhiPublicSchool.processCommand("add_course English-2024");
        delhiPublicSchool.processCommand("add_course Math-2024");
        delhiPublicSchool.processCommand("add_course Math-2024");
        delhiPublicSchool.processCommand("add_course Science-2024");
        delhiPublicSchool.processCommand("add_course Games-2024");
        delhiPublicSchool.processCommand("add_course Aptitude-2024");

//        convent.addCourse("math-24-25");
//        convent.addCourse("science-24-25");
//        convent.addCourse("computer-24-25");
//        convent.addCourse("sports-24-25");
//        convent.addCourse("dance-24-25");
        convent.processCommand("add_course math-24-25");
        convent.processCommand("add_course science-24-25");
        convent.processCommand("add_course computer-24-25");
        convent.processCommand("add_course sports-24-25");
        convent.processCommand("add_course dance-24-25");

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
//        System.out.println(delhiPublicSchool.calculateAverageGradeOfCourse("English-2024"));
//        System.out.println(delhiPublicSchool.getStudentCGPA("Akshat-Singla") + " average Grade");

    }

    //School Launcher
    public static void main(String[] args) {
        School<Integer, Double> school= new School<>();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to D.P. School:");
        System.out.println("Please follow the instructions below to help yourself:");
        while(true){
            printHelperCommands();
            String input = scanner.nextLine();
            school.processCommand(input);
        }
    }
}
