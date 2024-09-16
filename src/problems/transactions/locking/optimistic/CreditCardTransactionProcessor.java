package problems.transactions.locking.optimistic;

import com.zaxxer.hikari.HikariDataSource;
import problems.transactions.locking.optimistic.dbconnection.DBConnection;
import problems.transactions.locking.optimistic.pojo.CreditCardTransaction;
import problems.transactions.locking.optimistic.utilities.Utilities;

import java.util.concurrent.LinkedBlockingDeque;

public class CreditCardTransactionProcessor {

    // Define a shared ArrayBlockingQueue
    public static LinkedBlockingDeque<CreditCardTransaction> creditCardTransactionQueue = new LinkedBlockingDeque<>(5000);

    // HikariCP DataSource
    public static HikariDataSource dataSource;

    public static void main(String[] args) {
        // Step 1: Configure HikariCP connection pool
        DBConnection.configureHikariCP();

        // Step 2: Read file and load transactions into ArrayBlockingQueue
        Utilities.readTransactionFileAndWriteToQueue("/Users/Mohankumar.Chandrasekaran/source/java-examples/boca-core-java-examples/src/jdbc/transactions/locking/optimistic/resources/credit_card_transactions_1.txt");

        Utilities.startMultiThreadedProcessing();
    }

}
