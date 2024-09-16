package optimistic_lock.launcher;

import static optimistic_lock.process.CreditCardTransactionProcessor.*;

public class Launcher {
    public static void main(String[] args) {

        configureHikariCP();

        readTransactionFileAndWriteToQueue("/Users/Nimanshukumar.Patel/IdeaProjects/JDBC/src/optimistic_lock/credit_card_transactions.txt");

        startMultiThreadedProcessing();

    }
}
