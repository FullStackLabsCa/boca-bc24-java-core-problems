package upgraded.trade.processor.model;

import java.time.LocalDateTime;

public class JournalEntry {
    private final String tradeId;
    private final String accountNumber;
    private final String securityCusp;
    private final String direction;
    private final int quantity;
    private final String postedStatus;
    private final LocalDateTime transactionTime;

    public JournalEntry(String tradeId, String accountNumber, String securityCusp, String direction, int quantity, String postedStatus, LocalDateTime transactionTime) {
        this.tradeId = tradeId;
        this.accountNumber = accountNumber;
        this.securityCusp = securityCusp;
        this.direction = direction;
        this.quantity = quantity;
        this.postedStatus = postedStatus;
        this.transactionTime = transactionTime;
    }

    public String getTradeId() {
        return tradeId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getSecurityCusp() {
        return securityCusp;
    }

    public String getDirection() {
        return direction;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getPostedStatus() {
        return postedStatus;
    }

    public LocalDateTime getTransactionTime() {
        return transactionTime;
    }
}
