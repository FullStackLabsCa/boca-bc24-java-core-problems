package problems.trading_3way_with_object;

import java.sql.Date;

public class Trade {
    private String tradeId;
    private String tradeIdentifier;
    private String tickerSymbol;
    private int quantity;
    private double price;
    private String tradeDate;

    public Trade(String tradeId, String tradeIdentifier, String tickerSymbol, int quantity, double price, String tradeDate) {
        this.tradeId = tradeId;
        this.tradeIdentifier = tradeIdentifier;
        this.tickerSymbol = tickerSymbol;
        this.quantity = quantity;
        this.price = price;
        this.tradeDate = tradeDate;
    }

    // Getters and Setters

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
}
