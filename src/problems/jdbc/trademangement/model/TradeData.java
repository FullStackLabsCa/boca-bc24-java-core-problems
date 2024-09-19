package jdbc.trademangement.model;

import java.util.Date;

public class TradeData {
    String trade_id, ticker_symbol;
    int quantity;
    double price;
    String trade_date;

    public TradeData(String trade_id, String ticker_symbol, int quantity, double price, String trade_date) {
        this.trade_id = trade_id;
        this.ticker_symbol = ticker_symbol;
        this.quantity = quantity;
        this.price = price;
        this.trade_date = trade_date;
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

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTrade_date() {
        return trade_date;
    }

    public void setTrade_date(Date trade_date) {
        this.trade_date = String.valueOf(trade_date);
    }

    @Override
    public String toString() {
        return "TradeData{" +
                "trade_id='" + trade_id + '\'' +
                ", ticker_symbol='" + ticker_symbol + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", trade_date=" + trade_date +
                '}';
    }
}
