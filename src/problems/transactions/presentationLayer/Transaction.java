//package problems.transactions.presentationLayer;
//
//import com.zaxxer.hikari.HikariConfig;
//import com.zaxxer.hikari.HikariDataSource;
//import problems.transactions.model.CreditCardTransaction;
//import problems.transactions.serviceLayer.TransactionConsumer;
//
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.LinkedBlockingDeque;
//
//import static problems.transactions.database.HikariDatabaseConnection.startMultiThreadedProcessing;
//import static problems.transactions.model.ModelLayer.readTransactionFileAndWriteToQueue;
//
//
//public class Transaction {
//     //Define a shared ArrayBlockingQueue
//    // made change here
//    public static LinkedBlockingDeque<CreditCardTransaction> creditCardTransactionQueue = new LinkedBlockingDeque<>(5000);
//
//    }
//
//
//
//
//}
