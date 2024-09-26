package problems.tradefileparser.model;

public class TradeModel {
    private String tradeId;
    private String tradeIdentifier;
    private String tickerSymbol;
    private int quantity;
    private double price;
    private String tradeDate;

    public TradeModel(String tradeId, String tradeIdentifier, String tickerSymbol, int quantity, double price, String tradeDate) {
        this.tradeId = tradeId;
        this.tradeIdentifier = tradeIdentifier;
        this.tickerSymbol = tickerSymbol;
        this.quantity = quantity;
        this.price = price;
        this.tradeDate = tradeDate;
    }

    public String getTradeId() {
        return tradeId;
    }

    public String getTradeIdentifier() {
        return tradeIdentifier;
    }

    public String getTickerSymbol() {
        return tickerSymbol;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public String getTradeDate() {
        return tradeDate;
    }

    @Override
    public String toString() {
        return "model{" +
                "trade_id='" + tradeId + '\'' +
                "trade_identifier='" + tradeIdentifier + '\'' +
                ", ticker_symbol='" + tickerSymbol + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", trade_date='" + tradeDate + '\'' +
                '}';
    }
}
