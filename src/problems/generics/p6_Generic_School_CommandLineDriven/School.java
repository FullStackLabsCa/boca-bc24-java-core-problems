package problems.generics.p6_Generic_School_CommandLineDriven;

import java.util.*;

public class School<S, G extends Number> {

    public Map<S, Course<S, G>> studentWithCourse;

    public School() {
        studentWithCourse = new HashMap<>();
    }

    public Course<S, G> addCourse(S courseName) {
        System.out.println("Course '" + courseName + "' added.");
        return studentWithCourse.put(courseName, new Course<>());
    }

    public void enrollStudent(S courseName, S studentId) {
        if (!studentWithCourse.containsKey(courseName)) {
            System.out.println("Error: Course '" + courseName + "' does not exist. Can not enroll student");
            return;
        }
        studentWithCourse.get(courseName).enrollStudent(studentId);
        System.out.println("Student '" + studentId + "' enrolled in course '" + courseName + "'.");
    }

//    public Course<S, G> assignGrade(S courseName, S studentId, G grade) {
//        Collection<Course<S, G>> values = studentWithCourse.values();
//        values.forEach((k) -> {
//            String s = k.toString().split("=")[0];
//            String checkElement = s.substring(1);
//            if (!checkElement.equals(studentId)) {
//                System.out.println("Error: Can not assign grade. Student '" + studentId + "' is not enrolled in course '" + courseName + "'.");
//            }
//        });
//        if (studentWithCourse.containsKey(courseName)) {
//            studentWithCourse.get(courseName).assignGrade(studentId, grade);
//        }
//        System.out.println("Grade '" + grade + "' assigned to student '" + studentId + "' in course '" + courseName + "'.");
//        return this.studentWithCourse.get(courseName);
//    }

    public Course<S, G> assignGrade(S courseName, S studentId, G grade) {
        Course<S, G> course = studentWithCourse.get(courseName);

        if (course == null) {
            System.out.println("Error: Course '" + courseName + "' does not exist.");
            return null;
        }

        boolean isEnrolled = course.isStudentEnrolled(studentId);

        if (!isEnrolled) {
            System.out.println("Error: Can not assign grade. Student '" + studentId + "' is not enrolled in course '" + courseName + "'.");
            return null;
        }

        course.assignGrade(studentId, grade);
        System.out.println("Grade '" + grade + "' assigned to student '" + studentId + "' in course '" + courseName + "'.");
        return course;
    }


    public void listGrade(S courseName) {
        Course<S, G> sgCourse = studentWithCourse.get(courseName);
        sgCourse.allStudentWithGrade();
    }

    public void listAllCourse() {
        Set<S> s = studentWithCourse.keySet();
        System.out.println("Courses offered:");
        s.forEach(System.out::println);
    }

    public void listUniqueStudents() {
        System.out.println("Unique students enrolled:");
        Collection<Course<S, G>> values = studentWithCourse.values();
        values.forEach((k) -> {
            String s = k.toString().split("=")[0];
            String subString = s.substring(1);
            System.out.println(subString);
        });
    }

//    public double reportAverageScore(S courseName) {
//        double sumOfGrades = 0;
//        Course<S, G> sgCourse = studentWithCourse.get(courseName);
//        List<G> grades = sgCourse.listAllGrades();
//        for (G grade : grades) {
//            sumOfGrades = Double.sum(sumOfGrades, (Double) grade);
//        }
//        Double average = Double.valueOf(sumOfGrades / (double) grades.size());
//        System.out.println("Average score of course '" + courseName + "': " + average);
//        return average;
//    }

    public double reportAverageScore(S courseName) {
        double sumOfGrades = 0;
        Course<S, G> sgCourse = studentWithCourse.get(courseName);
        if (sgCourse == null) {
            System.err.println("Course not found.");
            return 0;
        }

        List<G> grades = sgCourse.listAllGrades();
        if (grades == null || grades.isEmpty()) {
            System.err.println("No grades available for this course.");
            return 0;
        }

        int count = 0;
        for (G grade : grades) {
            if (grade != null && grade instanceof Double) {
                sumOfGrades += (Double) grade;
                count++;
            } else {
                System.err.println("Invalid grade found: " + grade);
            }
        }

        if (count == 0) {
            System.err.println("No valid grades found for the course.");
            return 0;
        }

        double average = sumOfGrades / count;
        System.out.println("Average score of course '" + courseName + "': " + average);
        return average;
    }


    public double reportCulcumativeAverage(S studentId) {
        Collection<Course<S, G>> values = studentWithCourse.values();
        double sum = 0.0;
        double average = 0.0;

        for (Course<S, G> k : values) {
            String s = k.toString().split("=")[0];
            String checkElement = s.substring(1);
            String original = k.toString().split("=")[1];
            double grade = Double.parseDouble(original.substring(0, original.length() - 1));
            if (checkElement.equals(studentId)) {
                sum = Double.sum(sum, grade);
            }
            average = sum / Double.valueOf(values.size());
        }
        System.out.println("Cumulative average score for student '" + studentId + "': " + average);
        return average;
    }

    public static void main(String[] args) {
        School<String, Double> school = new School<>();

        while (true) {
            System.out.println("Enter the option number: " + "\n" +
                    "1. add_course" + "\n" +
                    "2. enroll_student" + "\n" +
                    "3. assign_grade " + "\n" +
                    "4. list_grades " + "\n" +
                    "4. list_courses " + "\n" +
                    "6. report_unique_courses " + "\n" +
                    "7. report_unique_students " + "\n" +
                    "8. report_average_score" + "\n" +
                    "9. report_cumulative_average " + "\n" +
                    "10. exit - Exit the program");

            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("10")) {
                System.out.println("Exiting from School Management System");
                break;
            }
            school.processCommand(userInput);
        }

    }

    public void processCommand(String inputCommand) {

        String[] splitInput = {};
        if (inputCommand.contains(" ")) {
            splitInput = inputCommand.split(" ");
        }

        switch (inputCommand.contains(" ") ? splitInput[0] : inputCommand) {

            case "add_course":
                if (splitInput.length < 2) {
                    System.out.println("Error: Missing course name for 'add_course' command.");
                    return;
                }
                addCourse((S) splitInput[1]);
                break;
            case "enroll_student":
                if (splitInput.length < 3) {
                    System.out.println("Error: Missing course name or student ID for 'enroll_student' command.");
                    return;
                }
                enrollStudent((S) splitInput[1], (S) splitInput[2]);
                break;
            case "assign_grade ":
                if (splitInput.length < 4) {
                    System.out.println("Error: Missing course name, student ID, or grade for 'assign_grade' command.");
                    return;
                }
                assignGrade((S) splitInput[1], (S) splitInput[2], (G) Double.valueOf(splitInput[3]));
                break;
            case "list_grades ":
                if (splitInput.length < 2) {
                    System.out.println("Error: Missing course name for 'list_grades' command.");
                    return;
                }
                listGrade((S) splitInput[1]);
                break;
            case "list_courses ":
            case "report_unique_courses ":
                listAllCourse();
                break;
            case "report_unique_students ":
                listUniqueStudents();
                break;
            case "report_average_score":
                if (splitInput.length < 2) {
                    System.out.println("Error: Missing course name for 'report_average_score' command.");
                    return;
                }
                reportAverageScore((S) splitInput[1]);
                break;
            case "report_cumulative_average ":
                if (splitInput.length < 2) {
                    System.out.println("Error: Missing student ID for 'report_cumulative_average' command.");
                    return;
                }
                reportCulcumativeAverage((S) splitInput[1]);
                break;
            default:
                System.out.println("Error: Unknown command 'unknown_command'. Please use a valid command.");
                break;


        }

    }

}
