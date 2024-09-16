package problems.jdbc.optimisticlocking;

import problems.jdbc.optimisticlocking.service.CreditCardTransactionService;

public class CreditCardTransactionProcessor {

    public static void main(String[] args) {
        CreditCardTransactionService.setupDBConnectionAndRunFileReadingAndExecuteThreads();
    }

}








