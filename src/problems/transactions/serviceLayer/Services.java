//package problems.transactions.serviceLayer;
//
//import problems.transactions.model.CreditCardTransaction;
//import problems.transactions.utility.OptimisticLockingException;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//
//import static problems.transactions.database.HikariDatabaseConnection.dataSource;
//import static problems.transactions.presentationLayer.Transaction.creditCardTransactionQueue;
//import static problems.transactions.repositoryLayer.CreditCardTransactionRepository.*;
//
//public class Services {
//    public void processTransaction(CreditCardTransaction creditCardTransaction) {
//        try (Connection connection = dataSource.getConnection()) {
//            connection.setAutoCommit(false);
//            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
//
//            try {
//                // Step 1: Check if account exists
//                int version = getAccountVersion(connection, creditCardTransaction.getCreditCardNumber());
//                if (version == -1) {
//                    // Step 2: If no account exists, insert it
//                    insertAccount(connection, creditCardTransaction);
//                }else {
//                    updateAccountBalance(connection, creditCardTransaction, version);
//                }
//                // Step 3: Now update with optimistic locking
//                connection.commit();  // Commit transaction
//                System.out.println("Transaction processed for card: " + creditCardTransaction.getCreditCardNumber());
//
//            } catch (OptimisticLockingException e) {
//                System.err.println(e.getMessage());
//                // Put the transaction back in the queue for retry
//                //made change here
//                creditCardTransactionQueue.putFirst(creditCardTransaction);
//            } catch (SQLException e) {
//                connection.rollback();
//                creditCardTransactionQueue.putFirst(creditCardTransaction);
////                e.printStackTrace();
//            }finally {
//                connection.setAutoCommit(true);
//            }
//        } catch (SQLException | InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//}
