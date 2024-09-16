package problems.jdbc.optimisticlocking;

public interface TransactionService {

    public void processTransaction(CreditCardTransaction creditCardTransaction);
}
