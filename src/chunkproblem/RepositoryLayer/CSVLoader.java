package chunkproblem.RepositoryLayer;

import chunkproblem.ServiceLayer.DataValidation;
import chunkproblem.UserDefinedExceptions.DatabaseInsertionException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CSVLoader {
    public static void loadCSVData(String chunkFilePath, Connection connection) {
        String insertSQL = "INSERT INTO trade_payloads_table (trade_id, status, payload) VALUES (?, ?, ?)";
        try (BufferedReader reader = new BufferedReader(new FileReader(chunkFilePath));
             PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(","); // Adjust based on your CSV format

                if (DataValidation.isValid(values)) {
                    preparedStatement.setInt(1, Integer.parseInt(values[0].trim()));
                    preparedStatement.setString(2, values[1].trim());
                    preparedStatement.setString(3, values[2].trim());
                    preparedStatement.executeUpdate();
                } else {
                    throw new DatabaseInsertionException("Invalid data can't be inserted :"+line);
                }
            }
            System.out.println("Data loaded successfully from " + chunkFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
