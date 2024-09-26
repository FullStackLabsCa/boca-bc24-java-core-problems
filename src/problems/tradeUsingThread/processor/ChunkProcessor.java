package problems.tradeUsingThread.processor;

import problems.tradeUsingThread.databaseConnection.HikariCP;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class ChunkProcessor implements Runnable{
    static String filePath;

    public ChunkProcessor(String filePath) {
        this.filePath= filePath;
    }

    @Override
    public void run() {
        try {
            System.out.println("Thread " + Thread.currentThread().getName() + " is processing file: " + filePath);


            readChunk();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void readChunk() throws FileNotFoundException {
        try (Scanner scanner= new Scanner(new FileReader(filePath))){

            int fieldCount= 7;
            boolean stat= true;

            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] fields = line.split(",");

                    String trade_id = fields[0];
                    boolean status = stat;
                    String payload = line;

                    if(fields.length!=fieldCount) {
                        status = false;
                    }
                insertTradePayloadData(trade_id, status, payload);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void insertTradePayloadData(String trade_id, Boolean status, String payload) throws Exception {
        String query= "INSERT INTO trade_payloads (trade_id, status, payload) VALUES (?, ?, ?)";
        try (Connection connection= HikariCP.getConnection()){
            PreparedStatement preparedStatement= connection.prepareStatement(query);
            preparedStatement.setString(1, trade_id);
            preparedStatement.setBoolean(2, status);
            preparedStatement.setString(3, payload);
            preparedStatement.executeUpdate();
        }
    }
}
