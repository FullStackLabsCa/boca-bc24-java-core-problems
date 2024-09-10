package problems.generics;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class GradeBookJdbc<T extends Number & Comparable<T>> {

    private final List<T> grades;

    public GradeBookJdbc() {
        this.grades = new ArrayList<>();
    }

    public void addGrade(T grade) {
        String sql = "Insert into Grades values (" + grade + ")";
        try (Connection conn = DatabaseHelper.getConnection("gradebook");
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.execute();
        } catch (SQLException e) {
            System.out.println("Error inserting into grades table: " + e.getMessage());
        }
        grades.add(grade);
    }

    public String calculateAverage() throws SQLException {
        double average = Double.MIN_VALUE;
        String sql = "Select * from Grades";
        try (Connection conn = DatabaseHelper.getConnection("gradebook");
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                
            }
        } catch (SQLException e) {
            System.out.println("Error selecting grades from: " + e.getMessage());
        }
        if (!grades.isEmpty()) {
            average = 0;
            for (T grade : grades) {
                average = average + grade.doubleValue();
            }

            return "Average grade: " + String.format("%.1f", average / this.grades.size());
        }

        return "No grades available to calculate the average.";
    }

    public String findHighestGrade() {
        if (!grades.isEmpty()) {
            return "Highest grade: " + Collections.max(grades).toString();
        }

        return "No grades available to find the highest grade.";
    }

    public int getNumberOfGrades() {
        return this.grades.size();
    }

    public String findLowestGrade() {
        if (!grades.isEmpty()) {
            return "Lowest grade: " + Collections.min(grades).toString();
        }

        return "No grades available to find the lowest grade.";
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        GradeBookJdbc<Integer> grades = new GradeBookJdbc<>();

        System.out.println("Please enter grades of 10 students: ");
        for (int i = 0; i < 10; i++) {
            grades.addGrade(s.nextInt());
        }

//        System.out.println(grades.calculateAverage());
//        System.out.println(grades.findHighestGrade());
//        System.out.println(grades.findLowestGrade());
    }
}
