//package io.reactivestax.problems.io.reactivestax.jdbc;
//
//import static io.reactivestax.problems.io.reactivestax.jdbc.CreditCardTransactionProcessor.readTransactionFileAndWriteToQueue;
//import static io.reactivestax.problems.transactions.presentationLayer.OptimisticLocking.configureHikariCP;
//import static io.reactivestax.problems.transactions.presentationLayer.OptimisticLocking.startMultiThreadedProcessing;
//
//public class Main {
//    public static void main(String[] args) {
//        // Step 1: Configure HikariCP connection pool
//        configureHikariCP();
//
//        // Step 2: Read file and load transactions into ArrayBlockingQueue
//        readTransactionFileAndWriteToQueue("/Users/Shifa.Kajal/source/student/boca-bc24-java-core-io.reactivestax.problems/src/io.reactivestax.problems/transactions/utility/credit_card_transactions.txt");
//        startMultiThreadedProcessing();
//    }
//}
