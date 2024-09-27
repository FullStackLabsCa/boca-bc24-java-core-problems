package problems.jdbc.tradeprocessor.service;

import problems.jdbc.tradeprocessor.repository.TradeRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ChunkProcessor implements Runnable {
    private final String filePath;

    public ChunkProcessor(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] transaction = line.split(",");
                TradeRepository tradeRepository = new TradeRepository();
                int queueNumber = QueueDistributor.getQueueNumber(transaction[2]);
                QueueDistributor.giveToQueue(transaction[0], queueNumber);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (IOException e) {
            System.out.println("File not found.");
        }
    }
}
