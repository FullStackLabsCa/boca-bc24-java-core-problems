package jdbc.creditcard;

import jdbc.creditcard.repository.CreditCardRepository;
import jdbc.creditcard.service.CreditCardService;

public class CreditCardTransactionProcessor {
    public static void main(String[] args) {
        CreditCardService.setupDbConnectionAndReadFile();
    }
}