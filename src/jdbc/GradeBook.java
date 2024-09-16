package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

public class GradeBook {

    public List<Double> grades = new ArrayList();


    public void GradeBook(List<Double> grades) {
        this.grades = new ArrayList<>();;
    }


    public void addGrade(Double grade) {
        String query = "INSERT INTO Gradebook(grade) VALUES (?)";
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, String.valueOf(grade));
            stmt.executeUpdate();
            System.out.println( grade + " added.");
        } catch (SQLException e) {
            System.out.println("Error adding grade in gradebook: " + e.getMessage());
        }

//        grades.add(grade);
    }

    public int getNumberOfGrades() {
        return this.grades.size();

    }

    public String calculateAverage() {
        Double average = Double.MIN_VALUE;

        if (grades.isEmpty()){
            average=0.0;
        }

        if(!grades.isEmpty()){
           for ( Double grade : grades) {
                average +=  grade.doubleValue();
           }
           return "Average grade: " +String.format("%.1f", average / this.grades.size());
        }


       return "No grades available to calculate the average.";

    }


    public String findHighestGrade() {
        if (!grades.isEmpty()) {
            return "Highest grade: " + Collections.max(grades).toString();
        }

        return "No grades available to find the highest grade.";
    }


    public String findLowestGrade() {
        if (!grades.isEmpty()) {
            return "Lowest grade: " + Collections.min(grades).toString();
        }

        return "No grades available to find the lowest grade.";
    }
}