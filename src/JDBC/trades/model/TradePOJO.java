package JDBC.trades.model;

import java.sql.Date;

public class TradePOJO {
    String trade_id;
    String trade_identifier;
    String ticker_symbol;
    int quantity;
    double price;
    Date date;

    public TradePOJO(String trade_id, String trade_identifier, String ticker_symbol, int quantity, double price, Date date) {
        this.trade_id = trade_id;
        this.ticker_symbol = ticker_symbol;
        this.quantity = quantity;
        this.price = price;
        this.date = date;
        this.trade_identifier = trade_identifier;
    }

    public void setTrade_identifier(String trade_identifier) {
        this.trade_identifier = trade_identifier;
    }

    public String getTrade_identifier(){
        return trade_identifier;
    }

    public String getTrade_id() {
        return trade_id;
    }

    public void setTrade_id(String trade_id) {
        this.trade_id = trade_id;
    }

    public String getTicker_symbol() {
        return ticker_symbol;
    }

    public void setTicker_symbol(String ticker_symbol) {
        this.ticker_symbol = ticker_symbol;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "TradePOJO{" +
                "trade_id='" + trade_id + '\'' +
                ", trade_identifier='" + trade_identifier + '\'' +
                ", ticker_symbol='" + ticker_symbol + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", date=" + date +
                '}';
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
