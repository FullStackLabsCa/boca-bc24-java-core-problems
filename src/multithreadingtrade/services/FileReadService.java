package multithreadingtrade.services;

import com.zaxxer.hikari.HikariDataSource;

import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FileReadService {
    public static void readFile() throws SQLException {
        int numberOfThreads = 10;
        String filePath = "";
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        for (int i = 0; i < numberOfThreads; i++) {
            if (!filePath.isEmpty()) {
                executorService.submit(new ChunkProcessor(filePath));
            }
        }
    }
}
