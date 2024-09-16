package jdbc.hikari.bank;

import java.util.concurrent.ArrayBlockingQueue;

public class MainRunner {
    public static ArrayBlockingQueue<CreditCardTransaction> creditCardTransactionQueue = new ArrayBlockingQueue<>(5000);

    public static void main(String[] args) throws Exception {
        //Read file and load transactions into ArrayBlockingQueue
        creditCardTransactionQueue = FileProcessor.readTransactionFileAndWriteToQueue("/Users/Gaurav.Manchanda/src/fullstack/boca-bc24-java-core-problems/src/jdbc/hikari/bank/credit_card_transactions.txt", creditCardTransactionQueue);

        MultiThread.startMultiThreadedProcessing();
    }
}
