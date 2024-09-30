package problems.creditcardtransaction.oopimplemented;

import problems.creditcardtransaction.oopimplemented.DataIO.AccountDAO;
import problems.creditcardtransaction.oopimplemented.config.HikariCPConfig;
import problems.creditcardtransaction.oopimplemented.model.CreditCardTransaction;
import problems.creditcardtransaction.oopimplemented.service.TransactionConsumerService;
import problems.creditcardtransaction.oopimplemented.service.TransactionService;
import problems.creditcardtransaction.oopimplemented.util.TransactionQueueUtil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainRunner {
    public static void main(String[] args) {
        // Step 1: Configure HikariCP
        HikariCPConfig.configureHikariCP();

        // Step 2: Load transactions into the queue
        readTransactionFileAndWriteToQueue("/Users/Rushikumar.Patel/source/files/credit_card_transactions.txt");

        // Step 3: Start multi-threaded processing
        startMultiThreadedProcessing();
    }

    private static void startMultiThreadedProcessing() {
        int numberOfThreads = 5;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        AccountDAO accountDAO = new AccountDAO();
        TransactionService transactionService = new TransactionService(accountDAO);

        for (int i = 0; i < numberOfThreads; i++) {
            executorService.submit(new TransactionConsumerService(HikariCPConfig.getDataSource(), transactionService));
        }

        executorService.shutdown();
    }

    private static void readTransactionFileAndWriteToQueue(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\|");
                CreditCardTransaction transaction = new CreditCardTransaction(data[0], data[1], data[2], Double.parseDouble(data[3]), Double.parseDouble(data[4]));
                TransactionQueueUtil.getQueue().put(transaction);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
