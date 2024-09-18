//package problems.transactions.main;
//
//import static problems.transactions.model.ModelLayer.readTransactionFileAndWriteToQueue;
//
//public class CreditCardTransactionMainMethod {
//    public static void main(String[] args) {
//        // Step 1: Configure HikariCP connection pool
//        configureHikariCP();
//
//        // Step 2: Read file and load transactions into ArrayBlockingQueue
//        readTransactionFileAndWriteToQueue("/Users/Shifa.Kajal/source/student/boca-bc24-java-core-problems/src/problems/transactions/utility/credit_card_transactions.txt");
//
//        startMultiThreadedProcessing();
//    }
//}
