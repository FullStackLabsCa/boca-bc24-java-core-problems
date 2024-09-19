package problems.jdbc.trading.model;

import java.sql.Date;
import java.time.LocalDate;

public class Trade {
    private final String tradeId;
    private final String tradeIdentifier;
    private final String tickerSymbol;
    private final int quantity;
    private final double price;
    private final LocalDate tradeDate;


    public Trade(String tradeId, String tradeIdentifier, String tickerSymbol, int quantity, double price, LocalDate tradeDate) {
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

    public Date getTradeDate() {
        return Date.valueOf(tradeDate);
    }

    @Override
    public String toString() {
        return "Trade{" +
                "tradeId='" + tradeId + '\'' +
                ", tradeIdentifier='" + tradeIdentifier + '\'' +
                ", tickerSymbol='" + tickerSymbol + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", tradeDate=" + tradeDate +
                '}';
    }
}
