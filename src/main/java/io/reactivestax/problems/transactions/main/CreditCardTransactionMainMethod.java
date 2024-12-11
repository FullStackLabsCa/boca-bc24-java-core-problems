//package io.reactivestax.problems.transactions.main;
//
//import static io.reactivestax.problems.transactions.model.ModelLayer.readTransactionFileAndWriteToQueue;
//
//public class CreditCardTransactionMainMethod {
//    public static void main(String[] args) {
//        // Step 1: Configure HikariCP connection pool
//        configureHikariCP();
//
//        // Step 2: Read file and load transactions into ArrayBlockingQueue
//        readTransactionFileAndWriteToQueue("/Users/Shifa.Kajal/source/student/boca-bc24-java-core-io.reactivestax.problems/src/io.reactivestax.problems/transactions/utility/credit_card_transactions.txt");
//
//        startMultiThreadedProcessing();
//    }
//}
