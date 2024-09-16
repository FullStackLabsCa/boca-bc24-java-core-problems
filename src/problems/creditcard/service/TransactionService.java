package problems.creditcard.service;

import com.zaxxer.hikari.HikariDataSource;
import problems.creditcard.dao.TransactionDAO;
import problems.creditcard.entity.CreditCardTransaction;
import problems.creditcard.exception.OptimisticLockingException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.concurrent.BlockingDeque;

import static problems.creditcard.config.HikariCPConfig.getDataSource;
import static problems.creditcard.dao.TransactionDAO.insertAccount;
import static problems.creditcard.dao.TransactionDAO.updateAccountBalance;


public class TransactionService implements Runnable {

    private BlockingDeque<CreditCardTransaction> creditCardTransactionQueue;
    private HikariDataSource dataSource;

    public TransactionService(BlockingDeque<CreditCardTransaction> creditCardTransactionQueue, HikariDataSource dataSource) {
        this.creditCardTransactionQueue = creditCardTransactionQueue;
        this.dataSource = dataSource;
    }

    @Override
    public void run() {
        while (true) {
            if (creditCardTransactionQueue.isEmpty()) {
                creditCardTransactionQueue = null;
                break;
            } else {
                try {
                    CreditCardTransaction creditCardTransaction = creditCardTransactionQueue.take();
                    processTransaction(creditCardTransaction);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
    }

    private void processTransaction(CreditCardTransaction creditCardTransaction) {
        try (Connection connection = getDataSource().getConnection()) {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            try {
                int version = TransactionDAO.getAccountVersion(connection, creditCardTransaction.getCreditCardNumber());
                if (version == -1) {
                    insertAccount(connection, creditCardTransaction);
                } else {
                    updateAccountBalance(connection, creditCardTransaction, version);
                }
                connection.commit();
                System.out.println("Transaction processed for card: " + creditCardTransaction.getCreditCardNumber());

            } catch (SQLIntegrityConstraintViolationException | OptimisticLockingException e) {
                System.err.println(e.getMessage());
                creditCardTransactionQueue.putFirst(creditCardTransaction);
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            }
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
