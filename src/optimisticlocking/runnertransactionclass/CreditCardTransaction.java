package optimisticlocking.runnertransactionclass;

public class CreditCardTransaction {
    private final String creditCardNumber;
    private final String cardType;
    private final String transactionType;
    private final double amount;
    private final double balance;

    public CreditCardTransaction(String creditCardNumber, String cardType, String transactionType, double amount, double balance) {
        this.creditCardNumber = creditCardNumber;
        this.cardType = cardType;
        this.transactionType = transactionType;
        this.amount = amount;
        this.balance = balance;
    }

    public static CreditCardTransaction fromCsv(String csvLine) {
        String[] data = csvLine.split("\\|");
        return new CreditCardTransaction(
                data[0],
                data[1],
                data[2],
                Double.parseDouble(data[3]),
                Double.parseDouble(data[4])
        );
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public String getCardType() {
        return cardType;
    }

    public String getTransactionType() {
        return transactionType;
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
                ", cardType='" + cardType + '\'' +
                ", transactionType='" + transactionType + '\'' +
                ", amount=" + amount +
                ", balance=" + balance +
                '}';
    }
}

