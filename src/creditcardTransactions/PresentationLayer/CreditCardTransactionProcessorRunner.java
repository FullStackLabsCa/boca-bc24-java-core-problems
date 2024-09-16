package creditcardTransactions.PresentationLayer;
import com.zaxxer.hikari.HikariDataSource;
import creditcardTransactions.Model.CreditCardTransaction;
import java.util.concurrent.LinkedBlockingDeque;
import static creditcardTransactions.ServiceLayer.CreditCardService.readTransactionFileAndWriteToQueue;
import static creditcardTransactions.ServiceLayer.CreditCardService.startMultiThreadedProcessing;
import static creditcardTransactions.databaseConnection.DatabaseConnectivity.configureHikariCP;

public class CreditCardTransactionProcessorRunner {
    public static LinkedBlockingDeque<CreditCardTransaction> creditCardTransactionQueue =  new LinkedBlockingDeque<>(5000);
    // HikariCP DataSource
   public static HikariDataSource dataSource;
    public static void main(String[] args) {
        // Step 1: Configure HikariCP connection pool
        configureHikariCP();
        // Step 2: Read file and load transactions into ArrayBlockingQueue
        readTransactionFileAndWriteToQueue("/Users/Manpreet.Kaur/Source/fullstacklabs/student-codebase/boca-bc24-java-core-problems/src/creditcardTransactions/Utility/credit_card_transactions.txt");
        startMultiThreadedProcessing();
    }
}
