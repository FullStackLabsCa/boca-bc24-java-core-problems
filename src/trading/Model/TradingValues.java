package trading.Model;

import java.time.LocalDate;

public class TradingValues {
    private String tradeId;
    private String tradeIdentifier;
    private String tickerSymbol;
    private int quantity;
    private double price;
    LocalDate tradeDate;

    //constructor
    public TradingValues(String tradeId, String tradeIdentifier, String tickerSymbol, int quantity, double price, LocalDate tradeDate) {
        this.tradeId = tradeId;
        this.tradeIdentifier = tradeIdentifier;
        this.tickerSymbol = tickerSymbol;
        this.quantity = quantity;
        this.price = price;
        this.tradeDate = tradeDate;
    }

    // Getters
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

    public LocalDate getTradeDate() {
        return tradeDate;
    }

    // To String method
    @Override
    public String toString() {
        return "TradingValues{" +
                "tradeId=" + tradeId +
                ", tradeIdentifier='" + tradeIdentifier + '\'' +
                ", tickerSymbol='" + tickerSymbol + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", tradeDate=" + tradeDate +
                '}';
    }
}
