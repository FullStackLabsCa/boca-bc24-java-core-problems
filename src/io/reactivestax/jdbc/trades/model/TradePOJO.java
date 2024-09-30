package io.reactivestax.jdbc.trades.model;

import java.sql.Date;

public class TradePOJO {
    String tradeId;
    String tradeIdentifier;
    String tickerSymbol;
    int quantity;
    double price;
    Date date;

    public TradePOJO(String tradeId, String tradeIdentifier, String tickerSymbol, int quantity, double price, Date date) {
        this.tradeId = tradeId;
        this.tickerSymbol = tickerSymbol;
        this.quantity = quantity;
        this.price = price;
        this.date = date;
        this.tradeIdentifier = tradeIdentifier;
    }

    public String getTradeIdentifier() {
        return tradeIdentifier;
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

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "TradePOJO{" +
                "trade_id='" + tradeId + '\'' +
                ", trade_identifier='" + tradeIdentifier + '\'' +
                ", ticker_symbol='" + tickerSymbol + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", date=" + date +
                '}';
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }


}
