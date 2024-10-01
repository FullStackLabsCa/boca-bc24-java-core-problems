package school_jdbc.report;

import dbconnection.DatabaseHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportCard {

    Connection conn;
    public ReportCard() throws SQLException{
        this.conn = DatabaseHelper.getConnection();
    }

    public void  displayReportCard(String studentName) {
        String findGradesQuerry = "SELECT * From Grades "
                +"WHERE student_id = (SELECT student_id FROM Students WHERE student_name = ?)";
        String courseQuerry = "SELECT * From Courses ";
//                +"WHERE student_id = (SELECT student_id FROM Students WHERE student_name = ?)";
        System.out.println("Report Card: ");
        System.out.println("Report Card For => "+studentName);
        try(PreparedStatement findGradeQuerrtStmt = conn.prepareStatement(findGradesQuerry);
            PreparedStatement courseQuerryStmt = conn.prepareStatement(courseQuerry)){
            findGradeQuerrtStmt.setString(1,studentName);
            ResultSet gradeRs = findGradeQuerrtStmt.executeQuery();
            ResultSet courseRs = courseQuerryStmt.executeQuery();
            double totalGrades = 0;
            int count = 0;
            double percentage = 0;
                while(gradeRs.next() && courseRs.next()) {
                    //String studentName = rs.getString("student_name");
                    String courseName = courseRs.getString("course_name");
                    double grade = gradeRs.getDouble("grade");
                    totalGrades += grade;
                    count ++;
                    System.out.println("  ("+count+") "+courseName + " => " + grade);
                    percentage = totalGrades/count;

                }
            System.out.println("  Total Grade => "+totalGrades+"/"+count*100);
            System.out.println("  Percentage => "+percentage+"%");
        }catch(SQLException e){
            System.out.println("Error Updating Course: "+ e.getMessage());
        }
    }

}
