package bank;

import config.DatabaseHelper;

import java.sql.*;
import java.util.HashMap;

public class Account {
    private int accountId;
    private int version;
    private double balance;
    private Connection connection;


    public Account(int accountId, Connection connection) {
        this.accountId = accountId;
        this.connection = connection;
    }
public Account(){

}
    public void withdrawMoney(double amount) {
        if (balance > amount) {
            balance = balance - amount;
        }else{
            throw new IllegalStateException();
        }
    }
    public void depositAmount(double amount){
        balance = balance+amount;
    }
    public void createAccount() throws SQLException {
        String sql = "INSERT INTO accounts (balance) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setDouble(1, this.balance);
            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    this.accountId = rs.getInt(1);
                }
            }
        }
    }
    public void getAccountById(int accountId) throws SQLException {
        HashMap<Integer, Double> accountDetail = new HashMap<>();
        String sql = "SELECT balance FROM accounts WHERE account_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, accountId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    this.balance = rs.getDouble("balance");
                    this.accountId = accountId;
                    accountDetail.put(accountId,balance);
                }
            }
            System.out.println(accountDetail);
        }
    }
    public void updateAccountBalance(double newBalance,int currentVersion) throws SQLException {
        String sql = "UPDATE accounts SET balance = ?, version = version + 1 WHERE account_id = ? AND version = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setDouble(1, newBalance);
            stmt.setInt(2,accountId);
            stmt.setInt(3,currentVersion);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated == 0) {
                throw new SQLException("Update failed, possible version mismatch.");
            }
        }
    }



    public void deleteAccount(int accountId) throws SQLException {
        String sql = "DELETE FROM accounts WHERE account_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, accountId);
            stmt.executeUpdate();
        }
    }

    public double getBalance(){
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
