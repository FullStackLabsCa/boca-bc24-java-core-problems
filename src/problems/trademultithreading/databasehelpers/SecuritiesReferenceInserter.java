package problems.trademultithreading.databasehelpers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static problems.trademultithreading.databasehelpers.DatabaseHelper.dataSource;

public class SecuritiesReferenceInserter {
    public static void main(String[] args) {
        String csvFilePath = "/Users/Rushikumar.Patel/source/problems/src/problems/tradingplatform/csvfiles/services.csv";

        loadCsvDataIntoDatabase(csvFilePath);

    }



    public static void loadCsvDataIntoDatabase(String csvFilePath) {
        String insertQuery = "INSERT INTO SecuritiesReference (symbol, description) VALUES (?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
             BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {

            String line;
            boolean isHeader = true;

            connection.setAutoCommit(false);
            while ((line = br.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }

                String[] values = line.split(",");
                if (values.length == 2) {
                    String symbol = values[0].trim();
                    String description = values[1].trim();

                    // Set the parameters for the prepared statement
                    preparedStatement.setString(1, symbol);
                    preparedStatement.setString(2, description);

                    // Add to batch
                    preparedStatement.addBatch();
                }
            }

            int[] rowsAffected = preparedStatement.executeBatch();
            System.out.println("Inserted " + rowsAffected.length + " rows into the database.");

            connection.commit();

        } catch (IOException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
