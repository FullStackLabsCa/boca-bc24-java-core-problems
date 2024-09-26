package jdbc.trade.model;

public class TradeData {
    String tradeId;
    String tickerSymbol;
    String tradeIdentifier;
    String tradeDate;
    int quantity;
    int lineNo;
    double price;

    public TradeData(String tradeId, String tradeIdentifier, String tickerSymbol, int quantity, double price, String tradeDate, int lineNo) {
        this.tradeId = tradeId;
        this.tradeIdentifier = tradeIdentifier;
        this.tickerSymbol = tickerSymbol;
        this.quantity = quantity;
        this.price = price;
        this.tradeDate = tradeDate;
        this.lineNo = lineNo;
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

    public int getLineNo() {
        return lineNo;
    }

    @Override
    public String toString() {
        return "TradeData{" +
                "tradeId='" + tradeId + '\'' +
                ", tickerSymbol='" + tickerSymbol + '\'' +
                ", tradeIdentifier='" + tradeIdentifier + '\'' +
                ", tradeDate='" + tradeDate + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
