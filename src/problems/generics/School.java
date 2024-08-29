package problems.generics;


import java.util.*;

public class School<T, S extends Number & Comparable<S>> {
    Map<T, Course<T, S>> courses;

    public School() {
        this.courses = new HashMap<>();
    }

    //To add course to school
    public void add_course(T courseName) {
        if (courses.isEmpty()) {
            this.courses.put(courseName, new Course<>());
            System.out.println("Course '" + courseName +"' added.");
        } else if (courses.containsKey(courseName)) {
            System.out.println("Course with identifier " + courseName + " already exists.");
        } else {
            this.courses.put(courseName, new Course<>());
            System.out.println("Course '" + courseName +"' added.");
        }
    }

    //To remove a course from school.
    public void remove_course(T courseName) {
        if (courses.isEmpty()) {
            System.out.println("There is no course to remove");
        } else if (!courses.containsKey(courseName)) {
            System.out.println("Course with identifier " + courseName + " not exists.");
        } else {
            this.courses.remove(courseName);
        }
    }

    //To list all courses in a school.
    public void list_courses() {
        if (courses.isEmpty()) {
            System.out.println("There is no course to list");
        } else {
            Set<T> courseNames = courses.keySet();
            System.out.println("Courses offered:");
            for (T course : courseNames) {
                System.out.println(course);
            }
        }
    }

    //To list grades of all students in a school
    public void list_grades(T courseName) {
        if (courses.isEmpty()) {
            System.out.println("Course '" + courseName + "' does not exist");
        } else if (courses.containsKey(courseName)) {
            Course<T, S> course = courses.get(courseName);
            Set<T> studentKey = course.getAllStudentsAndGrade().keySet();
            for (T student : studentKey) {
                System.out.println("Student: " + student + ", Grade: " + course.studentToGradeMap.get(student));
            }
        } else {
            System.out.println("Course with identifier " + courseName + " does not exists");
        }
    }

    //To enroll a student to a course
    public void enroll_student(T courseName, T studentID) {
        if (courses.isEmpty()) {
            System.out.println("Error: Cannot enroll student. Course '" + courseName + "' does not exist.");
        } else if (courses.containsKey(courseName)) {
            Course<T, S> course = courses.get(courseName);
            course.enrollStudent(studentID);
            System.out.println("Student '" + studentID +"' enrolled in course '" + courseName + "'.");
        } else {
            System.out.println("Error: Cannot enroll student. Course '" + courseName + "' does not exist.");
        }
    }

    public void assign_grade(T courseName, T studentID, S grade) {
        if (courses.isEmpty()) {
            System.out.println("Error: Cannot assign grade. Student '" +studentID + "' is not enrolled in course '" + courseName +"'.");
        } else if (courses.containsKey(courseName)) {
            Course<T, S> course = courses.get(courseName);
            if (course.studentToGradeMap.containsKey(studentID)) {
                course.studentToGradeMap.put(studentID, grade);
                System.out.println("Grade '" + grade + "' assigned to student '" + studentID +"' in course '"+ courseName+"'.");
            } else {
                System.out.println("Error: Cannot assign grade. Student '" +studentID + "' is not enrolled in course '" + courseName +"'.");
            }
        } else {
            System.out.println("Error: Cannot assign grade. Student '" +studentID + "' is not enrolled in course '" + courseName +"'.");
        }
    }

    //To list all unique courses in school
    public void report_unique_courses() {
        if (courses.isEmpty()) {
            System.out.println("There is no course to list");
        } else {
            Set<T> courseNames = courses.keySet();
            System.out.println("Courses offered:");
            for (T course : courseNames){
                System.out.println(course);
            }
        }
    }

    //To list all unique students in school (super set of all students in all courses)
    public void report_unique_students() {
        Set<T> uniqueStudentsInSchool = new HashSet<>();
        if (courses.isEmpty()) {
            System.out.println("There is no course to list");
        } else {
            Set<T> courseNames = courses.keySet();
            for (T course : courseNames) {
                if (!courses.get(course).isCourseNull()) {
                    Set<T> studentKeySet = courses.get(course).studentToGradeMap.keySet();
                    if (!studentKeySet.isEmpty()) {
                        uniqueStudentsInSchool.addAll(studentKeySet);
                    }
                }
            }
            System.out.println("Unique students enrolled:");
            for (T studentName : uniqueStudentsInSchool) {
                System.out.println(studentName);
            }

        }
    }


    //To Report average of all students grade in a course
    public void report_average_score(T courseName) {
        if (courses.isEmpty()) {
            System.out.println("There is no course to list");
        } else if (courses.containsKey(courseName)) {
            if (!courses.get(courseName).studentToGradeMap.isEmpty()) {
                double courseAve = courses.get(courseName).getAverage();

                System.out.println("Average score for course '" + courseName + "': "+ courseAve);
            } else {
                System.out.println("Course does not have any students enrolled yet");
            }
        } else {
            System.out.println("Course " + courseName + " not exists");
        }
    }

    public void report_cumulative_average(T studentID) {
        double studentGradeAvg = 0;
        List<Double> studentGrades= new ArrayList<>();
        if (courses.isEmpty()) {
            System.out.println("There is no course in school");
        } else {
            for (T course: courses.keySet()) {
                //System.out.println("course: " + course);
                if(courses.get(course).studentToGradeMap.containsKey(studentID)){
                    //System.out.println(courses.get(course).studentToGradeMap.get(studentID));
                    if (courses.get(course).studentToGradeMap.get(studentID) != null) {
                        studentGrades.add(courses.get(course).studentToGradeMap.get(studentID).doubleValue());
                    }
                }
            }
        }
        //System.out.println("Grades of Student  " + studentID + " is :" + studentGrades);
        for (Double grade : studentGrades)
        {
            studentGradeAvg += grade;
        }
        //System.out.println("Average grade of Student " + studentID + " is :" + studentGradeAvg/studentGrades.size());
        System.out.println("Cumulative average score for student '" +studentID + "': " +studentGradeAvg/studentGrades.size());
    }

    public void processCommand(String input) {
        List<String> allowedCommands = new ArrayList<>();
        allowedCommands.add("add_course");
        allowedCommands.add("list_courses");
        allowedCommands.add("enroll_student");
        allowedCommands.add("assign_grade");
        allowedCommands.add("list_grades");
        allowedCommands.add("report_unique_courses");
        allowedCommands.add("report_unique_students");
        allowedCommands.add("report_average_score");
        allowedCommands.add("report_cumulative_average");

        String[] inputArray = input.split(" ");
        if (inputArray.length > 4) {
            System.out.println("Entered invalid number of arguments");
            return;
        } else if (!allowedCommands.contains(inputArray[0])) {
            System.out.println("Error: Unknown command 'unknown_command'. Please use a valid command.");
            return;
        }
        switch (inputArray[0]) {
            case "add_course":
                if (inputArray.length != 2) {
                    System.out.println("Entered invalid number of arguments, please enter valid command");
                } else {
                    System.out.println(inputArray[1]);
                    add_course((T) inputArray[1]);
                }
                break;
            case "list_courses":
                if (inputArray.length != 1) {
                    System.out.println("Entered invalid number of arguments, please enter valid command");
                } else {
                    list_courses();
                }
                break;
            case "enroll_student":
                if (inputArray.length != 3) {
                    System.out.println("Entered invalid number of arguments, please enter valid command");
                } else {
                enroll_student((T) inputArray[1], (T) inputArray[2]);
                }
                break;
            case "assign_grade":
                if (inputArray.length != 4) {
                    System.out.println("Entered invalid number of arguments, please enter valid command");
                } else {
                assign_grade((T) inputArray[1], (T) inputArray[2],(S) Double.valueOf(inputArray[3]));
                }
                break;
            case "list_grades":
                if (inputArray.length != 2) {
                    System.out.println("Entered invalid number of arguments, please enter valid command");
                } else {
                   list_grades((T) inputArray[1]);
                }
                break;
            case "report_unique_courses":
                if (inputArray.length != 1) {
                    System.out.println("Entered invalid number of arguments, please enter valid command");
                } else {
                    report_unique_courses();
                }
                break;
            case "report_unique_students":
                if (inputArray.length != 1) {
                    System.out.println("Entered invalid number of arguments, please enter valid command");
                } else {
                    report_unique_students();
                }
                break;
            case "report_average_score":
                if (inputArray.length != 2) {
                    System.out.println("Entered invalid number of arguments, please enter valid command");
                } else {
                   report_average_score((T) inputArray[1]);
                }
                break;
            case "report_cumulative_average":
                if (inputArray.length != 2) {
                    System.out.println("Entered invalid number of arguments, please enter valid command");
                } else {
                  report_cumulative_average((T) inputArray[1]);
                }
                break;
        }
    }
}

