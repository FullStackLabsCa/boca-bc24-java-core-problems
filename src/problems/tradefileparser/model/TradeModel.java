package problems.tradefileparser.model;

public class TradeModel {
    private String trade_id;
    private String trade_identifier;
    private String ticker_symbol;
    private int quantity;
    private double price;
    private String trade_date;

    public TradeModel(String trade_id, String trade_identifier, String ticker_symbol, int quantity, double price, String trade_date) {
        this.trade_id = trade_id;
        this.trade_identifier = trade_identifier;
        this.ticker_symbol = ticker_symbol;
        this.quantity = quantity;
        this.price = price;
        this.trade_date = trade_date;
    }

    public String getTrade_id() {
        return trade_id;
    }

    public String getTrade_identifier() {
        return trade_identifier;
    }

    public String getTicker_symbol() {
        return ticker_symbol;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public String getTrade_date() {
        return trade_date;
    }

    @Override
    public String toString() {
        return "model{" +
                "trade_id='" + trade_id + '\'' +
                "trade_identifier='" + trade_identifier + '\'' +
                ", ticker_symbol='" + ticker_symbol + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", trade_date='" + trade_date + '\'' +
                '}';
    }
}
