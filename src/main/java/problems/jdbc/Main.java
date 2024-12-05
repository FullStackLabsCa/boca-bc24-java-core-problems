//package problems.jdbc;
//
//import static problems.jdbc.CreditCardTransactionProcessor.readTransactionFileAndWriteToQueue;
//import static problems.transactions.presentationLayer.OptimisticLocking.configureHikariCP;
//import static problems.transactions.presentationLayer.OptimisticLocking.startMultiThreadedProcessing;
//
//public class Main {
//    public static void main(String[] args) {
//        // Step 1: Configure HikariCP connection pool
//        configureHikariCP();
//
//        // Step 2: Read file and load transactions into ArrayBlockingQueue
//        readTransactionFileAndWriteToQueue("/Users/Shifa.Kajal/source/student/boca-bc24-java-core-problems/src/problems/transactions/utility/credit_card_transactions.txt");
//        startMultiThreadedProcessing();
//    }
//}
