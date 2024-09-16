package jdbc.creditcard.model;

// Transaction POJO
public class CreditCardTransaction {
    private String creditCardNumber;
    private double amount;
    private double balance;

    public CreditCardTransaction(String creditCardNumber, double balance) {
        this.creditCardNumber = creditCardNumber;
        this.amount = amount;
        this.balance = balance;
    }

    // Getters
    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public double getAmount() {
        return amount;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "creditCardNumber='" + creditCardNumber + '\'' +
                ", amount=" + amount +
                ", balance=" + balance +
                '}';
    }
}