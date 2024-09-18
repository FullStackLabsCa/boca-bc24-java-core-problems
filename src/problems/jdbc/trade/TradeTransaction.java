package problems.jdbc.trade;


import java.util.Date;

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

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public String getTickerSymbol() {
        return tickerSymbol;
    }

    public void setTickerSymbol(String tickerSymbol) {
        this.tickerSymbol = tickerSymbol;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    @Override
    public String toString() {
        return "TradeTransaction{" +
                "tickerId='" + tradeId + '\'' +
                ", tickerSymbol='" + tickerSymbol + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", tradeDate='" + tradeDate + '\'' +
                '}';
    }
}
