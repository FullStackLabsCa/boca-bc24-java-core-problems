package problems.trademultithreading.databasehelpers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static problems.trademultithreading.databasehelpers.DatabaseHelper.dataSource;

public class LoadSecurityToDB {
    public static void main(String[] args) {
        String csvFilePath = "/Users/Rushikumar.Patel/source/problems/src/problems/trademultithreading/csvfiles/securities_custom_reference.csv";
        String sql = "INSERT INTO securitiesReference (cusip, security_id) VALUES (?, ?)";
        int batchSize = 20;
        Connection connection = null;

        try {
            // Establish database connection
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);  //

            try (PreparedStatement statement = connection.prepareStatement(sql);
                 BufferedReader lineReader = new BufferedReader(new FileReader(csvFilePath))) {

                String lineText;
                int count = 0;

                // Skip the header line
                lineReader.readLine();

                // Read CSV file line by line
                while ((lineText = lineReader.readLine()) != null) {
                    String[] data = lineText.split(","); // Assuming CSV is comma-separated
                    String symbol = data[0].trim();
                    String securityId = data[1].trim(); // Assuming the second column is security_id

                    statement.setString(1, symbol);
                    statement.setString(2, securityId);
                    statement.addBatch();

                    if (++count % batchSize == 0) {
                        statement.executeBatch(); // Execute batch after every batchSize rows
                    }
                }
                // Execute remaining batches
                statement.executeBatch();
                // Commit the transaction
                connection.commit();
                System.out.println("Data has been inserted successfully.");
            } catch (IOException ex) {
                System.err.println("Error reading CSV file.");
                ex.printStackTrace();
            } catch (SQLException ex) {
                ex.printStackTrace();
                connection.rollback();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
