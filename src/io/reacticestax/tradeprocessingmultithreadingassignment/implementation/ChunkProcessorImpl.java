package io.reacticestax.tradeprocessingmultithreadingassignment.implementation;
import io.reacticestax.tradeprocessingmultithreadingassignment.projectinterfaces.ChunkProcessor;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Deque;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;

public class ChunkProcessorImpl implements ChunkProcessor, Runnable {
    protected final File chunkFile;
    public ChunkProcessorImpl( File chunkFile){
        this.chunkFile = chunkFile;
    }



    @Override
    public void processChunk(File chunkFile) throws IOException {
        File outputFile = new File(chunkFile.getParent(), "validated_" + chunkFile.getName());

        try (BufferedReader reader = new BufferedReader(new FileReader(chunkFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

            String line;
            // Write header with status
            writer.write("trade_id,account_num,activity,CUSIP,quantity,price,timestamp,status");
            writer.newLine();

            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(",");
                if (columns.length != 7) {
                    writer.write(line + ",invalid");
                    writer.newLine();
                    continue;
                }

                String status = validateTradeRecord(columns);
                writer.write(line + "," + status);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Error processing chunk: " + chunkFile.getName(), e);
        }
    }




    private String validateTradeRecord(String[] columns) {

        if ((columns[0]).isEmpty()) {
            return "invalid";
        }

        if (!isLong(columns[1])) {
            return "invalid";
        }

        if (columns[2].isEmpty()) {
            return "invalid";
        }

        if (columns[3].isEmpty()) {
            return "invalid";
        }

        if (!isInteger(columns[4])) {
            return "invalid";
        }

        if (!isDouble(columns[5])) {
            return "invalid";
        }

        if (!isValidTimestamp(columns[6])) {
            return "invalid";
        }
   //if all validations passed return valid
        return "valid";
    }

    private boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isLong(String str) {
        try {
            Long.parseLong(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isValidTimestamp(String timestamp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false); //prevents accepting date formats other than this
        try {
            Date date = dateFormat.parse(timestamp);
            return date != null;
        } catch (ParseException e) {
            return false;
        }
    }























@Override
public void insertToRawTableInDB(String trade_id, String status, String payload) throws IOException {

        try (BufferedReader reader = new BufferedReader(new FileReader(chunkFile))) {
            String line;
            while ((line = reader.readLine()) != null) {

                String[] columns = line.split(",");
                trade_id = String.valueOf(columns[0]);
                status = String.valueOf(columns[7]);
                payload = line;
            }
            //to Log the values before inserting
            System.out.println("Inserting into trade_payloads: tradeId=" + trade_id + ", status=" + status + ", payload=" + payload);

            String sql = "INSERT INTO trade_payloads (trade_id, status, payload) VALUES (?, ?, ?)";
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bootcamp", "root", "password123");
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setString(1, trade_id);
                pstmt.setString(2, status);
                pstmt.setString(3, payload);
                pstmt.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }


        }

    }

    @Override
    public Integer consultAccountToQueueMap(Long account_num, Integer queue_num) {
        ConcurrentHashMap<Long,Integer> AccountToQueueMap = new ConcurrentHashMap<>();
        if (AccountToQueueMap.contains(account_num)){
         return AccountToQueueMap.get(queue_num);
        }else
            AccountToQueueMap.put(account_num,queue_num);
           return queue_num += 1;
    }

    @Override
    public void insertToTradeQueue(int queue_num) {
        Deque<Integer> tradeQueue1 = new LinkedBlockingDeque<>();
        Deque<Integer> tradeQueue2 = new LinkedBlockingDeque<>();
        Deque<Integer> tradeQueue3 = new LinkedBlockingDeque<>();

        if (queue_num == 1){
           // tradeQueue1.push();


     }
    }


    @Override
    public void run() {
        try {
            processChunk(chunkFile);
            System.out.println("Processed chunk file: " + chunkFile.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}





