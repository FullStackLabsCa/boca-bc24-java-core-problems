package problems.trademultithreading;

public class TradePayload {
    private final String tradeId;
    private final String accountNumber;
    private final String securityId;
    private final String direction;
    private final int quantity;

    public TradePayload(String tradeId, String accountNumber, String securityId, String direction, int quantity) {
        this.tradeId = tradeId;
        this.accountNumber = accountNumber;
        this.securityId = securityId;
        this.direction = direction;
        this.quantity = quantity;
    }

    public String getTradeId() {
        return tradeId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getSecurityId() {
        return securityId;
    }

    public String getDirection() {
        return direction;
    }

    public int getQuantity() {
        return quantity;
    }
}
