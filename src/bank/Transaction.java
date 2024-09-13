package bank;

import config.DatabaseHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Transaction {
    private int transactionId;
    private int accountId;
    private double amount;
    private Timestamp timestamp;
    private Connection connection;

    public Transaction( int accountId, double amount, Timestamp timestamp) {
        this.accountId = accountId;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public Transaction() throws SQLException {
        this.connection = DatabaseHelper.getConnection();
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    public void logTransaction(Transaction transaction){
        String sql = "INSERT INTO transactions (account_id, amount, transaction_timestamp) VALUES (?, ?, ?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1,this.accountId);
            preparedStatement.setDouble(2,this.amount);
            preparedStatement.setTimestamp(3,this.timestamp);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static List<Transaction> getTransactionsByAccountId(int accountId,Connection connection) throws SQLException {
        String sql = "SELECT * FROM transactions WHERE account_id=?";
        List<Transaction> transactions = new ArrayList<>();

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1,accountId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                double amount = resultSet.getDouble("amount");
                Timestamp timestamp  = resultSet.getTimestamp("transaction_timestamp");

                Transaction transaction = new Transaction(accountId,amount,timestamp);

                transactions.add(transaction);
            }
        }
        return transactions;
    }
}
