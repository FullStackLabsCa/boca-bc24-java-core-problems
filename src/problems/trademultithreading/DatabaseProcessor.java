package problems.trademultithreading;

import problems.trademultithreading.databasehelpers.DatabaseHelper;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.*;

import static problems.trademultithreading.databasehelpers.DatabaseHelper.dataSource;

public class DatabaseProcessor {
    private final ExecutorService chunkProcessorPool;
    private final ConcurrentHashMap<String, String> queueDistributorMap;
    private final ConcurrentHashMap<String, BlockingQueue<String>> tradeQueues; // Single Map for queues
    private String statusOfPayload;

    List<Future<?>> futures = new ArrayList<>();
    public DatabaseProcessor(int numberOfChunks, ConcurrentHashMap<String, String> queueDistributorMap) {
        this.chunkProcessorPool = Executors.newFixedThreadPool(numberOfChunks);
        this.queueDistributorMap = queueDistributorMap;
        this.tradeQueues = new ConcurrentHashMap<>();
        createQueues();
    }

    public void processChunks(String outputDir) throws IOException {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(outputDir), "*.csv")) {
            for (Path chunkFile : stream) {
                System.out.println("Processing chunk file: " + chunkFile);
                Future<?> future =  chunkProcessorPool.submit(() -> processChunk(chunkFile.toFile()));
                futures.add(future);
            }
            for (Future<?> future : futures) {
                try {
                    future.get();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            showSizeOfQueues();    // For checking the size of the Queue (Queue 1 to Queue 3)
            chunkProcessorPool.shutdown();

            try {
                chunkProcessorPool.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
                chunkProcessorPool.shutdownNow();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            // Step 3: Add data into journal_entry table
            startTradeProcessors();

            // Step 4: Processing further for the Position table
            // startInsertingPosition();

        } catch (IOException e) {
            throw new IOException("Failed to process chunk files: " + e.getMessage());
        }

    }


    private void processChunk(File chunkFile) {
        try {
            List<String> chunkData = Files.readAllLines(chunkFile.toPath());
            System.out.println("Processing " + chunkData.size() + " lines in chunk: " + chunkFile.getName());
            for (String tradeData : chunkData) {
                processTrade(tradeData);
            }
        } catch (IOException e) {
            System.out.println("Error reading chunk file: " + e.getMessage());
        }
    }

    private void processTrade(String tradeData) {
        String[] tradeFields = tradeData.split(",");
        System.out.println("Processing trade data: " + Arrays.toString(tradeFields)); // Log trade data

        if (tradeFields.length == 7) {
            statusOfPayload = "Valid";
        } else {
            statusOfPayload = "Invalid";
            System.out.println("Invalid trade data format. Expected 7 fields but found " + tradeFields.length);
        }
        String tradeId = tradeFields[0];
        String accountNumber = tradeFields[2];

        System.out.println("Status = " + statusOfPayload);

        String createdTimeStamp = getCurrentTimestamp();

        // Step 1: Insert into the database
        insertTradeIntoDatabase(tradeId, tradeData, statusOfPayload, createdTimeStamp);

        // Step 2: After successful insertion, assign trade to the appropriate queue
        assignToQueue(accountNumber, tradeId);
    }

    private void insertTradeIntoDatabase(String tradeId, String tradeData, String statusOfPayload, String createdTimeStamp) {
        System.out.println("Inserting trade into database with ID: " + tradeId);
          try (Connection conn = dataSource.getConnection()) {

            if (conn == null) {
                System.out.println("Database connection is null. Check your connection settings.");
                return;
            }

            String insertQuery = "INSERT IGNORE INTO trade_payload (trade_id, status, created_ts, updated_ts, payload) VALUES (?, ?, ?, ?, ?)";
            conn.setAutoCommit(false);
            try (PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {
                pstmt.setString(1, tradeId);
                pstmt.setString(2, statusOfPayload);
                pstmt.setString(3, createdTimeStamp);
                pstmt.setString(4, createdTimeStamp);
                pstmt.setString(5, tradeData);

                int rowsInserted = pstmt.executeUpdate();
                System.out.println("Rows inserted: " + rowsInserted);
                if (rowsInserted > 0) {
                    conn.commit();
                } else {
                    conn.rollback();
                }
            }
        } catch (SQLException e) {
            System.out.println("Error inserting trade into database: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void assignToQueue(String accountNumber, String tradeId) {
        String queueName = queueDistributorMap.computeIfAbsent(accountNumber, key -> getRandomQueue());
        enqueueTradeId(queueName, tradeId);
        System.out.println("Trade " + tradeId + " assigned to queue: " + queueName);
    }

    private void createQueues() {
        tradeQueues.put("queue1", new LinkedBlockingQueue<>());
        tradeQueues.put("queue2", new LinkedBlockingQueue<>());
        tradeQueues.put("queue3", new LinkedBlockingQueue<>());
    }

    private String getRandomQueue() {
        List<String> queues = Arrays.asList("queue1", "queue2", "queue3");
        return queues.get(new Random().nextInt(queues.size()));
    }

    private void enqueueTradeId(String queueName, String tradeId) {
        BlockingQueue<String> selectedQueue = tradeQueues.get(queueName);
        if (selectedQueue != null) {
            try {
                selectedQueue.put(tradeId);
                System.out.println("Trade " + tradeId + " added to " + queueName);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Failed to add trade " + tradeId + " to " + queueName);
            }
        } else {
            System.out.println("Queue " + queueName + " does not exist.");
        }
    }

    public void showSizeOfQueues() {
        tradeQueues.forEach((queueName, queue) -> System.out.println("Size of " + queueName + " = " + queue.size()));
    }

    public static String getCurrentTimestamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        return LocalDateTime.now().format(formatter);
    }

    // Implementing Step 3
    public void startTradeProcessors() {
        ExecutorService tradeProcessorPool = Executors.newFixedThreadPool(tradeQueues.size());
        for (Map.Entry<String, BlockingQueue<String>> entry : tradeQueues.entrySet()) {
            String queueName = entry.getKey();
            BlockingQueue<String> queueHoldingValue = entry.getValue();

            tradeProcessorPool.execute(new TradeProcessor(queueHoldingValue, queueName));
        }
        tradeProcessorPool.shutdown();
//        int numberOfThreads = tradeQueues.size();
//        System.out.println("Number of threads - "+numberOfThreads);

    }
}

