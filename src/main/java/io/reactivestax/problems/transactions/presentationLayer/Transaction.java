package io.reactivestax.problems.transactions.presentationLayer;

import io.reactivestax.problems.transactions.model.CreditCardTransaction;

import java.util.concurrent.LinkedBlockingDeque;


public class Transaction {

    public static LinkedBlockingDeque<CreditCardTransaction> creditCardTransactionQueue = new LinkedBlockingDeque<>(5000);

}





