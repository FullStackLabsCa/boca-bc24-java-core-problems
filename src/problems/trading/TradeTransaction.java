package problems.trading;

import java.sql.Date;

public class TradeTransaction {
    private String tradeId;
    private String tickerSymbol;
    private int quantity;
    private double price;
    private Date tradeDate;

    public TradeTransaction(String tradeId, String tickerSymbol, int quantity, double price, Date tradeDate) {
        this.tradeId = tradeId;
        this.tickerSymbol = tickerSymbol;
        this.quantity = quantity;
        this.price = price;
        this.tradeDate = tradeDate;
    }

    public String getTradeId() {
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

    public Date getTradeDate() {
        return tradeDate;
    }

    @Override
    public String toString() {
        return "TradeTransaction{" +
                "tradeId='" + tradeId + '\'' +
                ", tickerSymbol='" + tickerSymbol + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", tradeDate=" + tradeDate +
                '}';
    }
}
