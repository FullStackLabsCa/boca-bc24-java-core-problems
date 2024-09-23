package Trading.model;

import java.time.LocalDate;

public class TradingValues {
    private String trade_id;
    private String trade_identifier;
    private String ticker_symbol;
    private int quantity;
    private double price;
    LocalDate trade_date;

    public TradingValues(String trade_id, String trade_identifier, String ticker_symbol, int quantity, double price, LocalDate trade_date) {
        this.trade_id = trade_id;
        this.trade_identifier = trade_identifier;
        this.ticker_symbol = ticker_symbol;
        this.quantity = quantity;
        this.price = price;
        this.trade_date = trade_date;
    }

    public String getTrade_identifier() {
        return trade_identifier;
    }

    public void setTrade_identifier(String trade_identifier) {
        this.trade_identifier = trade_identifier;
    }

    public LocalDate getTrade_date() {
        return trade_date;
    }

    public void setTrade_date(LocalDate trade_date) {
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

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Trading.model.Trading{" +
                "trade_id='" + trade_id + '\'' +
                ", trade_identifier='" + trade_identifier + '\'' +
                ", ticker_symbol='" + ticker_symbol + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", trade_date='" + trade_date + '\'' +
                '}';
    }
}
