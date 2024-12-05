//package problems.multiThreadedTradeProcessor;
//
//
//import com.zaxxer.hikari.HikariDataSource;
//
//import java.io.IOException;
//
//import static problems.multiThreadedTradeProcessor.FileReader.generateChunksAndSubmitTask;
//
//public class TradeThreadMain {
//
//        public static HikariDataSource dataSource;
//
//        public static void main(String[] args) throws IOException, InterruptedException {
//
//            HikariDataSource dataSource = DatabaseConnector.configureHikariCP();
//            generateChunksAndSubmitTask(dataSource);
//
//        }
//    }
//
