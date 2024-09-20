package problems.jdbc.trading.model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trade trade = (Trade) o;
        return quantity == trade.quantity && Double.compare(price, trade.price) == 0 && Objects.equals(tradeId, trade.tradeId) && Objects.equals(tradeIdentifier, trade.tradeIdentifier) && Objects.equals(tickerSymbol, trade.tickerSymbol) && Objects.equals(tradeDate, trade.tradeDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tradeId, tradeIdentifier, tickerSymbol, quantity, price, tradeDate);
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
