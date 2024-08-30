package problems.generics.problem6_school;


import java.util.*;


public class School<S, G extends Number> {
    S student;
    G grade;
    String courseName;
    Map<String, Course<S, G>> schoolMap;

    public School() {
        schoolMap = new HashMap<>();
    }

    public void addCourse(String courseName) {
        schoolMap.put(courseName, new Course<>());
        System.out.println("Course '" + courseName + "' added.");
    }

    public void enrollStudent(String courseName, S student) {
        if (schoolMap.containsKey(courseName)) {
            schoolMap.get(courseName).enrollStudent(student);
            System.out.println("Student '" + student + "' enrolled in course '" + courseName + "'.");
        } else System.out.println("Error: Cannot enroll student. Course 'Physics103' does not exist.");
    }

    public void assignGrade(String courseName, S student, G grade) {
        if (schoolMap.get(courseName).assignGrade(student, grade)) {
            System.out.println("Grade '" + grade + "' assigned to student '"
                    + student + "' in course '" + courseName + "'.");
        } else {
            System.out.println("Error: Cannot assign grade. Student '" + student +
                    "' is not enrolled in course '" + courseName + "'.");
        }
    }

    public void listGrade(String courseName) {
        if (schoolMap.containsKey(courseName) && !schoolMap.containsKey(null)) {
            schoolMap.get(courseName).listAllGrades();
        } else {
            System.out.println("Course Doesn't exist.");
        }
    }

    public void listCourse() {
        Set<String> strings = schoolMap.keySet();
//        System.out.println(
//                strings.stream()
//                        .map(String::toLowerCase)
//                        .collect(Collectors.toSet())
//        );
        System.out.println("Courses offered:");
        for (String elements : strings) {
            System.out.println(elements);
        }
    }

    public void listUniqueStudent() {
        Set<S> listOfUniqueStudents = new TreeSet<>();
        System.out.println("Unique students enrolled:");
        if (!schoolMap.isEmpty() && !schoolMap.containsKey(null)) {
            Collection<Course<S, G>> courses = schoolMap.values();
            for (Course<S, G> course : courses) {
                Collection<S> studentsCollection = course.listUniqueAllStudent();
                listOfUniqueStudents.addAll(studentsCollection);
            }
            for (S uniqueStudent : listOfUniqueStudents)
                System.out.println(uniqueStudent);
        } else System.out.println("Error: ");
    }

    private void averageGrades(String courseName) {
        if (schoolMap.containsKey(courseName)) {
            double averageGrades = schoolMap.get(courseName).averageGrades();
            System.out.println("Average score for course '" + courseName + "': " + averageGrades);
        } else {
            System.out.println("Error: Course not added into the school.");
        }
    }

    public void cumulativeAverage(S student) {
        double cumulativeSum = 0;
        int courseEnrolledByStudent = 0;
        Collection<Course<S, G>> courseCollection = schoolMap.values();
            for (Course<S, G> course : courseCollection) {
                Collection<S> studentCollection = course.listUniqueAllStudent();

                if (studentCollection.contains(student)) {
                    courseEnrolledByStudent++;
                    if (course.getGrade(student).isPresent()) {
                        cumulativeSum += course.getGrade(student).get().doubleValue();
                    }
                }
            }
            double cumulativeAverage = cumulativeSum / courseEnrolledByStudent;
            System.out.println("Cumulative average score for student '" + student + "': " + cumulativeAverage);
        }





    public void processCommand(String userCommand) {
        String[] userCommandSplit = userCommand.split(" ");
        switch (userCommandSplit[0]) {
            case "add_course" -> {
                addCourse(userCommandSplit[1]);
            }
            case "list_courses", "report_unique_courses" -> {
                listCourse();
            }
            case "enroll_student" -> {
                enrollStudent(userCommandSplit[1], (S) userCommandSplit[2]);
            }
            case "assign_grade" -> {

                assignGrade(userCommandSplit[1], (S) userCommandSplit[2], (G) Double.valueOf(userCommandSplit[3]));
            }
            case "list_grades" -> {
                listGrade(userCommandSplit[1]);
            }
            case "report_unique_students" -> {
                listUniqueStudent();
            }
            case "report_average_score" -> {
                averageGrades(userCommandSplit[1]);
            }
            case "report_cumulative_average" -> {
                cumulativeAverage((S) userCommandSplit[1]);
            }
            case "unknown_command" -> {
                System.out.println("Error: Unknown command 'unknown_command'. " +
                        "Please use a valid command.");
            }

        }
    }

    public static void main(String[] args) {
        School<String, Double> school = new School<>();
        System.out.println("********************");
        school.addCourse("Java");
        System.out.println("+++++++++++++++++++");
        school.enrollStudent("Java", "Karan");
        System.out.println("================================");
        school.assignGrade("Java", "Karan", 95.02d);
        school.addCourse("Spring");
        school.enrollStudent("Spring", "Karan");
        school.addCourse("JAva");
        school.enrollStudent("JAva", "Gurpreet");
        school.listCourse();
        System.out.println("=================================");
        school.listGrade("Java");
        System.out.println("_____________________");
        school.listUniqueStudent();
    }


}

