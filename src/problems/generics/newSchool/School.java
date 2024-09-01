package problems.generics.newSchool;

import java.util.*;

public class School<S,G extends Number> {


    public HashMap<String, problems.generics.newSchool.Course> courseMap= new HashMap<>();




    public void processCommand(String inputCommand) {

        String[] parts =String.valueOf(inputCommand).split(" ");
        String command = parts[0];


        switch(command){

            case "add_course":
                String course = parts[1];
                if(courseMap.containsKey(course)) {
                    System.out.println("Course Already Exist");
                }
                else {
                    Course<S,G> courseObject = addCourse(parts[1]);
                    courseMap.put(parts[1], courseObject);
                    System.out.println("Course '" + parts[1] + "' added.");
                }
                break;

            case "list_courses":
                if(courseMap.keySet().isEmpty()){
                    System.out.println("No Course Added");
                }
                else{
                    System.out.println("Courses offered:");
                    for(String element:courseMap.keySet() ){
                        System.out.println(element);

                    }
                }


                break;

            case "enroll_student":

                if (courseMap.containsKey(parts[1])) {
                    Course<S,G> courseObject = courseMap.get(parts[1]);
                    courseObject.enrollStudent((S) parts[2]);
                    System.out.println("Student '"+ parts[2] +"' enrolled in course '"+parts[1]+"'.");
                } else {
                    System.out.println("Error: Cannot enroll student. Course '"+parts[1]+"' does not exist.");
                }
                break;


            case "list_grades":
                if (courseMap.containsKey(parts[1])) {
                    Course<S,G> courseObject = courseMap.get(parts[1]);
                    Map<S,G> allGrades = courseObject.getAllGrades();
                    for (S key: allGrades.keySet()) {
                        System.out.println("Student: "+key+", Grade: "+allGrades.get(key));
                    }
                }
                break;

            case "assign_grade":

                boolean flag = true;
                if (courseMap.containsKey(parts[1])) {
                    Course<S,G> courseObject = courseMap.get(parts[1]);

                    flag = courseObject.assignGrade((S) parts[2], (G) Double.valueOf(parts[3]));
                    if (flag) {
                        System.out.println("Grade '" + parts[3] + "' assigned to student '" + parts[2] + "' in course '" + parts[1] + "'.");
                    }
                    else{
                        System.out.println("Error: Cannot assign grade. Student '"+parts[2]+"' is not enrolled in course '"+parts[1]+"'.");
                    }
                }
                else{

                }
                break;

            case "report_unique_courses":
                System.out.println("Courses offered:");
                if(courseMap.isEmpty()){
                    System.out.println("None");
                }
                else {
                    for ( String key: courseMap.keySet() ){
                        System.out.println(key);
                    }
                }
                break;

            case "report_unique_students":
                System.out.println("Unique students enrolled:");
                for(String courseKey:courseMap.keySet()) {
                    Course<S,G> courseObject = courseMap.get(courseKey);
                    Map<S, G> allGrades = courseObject.getAllGrades();
                    for (S studentKey : allGrades.keySet()) {
                        System.out.println(studentKey);
                    }
                }


                break;

            case "report_average_score":
                if (courseMap.containsKey(parts[1])) {
                    Double sum=0.0;
                    Double avg;
                    Course<S,G> courseObject = courseMap.get(parts[1]);
                    Map<S,G> allGrades = courseObject.getAllGrades();
                    for (S key: allGrades.keySet()) {
                        sum=sum+ allGrades.get(key).doubleValue();
                    }
                    avg=sum/courseMap.keySet().size();
                    System.out.println("Average score for course '"+parts[1]+"': "+avg);
                }
                break;

            case "report_cumulative_average":

                Double sum=0.0;
                Double cgpa;
                for (String courseKey : courseMap.keySet()) {
                    Course<S, G> courseObject = courseMap.get(courseKey);
                    Map<S, G> allGrades = courseObject.getAllGrades();
                    for (S key : allGrades.keySet()) {
                        if (key.equals(parts[1])) {
                            sum = sum + allGrades.get(key).doubleValue();
                        }
                    }
                }
                cgpa=sum/courseMap.keySet().size();
                System.out.println("Cumulative average score for student '"+parts[1]+"': "+cgpa);

                break;
            default:
                System.out.println("Error: Unknown command 'unknown_command'. Please use a valid command.");
                break;

        }




    }

    public Course<S,G> addCourse(String courseValue){
        return new Course<S,G>();
    }

}