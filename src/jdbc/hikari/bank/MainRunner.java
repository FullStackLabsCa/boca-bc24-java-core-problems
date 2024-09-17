package jdbc.hikari.bank;

import java.util.concurrent.LinkedBlockingDeque;

public class MainRunner {
    public static LinkedBlockingDeque<CreditCardTransaction> creditCardTransactionQueue = new LinkedBlockingDeque<>(5000);

    public static void main(String[] args) throws Exception {
        MultiThread.startMultiThreadedProcessing();
        //Read file and load transactions into ArrayBlockingQueue
        creditCardTransactionQueue = FileProcessor.readTransactionFileAndWriteToQueue("/Users/Gaurav.Manchanda/src/fullstack/boca-bc24-java-core-problems/src/jdbc/hikari/bank/credit_card_transactions.txt", creditCardTransactionQueue);

    }
}
