package problems.creditcard;

import com.zaxxer.hikari.HikariDataSource;
import problems.creditcard.config.HikariCPConfig;
import problems.creditcard.entity.CreditCardTransaction;
import problems.creditcard.service.TransactionService;
import problems.creditcard.util.TransactionFileReader;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CreditCardProcessor {

    private static HikariDataSource dataSource;
    private static String filePath ="/Users/AnilKumar.Mummadisetti/source/boca-bc24-java-core-problems/transactions.txt";
    private static BlockingDeque<CreditCardTransaction> creditCardTransactionQueue;

    public static void main(String[] args) {

        try {
            dataSource = HikariCPConfig.getDataSource();
            creditCardTransactionQueue = TransactionFileReader.readTransactionFileAndWriteToQueue(filePath);
            startMultiThreadedProcessing();
            System.out.println("Threds fifnished"+creditCardTransactionQueue);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private static void startMultiThreadedProcessing() {
        try{
            int numberOfThreads = 5;
            ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
            for (int i = 0; i < numberOfThreads; i++) {
                executorService.submit(new TransactionService(creditCardTransactionQueue, dataSource));
            }
            executorService.shutdown();
           /* try {
                if (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
                    executorService.shutdownNow(); // Force shutdown if not terminated
                }
            } catch (InterruptedException e) {
                executorService.shutdownNow(); // Ensure shutdown if interrupted
                Thread.currentThread().interrupt(); // Restore interrupted status
            }*/
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
