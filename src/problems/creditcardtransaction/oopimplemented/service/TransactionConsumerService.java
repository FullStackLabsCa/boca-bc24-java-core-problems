package problems.creditcardtransaction.oopimplemented.service;

import com.zaxxer.hikari.HikariDataSource;

import problems.creditcardtransaction.oopimplemented.model.CreditCardTransaction;
import problems.creditcardtransaction.oopimplemented.util.TransactionQueueUtil;

import java.sql.Connection;
import java.sql.SQLException;

    public class TransactionConsumerService implements Runnable {
        private final HikariDataSource dataSource;
        private final TransactionService transactionService;

        public TransactionConsumerService(HikariDataSource dataSource, TransactionService transactionService) {
            this.dataSource = dataSource;
            this.transactionService = transactionService;
        }

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                CreditCardTransaction transaction = TransactionQueueUtil.getQueue().pollFirst();
                if (transaction != null) {
                    try (Connection connection = dataSource.getConnection()) {
                        connection.setAutoCommit(false);
                        connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
                        transactionService.processTransaction(transaction, connection);
                        connection.commit();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

}
