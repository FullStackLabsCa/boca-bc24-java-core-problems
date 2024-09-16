package transaction;

public class Runner {

    public static void main(String[] args) {
        // Step 1: Configure HikariCP connection pool
        DatabaseConnection.configureHikariCP();

        // Step 2: Read file and load transactions into ArrayBlockingQueue
        FileReader.readTransactionFileAndWriteToQueue("/Users/Gurpreet.Singh/source/credit_card_transactions.txt");
        MultiThreading.startMultiThreadedProcessing();
    }
}
