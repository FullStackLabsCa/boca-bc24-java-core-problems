package problems.database_problems.gradebook_db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GradeBook {

    //adding Grades
    public void addGrades(Double grades) {
        String query = "INSERT INTO Gradebook (grades_added) VALUES (?)";
        try (Connection connection = DatabaseHelper.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDouble(1, grades);
            preparedStatement.executeUpdate();
            System.out.println("Grade '" + grades + "' added.");
        } catch (SQLException sqlException) {
            System.out.println("Error adding grades: " + sqlException.getMessage());
        }
    }
}