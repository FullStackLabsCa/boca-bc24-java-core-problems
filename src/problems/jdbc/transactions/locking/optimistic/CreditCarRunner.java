package problems.jdbc.transactions.locking.optimistic;

import static practice.jdbc.transactions.locking.optimistic.CreditCardTransactionHikariPool.configureHikariCP;
import static practice.jdbc.transactions.locking.optimistic.CreditCardTransactionProcessor.startFileReadingThread;
import static practice.jdbc.transactions.locking.optimistic.CreditCardTransactionProcessor.startMultiThreadedProcessing;

public class CreditCarRunner {
    public static void main(String[] args) {
        // Step 1: Configure HikariCP connection pool
        configureHikariCP();

        // Step 2: Read file and load transactions into BlockingDeque
        startFileReadingThread("/Users/Karan.Rana/source/student/boca-bc24-java-core-problems/credit_card_transactions.txt");

        // Step 3: Start multi-threaded processing
        startMultiThreadedProcessing();

        //step 4:

    }
}
