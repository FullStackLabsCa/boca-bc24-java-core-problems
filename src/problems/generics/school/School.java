package problems.generics.school;

import java.util.*;


//    System.out.println("School program started");
//    School<String, Double> school = new School<>();
//    do{
//        System.out.println("enter next command");
//        Scanner scanner = new Scanner(System.in);
//        String command = scanner.nextLine();
//        String[] operation = command.split(" ");
//        school.processCommand(operation);
//    }while(true);



    public class School<S, G extends Number> {
        Map<String, Course<S, G>> courseToGradeMap = new HashMap<>();

        public void addCourse(String courseName) {
            if (!courseToGradeMap.containsKey((courseName))) {
                System.out.println("Course " + courseName + " added.");
                Course<S, G> course = new Course<>();
                courseToGradeMap.put(courseName, course);
            } else {
                System.out.println("Error: Course " + courseName + " already exists.");
            }
        }

        public void enrollStudent(String courseName, S studentId) {
            if (courseToGradeMap != null && courseToGradeMap.containsKey(courseName)) {
                courseToGradeMap.get(courseName).enrollStudent(studentId);
                System.out.println("Student '" + studentId + "' enrolled in course '" + courseName + "'.");
            } else {
                System.out.println("Error: Cannot enroll student. Course '" + courseName + "' does not exist.");
            }
        }


        public void assignGrade(String courseName, S studentID, G grade) {
//            CourseToGradeMap.get(courseName);
            Course<S, G> course = courseToGradeMap.get(courseName);
            if (course != null) {
                course.assignGrade(studentID, grade);
                System.out.println("Grade '" + grade + "' assigned to student '" + studentID + "' in course '" + courseName + "'.");
            } else {
                System.out.println("Error: Cannot assign grade. Course '" + courseName + "' does not exist.");
            }
        }

        public void listGrade(String courseName) {
//            courseToGradeMap.get(new Course<>());
            Course<S, G> course = courseToGradeMap.get(courseName);

            if (course != null) {
                System.out.println("Grades for course '" + courseName + "':");
                for (Map.Entry<S, G> entry : course.getAllGrades().entrySet()) {
                    System.out.println("Student: " + entry.getKey() + " - Grade: " + entry.getValue());
                }
            } else {
                System.out.println("Error: Course '" + courseName + "' does not exist.");
            }
        }

        public void listCourses() {
//            CourseToGradeMap.get(new Course<>());
            System.out.println("Courses offered:");
            for (String courseName : courseToGradeMap.keySet()) {
                System.out.println(courseName);
            }
        }

        public void reportUniqueCourses() {
            System.out.println("Unique courses offered:");
            listCourses();
        }

        public void reportUniqueStudents() {
            Set<S> uniqueStudents = new HashSet<>();
            for (Course<S, G> course : courseToGradeMap.values()) {
                uniqueStudents.addAll(course.getAllGrades().keySet());
            }
            System.out.println("Unique students enrolled:");
            for (S studentId : uniqueStudents) {
                System.out.println(studentId);
            }
        }

        public void reportAverageScore(String courseName) {
            Course<S, G> course = courseToGradeMap.get(courseName);
            if (course != null) {
                Collection<G> values = course.studentToGradesMap.values();
                double total = 0;
                for (G value: values){
                    total = total + value.doubleValue();
                }
                double average = total/values.size();
                System.out.println("Average score for course '" + courseName + "': " + average);
            } else {
                System.out.println("Error: Course '" + courseName + "' does not exist.");
            }
        }

        public void reportCumulativeAverage(S studentId) {
            double sum = 0.0;
            int count = 0;
            for (Course<S, G> course : courseToGradeMap.values()) {
                G grade = (G) course.getAllGrades();
                if (grade != null && grade instanceof Number) {
                    sum += ((Number) grade).doubleValue();
                    count++;
                }
            }
        }

        public void processCommand(String input){


                //input = "Mandeep";

                String[] op = input.split(" ");
                switch (op[0]) {
                    case "add_course": {
                        addCourse(op[1]);
                        break;
                    }
                    case "enroll_student": {
                        enrollStudent(op[1], (S) op[2]);
                        break;
                    }
                    case "assign_grade": {
                        assignGrade(op[1], (S) op[2], (G) Double.valueOf(op[3]));
                        break;
                    }
                    case "list_grades": {
                        listGrade(op[1]);
                        break;
                    }
                    case "report_unique_course": {
                        reportUniqueCourses();
                        break;
                    }
                    case "report_unique_students": {
                        reportUniqueStudents();
                        break;
                    }
                    case "report_average_score": {
                        reportAverageScore(op[1]);
                        break;
                    }

                }
            }
        }

