package trading.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Trade implements Serializable {
    private int line_no;
    private String trade_id;
    private String trade_identify;
    private String ticker_symbol;
    private int quantity;
    private double price;
    LocalDate date;

    public Trade(int line_no, String trade_id, String trade_identify, String ticker_symbol, int quantity, double price, LocalDate date) {
        this.trade_id = trade_id;
        this.trade_identify = trade_identify;
        this.ticker_symbol = ticker_symbol;
        this.quantity = quantity;
        this.price = price;
        this.date = date;
        this.line_no = line_no;
    }

    public Trade(int line_no) {
        this.line_no = line_no;
    }

    public String getTrade_id() {
        return trade_id;
    }

    public void setTrade_id(String trade_id) {
        this.trade_id = trade_id;
    }

    public String getTrade_identify() {
        return trade_identify;
    }

    public void setTrade_identify(String trade_identify) {
        this.trade_identify = trade_identify;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getLine_no() {
        return line_no;
    }

    public void setLine_no(int line_no) {
        this.line_no = line_no;
    }

    @Override
    public String toString() {
        return "Trade{" +
                "trade_id=" + trade_id +
                ", trade_identify='" + trade_identify + '\'' +
                ", ticker_symbol='" + ticker_symbol + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", date=" + date +
                '}';
    }
}
