package problems.creditcardtransaction.oopimplemented.util;
import problems.creditcardtransaction.oopimplemented.model.CreditCardTransaction;

import java.util.Deque;
import java.util.concurrent.LinkedBlockingDeque;

public class TransactionQueueUtil {
    private static final LinkedBlockingDeque<CreditCardTransaction> transactionQueue = new LinkedBlockingDeque<>(5000);

    public static LinkedBlockingDeque<CreditCardTransaction> getQueue() {
        return transactionQueue;
    }
}
