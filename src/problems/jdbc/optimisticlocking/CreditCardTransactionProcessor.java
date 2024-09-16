package jdbc.optimisticlocking;

import jdbc.optimisticlocking.service.CreditCardService;

public class CreditCardTransactionProcessor {
    public static void main(String[] args) {
        CreditCardService.setupDbConnectionAndReadFile();
    }
}