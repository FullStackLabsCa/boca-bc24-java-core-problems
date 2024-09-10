package generic_problems.school.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class School<S , G extends Number> {
    private String courseName;

    public void addCourse(String courseName) {
        String query = "INSERT INTO Courses (course_name) Values (?)";
        try (Connection con = DatabaseHelper.getConnection();
             PreparedStatement prepStat = con.prepareStatement(query)) {
            prepStat.setString(1, courseName);
            prepStat.executeUpdate();
            System.out.println("Course : " + courseName + " added.");
        } catch (SQLException e) {
            System.out.println("Error adding Course: " + e.getMessage());
        }
    }

    public void enrollStudent(String courseName, S student) {
        String courseQuery = "SELECT course_id FROM Courses WHERE course_name= ?";
        String studentQuery = "SELECT student_id FROM Students WHERE student_id= ?";
        String addStudentQuery = "INSERT INTO Students (student_id, student_name) VALUES (?, ?)";
        String enrollQuery = "INSERT INTO Enrollments (course_id, student_id) VALUES (?, ?)";
        try (Connection con = DatabaseHelper.getConnection();
             PreparedStatement courStat = con.prepareStatement(courseQuery);
             PreparedStatement stdStat = con.prepareStatement(studentQuery);
             PreparedStatement addStdStat = con.prepareStatement(addStudentQuery);
             PreparedStatement enrollStat = con.prepareStatement(enrollQuery)) {

            courStat.setString(1, courseName);
            ResultSet courseRes = courStat.executeQuery();
            if (!courseRes.next()) {
                System.out.println("Error: Course " + courseName + " does not exist.");
            }
            int courseId = courseRes.getInt("course_id");

            stdStat.setInt(1, Integer.parseInt(String.valueOf(student)));
            ResultSet studentRes = stdStat.executeQuery();

            if (!studentRes.next()) {
                addStdStat.setInt(1, Integer.parseInt(String.valueOf(student)));
                addStdStat.setString(2, String.valueOf(student));
                addStdStat.executeUpdate();
                System.out.println("Student: " + student + " added to the Student Table");
            }
            enrollStat.setInt(1, courseId);
            enrollStat.setInt(2, Integer.parseInt(String.valueOf(student)));
            enrollStat.executeUpdate();
            System.out.println("Student : " + student +  " enrolled.");
        } catch (SQLException e) {
            System.out.println("Error enrolling student: " + e.getMessage());
        }
    }


    public void assignGrade(String courseName, S student, G grade) {
        String courseQuery = "SELECT course_id FROM Courses WHERE course_name= ?";
        String gradeQuery = "INSERT INTO Grades (course_id, student_id, grade) VALUES (?,?,?)";
        try (Connection con = DatabaseHelper.getConnection();
             PreparedStatement courStat = con.prepareStatement(courseQuery);
             PreparedStatement gradStat = con.prepareStatement(gradeQuery)) {
            courStat.setString(1, courseName);
            ResultSet courRes = courStat.executeQuery();
            if (courRes.next()) {
                int courseId = courRes.getInt("course_id");

                gradStat.setInt(1, courseId);
                gradStat.setInt(2, Integer.parseInt(String.valueOf(student)));
                gradStat.setDouble(3, Double.parseDouble(String.valueOf(grade)));
                gradStat.executeUpdate();
                System.out.println("Grade: " + grade + " assign to student " + String.valueOf(student));
            }
        } catch (SQLException e) {
            System.out.println("Error while assign Grade " + e.getMessage());
        }
    }


    public void listOfCourses() {
        String courQuery = "SELECT * FROM Courses";
        try (Connection con = DatabaseHelper.getConnection();
             PreparedStatement courStat = con.prepareStatement(courQuery)){
            ResultSet corRes = courStat.executeQuery();
            List<Courses> coursesList =new ArrayList<>();
            while (corRes.next()){
                Courses courses = new Courses();
                courses.setCourse_id(corRes.getInt("course_id"));
                courses.setCourse_name(corRes.getString("course_name"));
                coursesList.add(courses);
            }
            System.out.print("Courses List: \n");
            for (Courses cou :coursesList){
                System.out.print(cou+"");
            }

        } catch (SQLException e) {
            System.out.println("Error While fetching Courses "+e.getMessage());
        }

    }


    public void listOfStudents() {
        String courQuery = "SELECT * FROM Students";
        try (Connection con = DatabaseHelper.getConnection();
             PreparedStatement stuStat = con.prepareStatement(courQuery)) {
            ResultSet stuRes = stuStat.executeQuery();
            List<Students> studentsList = new ArrayList<>();
            while (stuRes.next()) {
                Students students = new Students();
                students.setStudent_id(stuRes.getInt("student_id"));
                students.setStudent_name(stuRes.getString("student_name"));
                studentsList.add(students);
            }
            System.out.print("Students List: \n");
            for (Students stu :  studentsList) {
                System.out.print(stu + "");
            }

        } catch (SQLException e) {
            System.out.println("Error While fetching Students " + e.getMessage());
        }
    }


    public void averageScoreInCourse(String courseName) {
        double sum=0;
        double avg=0;
        int count=0;
        String courseQuery="SELECT course_id FROM Courses WHERE course_name= ?";
        String enrollmentQuery="SELECT student_id FROM Enrollments WHERE course_id= ?";
        String gradeQuery="SELECT grade FROM Grades WHERE student_id = ? AND course_id = ?;";
        String averageQuery="INSERT INTO Course_Averages (course_name, average ) VALUES (?,?)";
        try(Connection con = DatabaseHelper.getConnection();
            PreparedStatement courStat = con.prepareStatement(courseQuery);
            PreparedStatement gradStat = con.prepareStatement(gradeQuery);
            PreparedStatement enrollStat = con.prepareStatement(enrollmentQuery);
            PreparedStatement avgStat = con.prepareStatement(averageQuery)){

            courStat.setString(1,courseName);
            ResultSet courRes = courStat.executeQuery();
            courRes.next();
            int courseId = courRes.getInt("course_id");

            enrollStat.setInt(1,courseId);
            ResultSet enrollRes = enrollStat.executeQuery();
            boolean next = enrollRes.next();
            int studentId = enrollRes.getInt("student_id");

            while (next){
                gradStat.setInt(1,studentId);
                gradStat.setInt(2,courseId);
                ResultSet gradRes = gradStat.executeQuery();
                gradRes.next();
                double grade = gradRes.getDouble("grade");
                sum=sum+grade;
                count++;
                next=enrollRes.next();
            }
            avg=sum/count;

            avgStat.setString(1,courseName);
            avgStat.setDouble(2,avg);
            avgStat.executeUpdate();
            System.out.println("Average: "+courseName+" -> "+ avg);
        } catch (SQLException e) {
            System.out.println("Error While calculating Average in Course " + e.getMessage());
        }
    }

    public void studentAverageGrade(S student) {
        String query="Insert into Student_Averages (student_name,average)SELECT  Students.student_name,AVG(Grades.grade) as average\n" +
                "from Grades\n" +
                "join Students on Students.student_id=Grades.student_id\n" +
                "where Students.student_id=?;\n" ;
        String resQuery="Select * from Student_Averages";
        try (Connection con = DatabaseHelper.getConnection();
             PreparedStatement resStat = con.prepareStatement(resQuery);
             PreparedStatement queryStat = con.prepareStatement(query)) {

            queryStat.setInt(1,Integer.parseInt(String.valueOf(student)));
            queryStat.executeUpdate();
            ResultSet averageGrade = resStat.executeQuery();
            averageGrade.next();
            double average = averageGrade.getDouble("average");
            System.out.println("Average of "+ student +" -> "+average);

        } catch (SQLException e) {
            System.out.println("Error While calculating Average in Course " + e.getMessage());
        }
    }

    public void gradesPerCourse(String courseName) {
        String gradQuery = "SELECT * FROM Grades";
        try (Connection con = DatabaseHelper.getConnection();
             PreparedStatement gradeStat = con.prepareStatement(gradQuery)) {
            ResultSet grdRes = gradeStat.executeQuery();
            List<Grade> gradeList = new ArrayList<>();
            while (grdRes.next()) {
                Grade grade = new Grade();
                grade.setStudent_id(grdRes.getInt("student_id"));
                grade.setGrade(grdRes.getInt("grade"));
                gradeList.add(grade);
            }
            System.out.print("Grade List: \n");
            for (Grade grd :  gradeList) {
                System.out.print(grd + "");
            }

        } catch (SQLException e) {
            System.out.println("Error While fetching Grades " + e.getMessage());
        }
    }

    public void processCommand(String userCommand) {
        String[] spiltUserCommand = userCommand.split("\\s");
        switch (spiltUserCommand[0]) {
            case "add_course":
                addCourse(spiltUserCommand[1]);
                break;
            case "list_courses":
                listOfCourses();
                break;
            case "enroll_student":
                enrollStudent(spiltUserCommand[1], (S) spiltUserCommand[2]);
                break;
            case "assign_grade":
                assignGrade(spiltUserCommand[1], (S) spiltUserCommand[2], (G) Double.valueOf(spiltUserCommand[3]));
                break;
            case "list_grades":
                gradesPerCourse(spiltUserCommand[1]);
                break;
            case "report_unique_courses":
                listOfCourses();
                break;
            case "report_unique_students":
                listOfStudents();
                break;
            case "report_average_score":
                averageScoreInCourse(spiltUserCommand[1]);
                break;
            case "report_cumulative_average":
                studentAverageGrade((S) spiltUserCommand[1]);
                break;
            case "unknown_command":
                System.out.println("Error: Unknown command '" + spiltUserCommand[0] + "'. Please use a valid command.");
        }
    }

}

class Courses{
    private String course_name;
    private int course_id;

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    @Override
    public String toString() {
        return "course_name='" + course_name + '\'' +
                ", course_id=" + course_id +"\n";
    }
}

class Students{
    private String student_name;
    private int student_id;

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    @Override
    public String toString() {
        return "student_name='" + student_name + '\'' +
                ", student_id=" + student_id +"\n";
    }
}

class Grade{
    private int grade_id;
    private int course_id;
    private int student_id;
    private double grade;

    public void setGrade_id(int grade_id) {
        this.grade_id = grade_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "grade=" + grade +
                ", student_id=" + student_id +"\n";
    }
}