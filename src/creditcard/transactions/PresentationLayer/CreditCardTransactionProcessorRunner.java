package creditcard.transactions.PresentationLayer;
import com.zaxxer.hikari.HikariDataSource;
import creditcard.transactions.Model.CreditCardTransaction;
import java.util.concurrent.LinkedBlockingDeque;
import static creditcard.transactions.ServiceLayer.CreditCardService.readTransactionFileAndWriteToQueue;
import static creditcard.transactions.ServiceLayer.CreditCardService.startMultiThreadedProcessing;
import static creditcard.transactions.databaseConnection.DatabaseConnectivity.configureHikariCP;

public class CreditCardTransactionProcessorRunner {
    public static LinkedBlockingDeque<CreditCardTransaction> creditCardTransactionQueue =  new LinkedBlockingDeque<>(5000);
    // HikariCP DataSource
   public static HikariDataSource dataSource;
    public static void main(String[] args) {
        configureHikariCP();
        // Step 2: Read file and load transactions into ArrayBlockingQueue
        readTransactionFileAndWriteToQueue("/Users/Manpreet.Kaur/Source/fullstacklabs/student-codebase/boca-bc24-java-core-problems/src/creditcardTransactions/Utility/credit_card_transactions.txt");
        startMultiThreadedProcessing();
    }
}
