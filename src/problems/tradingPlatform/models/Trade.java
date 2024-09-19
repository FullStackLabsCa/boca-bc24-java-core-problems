package problems.tradingPlatform.models;

import java.io.Serial;
import java.io.Serializable;

public class Trade implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String tickerSymbol,tradeIdentifier;
    private int tradeId,quantity;
    private double price;
    private String tradeDate;

    public Trade(int tradeId, String tradeIdentifier,String tickerSymbol, int quantity, double price, String tradeDate) {
        this.tradeId = tradeId;
        this.tradeIdentifier = tradeIdentifier;
        this.tickerSymbol = tickerSymbol;
        this.quantity = quantity;
        this.price = price;
        this.tradeDate = tradeDate;
    }

    public int getTradeId() {
        return tradeId;
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

    public String getTradeIdentifier() {
        return tradeIdentifier;
    }

    public void setTradeIdentifier(String tradeIdentifier) {
        this.tradeIdentifier = tradeIdentifier;
    }

    public void setTradeId(int tradeId) {
        this.tradeId = tradeId;
    }

    public void setTickerSymbol(String tickerSymbol) {
        this.tickerSymbol = tickerSymbol;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate;
    }

    @Override
    public String toString() {
        return "Trade{" +
                "tradeId=" + tradeId +
                ", tradeIdentifier='" + tradeIdentifier + '\'' +
                ", tickerSymbol='" + tickerSymbol + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", tradeDate='" + tradeDate + '\'' +
                '}';
    }
}
