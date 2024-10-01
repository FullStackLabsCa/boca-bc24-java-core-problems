//package school_jdbc;
//
//import school_jdbc.course.Course;
//import dbconnection.DatabseConnectionPool;
//import school_jdbc.enrollment.Enrollment;
//import school_jdbc.grade.Grade;
//import school_jdbc.report.ReportCard;
//import school_jdbc.student.Student;
//
//import java.sql.Connection;
//import java.util.Scanner;
//
//public class MainRunner {
//    public static void main(String[] args) throws Exception {
//        Course courseObj = new Course();
//        Student studentObj = new Student();
//        Enrollment enrollmentObj = new Enrollment();
//        Grade gradeObj = new Grade();
//        ReportCard reopCardObj = new ReportCard();
//
//        Connection con = DatabseConnectionPool.getConnection();
//
//
//        Scanner sc = new Scanner(System.in);
//        boolean flag = true;
//        System.out.println("<<<<<<<<WELCOME TO SCHOOL>>>>>>>>>>>>>>>>>");
//        while (flag) {
//            System.out.print("""
//                Please Select Your Option:\s
//                1. Course\s
//                2. Student\s
//                3. Enrollment\s
//                4. Grade\s
//                5. Report card\s
//                6. Exit\s
//                """);
//        int option = sc.nextInt();
//        sc.nextLine();
//            switch (option) {
//                case 1:
//                    System.out.println("""
//                            ------WELCOME TO COURSE-----\s
//                            1. Add Course with add_course coursename\s
//                            2. Romove Course with remove_course coursename\s
//                            3. Update Course with update_course newcoursename oldcoursename\s
//                            4. Display all courses with get_courses\s
//                            """);
//                    break;
//                case 2:
//                    System.out.println("""
//                            ------WELCOME TO STUDENT-----\s
//                            1. Add Student with add_student studentname\s
//                            2. Romove Student with remove_student studentname\s
//                            3. Update Student with update_student newstudentname oldstudentname\s
//                            4. Display all students with get_student\s
//                            """);
//                    break;
//                case 3:
//                    System.out.println("""
//                            ------WELCOME TO ENROLLMENT-----\s
//                            1. Enroll Student with enroll_student coursename studentname\s
//                            2. Unroll Student with unroll_student enrollmentid\s
//                            """);
//                    break;
//                case 4:
//                    System.out.println("""
//                            ------WELCOME TO GRADE-----\s
//                            1. Assign Grade of Student to course with assign_grade coursename studentname grade\s
//                            2. Remove Grade of student with remove_grade gradeId\s
//                            """);
//                    break;
//                case 5:
//                    System.out.println("""
//                            ------WELCOME TO REPORT CARD-----\s
//                            1. Display Report Card with report_card studentname\s
//                            """);
//                    break;
//
//            }
//            System.out.println("Enter user intention");
//            String input = sc.nextLine();
//            String[] inputArray = input.split(" ");
//
//            switch (inputArray[0]) {
//                case "add_course":
//                    courseObj.addCourse(inputArray[1]);
//                    break;
//                case "remove_course":
//                    courseObj.removeCourse(inputArray[1]);
//                    break;
//                case "update_course":
//                    courseObj.updateCourse(inputArray[1], inputArray[2]);
//                    break;
//                case "get_course":
//                    courseObj.displayCourse();
//                    break;
//                case "add_student":
//                    studentObj.addStudent(inputArray[1]);
//                    break;
//                case "remove_student":
//                    studentObj.removeStudent(inputArray[1]);
//                    break;
//                case "update_student":
//                    studentObj.updateStudent(inputArray[1], inputArray[2]);
//                    break;
//                case "get_students":
//                    studentObj.displayStudent();
//                case "enroll_student":
//                    enrollmentObj.enrollStudent(inputArray[1], inputArray[2]);
//                    break;
//                case "unroll_student":
//                    enrollmentObj.removeEnrollmentOfStudent(inputArray[1]);
//                    break;
//                case "assign_grade":
//                    gradeObj.assignGradeToStudent(inputArray[1], inputArray[2], Integer.parseInt((inputArray[3])));
//                    break;
//                case "remove_grade":
//                    gradeObj.removeGrade(inputArray[1]);
//                    break;
//                case "report_card":
//                    reopCardObj.displayReportCard(inputArray[1]);
//                    break;
//                default:
//                    throw new IllegalStateException("Unexpected value: " + inputArray[0]);
//            }
//
////       Course courseObj = new Course();
////       courseObj.addCourse("Math101");
////       courseObj.addCourse("Science101");
////       courseObj.addCourse("Economics101");
////       courseObj.removeCourse("Economics101");
////       courseObj.updateCourse("Math201","Math101");
////       courseObj.displayCourse();
////
////        Student studentObj = new Student();
////        studentObj.addStudent("Mann");
////        studentObj.addStudent("Abhay");
////        studentObj.addStudent("Dhruv");
////        studentObj.addStudent("Harshil");
////        studentObj.removeStudent("Harshil");
////        studentObj.updateStudent("Dhruv Desai","Dhruv");
////        studentObj.displayStudent();
//
////        Enrollment enrollmentObj = new Enrollment();
////        enrollmentObj.EnrollStudent("Math201","Mann");
////        enrollmentObj.EnrollStudent("Math201","Abhay");
////        enrollmentObj.EnrollStudent("Math201","Dhruv Desai");
////
////        enrollmentObj.EnrollStudent("Science101","Mann");
////        enrollmentObj.EnrollStudent("Science101","Abhay");
////        enrollmentObj.EnrollStudent("Science101","Dhruv Desai");
////
////        enrollmentObj.removeEnrollmentOfStudent("4");
////        enrollmentObj.removeEnrollmentOfStudent("5");
////        enrollmentObj.removeEnrollmentOfStudent("6");
//
////        Grade gradeObj = new Grade();
////        gradeObj.assignGradeToStudent("Math201","Mann",98);
////        gradeObj.assignGradeToStudent("Math201","Abhay",95);
////        gradeObj.assignGradeToStudent("Math201","Dhruv Desai",99);
////        gradeObj.assignGradeToStudent("Science101","Mann",90);
////        gradeObj.assignGradeToStudent("Science101","Abhay",96);
////        gradeObj.assignGradeToStudent("Science101","Dhruv Desai",97);
//
//            //       gradeObj.updeGradeOfStudent("Science101","Mann",95);
//
////        ReportCard reopCardObj = new ReportCard();
////        reopCardObj.displayReportCard("Mann");
//
//        }
//    }
//}