package problems.creditcardtransaction.oopimplemented.service;

import problems.creditcardtransaction.oopimplemented.model.CreditCardTransaction;
import problems.creditcardtransaction.oopimplemented.DataIO.AccountDAO;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionService {
    private final AccountDAO accountDAO;

    public TransactionService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public void processTransaction(CreditCardTransaction transaction, Connection connection) throws SQLException {
        int version = accountDAO.getAccountVersion(connection, transaction.getCreditCardNumber());

        if (version == -1) {
            accountDAO.insertAccount(connection, transaction);
        }

        accountDAO.updateAccountBalance(connection, transaction, version);
    }
}
