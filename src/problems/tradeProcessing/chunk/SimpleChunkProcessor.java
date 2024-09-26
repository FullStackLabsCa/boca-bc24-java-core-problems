package problems.tradeProcessing.chunk;

import problems.tradeProcessing.interfaceFiles.ChunkProcessor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleChunkProcessor implements ChunkProcessor {

    private ExecutorService executorService;
    private Connection connection;

    public SimpleChunkProcessor(int numberOfChunks, Connection connection) {
        // Initialize a fixed thread pool based on the number of chunks
        this.executorService = Executors.newFixedThreadPool(numberOfChunks);
        this.connection = connection;
    }


    @Override
    public void processChunk(List<String> chunkFilePaths) {
        // Process each chunk file
        for (String chunkFilePath : chunkFilePaths) {
            executorService.submit(() -> {
                try {
                    processSingleChunk(chunkFilePath);
                } catch (Exception e) {
                    // Handle any exceptions that occur during processing
                    System.err.println("Error processing file " + chunkFilePath + ": " + e.getMessage());
                }
            });
        }
        // Shutdown the executor service
        executorService.shutdown();
    }

    private void processSingleChunk(String chunkFilePath) throws Exception {
        // Read the chunk file and process the data
        try (BufferedReader reader = new BufferedReader(new FileReader(chunkFilePath))) {
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                // Skip the header line
                if (lineNumber == 1) {
                    continue; // Skip header
                }

                // Validate and insert data into the database
                if (validateData(line)) {
                    insertIntoDatabase(line, "valid"); // Insert as valid
                } else {
                    insertIntoDatabase(line, "notvalid"); // Insert as not valid
                }

            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading chunk file: " + chunkFilePath, e);
        }
    }

    private boolean validateData(String line) throws Exception{

        // Split the line into fields (assuming comma-separated values)
        String[] fields = line.split(",");

        // Check the number of fields matches expected count (e.g., 7 fields)
        if (fields.length != 7) {
            System.out.println("Invalid number of fields in line: " + line);
            return false;
        }

        // Validate each field
        for (String field : fields) {
            if (field.trim().isEmpty()) {
                System.out.println("Missing value in field: " + field);
                return false;
            }
        }
        // If all validations pass, it's valid
//        System.out.println("Valid data: " + line);
        return true;
    }

    private void insertIntoDatabase(String validatedData, String status) throws Exception{

        // Split the validated data into fields (assuming the same CSV structure)
        String[] fields = validatedData.split(",");

        String trade_id = fields[0];
        String payload = validatedData; // Full CSV line as payload


        String insertQuery = "INSERT INTO trade_payloads (trade_id, status, payload) VALUES (?, ?, ?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)){
            preparedStatement.setString(1, trade_id);
            preparedStatement.setString(2, status);
            preparedStatement.setString(3, payload); // Full CSV line as payload

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new record was inserted successfully!");
            }
        } catch (SQLException e) {
            System.err.println("SQL Error while inserting data: " + e.getMessage());
            throw e; // Rethrow or handle as necessary
        }

        System.out.println("Inserting data: " + validatedData);
    }



}
