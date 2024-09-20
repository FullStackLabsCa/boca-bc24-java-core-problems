package problems.bank;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class MainRunner {

    public static void main(String[] args) throws SQLException {
        Connection conn = null;
        try {
            conn = DatabaseHelper.getConnection();

            Account account = new Account(conn);
            PerformTransactions performTransactions = new PerformTransactions(conn);

            Scanner scanner = new Scanner(System.in);

            System.out.println("Please choose an option:");
            System.out.println("1. Check Balance");
            System.out.println("2. Withdraw");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter account no. to get the balance: ");
                    int acc_id = scanner.nextInt();
                    account.getBalance(acc_id);
                    break;

                case 2:
                    System.out.print("Enter account no. to withdraw from: ");
                    int withdrawAccId = scanner.nextInt();
                    System.out.print("Enter amount to withdraw: ");
                    double amount = scanner.nextDouble();
                    performTransactions.withdraw(withdrawAccId, amount);
                    break;

                default:
                    System.out.println("Invalid choice. Please select 1 or 2.");
                    break;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
}
