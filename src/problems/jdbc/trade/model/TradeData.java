package jdbc.trade.model;

public class TradeData {
    String trade_id, ticker_symbol, trade_identifier, trade_date;
    int quantity, line_no;
    double price;

    public TradeData(String trade_id, String trade_identifier, String ticker_symbol, int quantity, double price, String trade_date, int line_no) {
        this.trade_id = trade_id;
        this.trade_identifier = trade_identifier;
        this.ticker_symbol = ticker_symbol;
        this.quantity = quantity;
        this.price = price;
        this.trade_date = trade_date;
        this.line_no = line_no;
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

    public int getLine_no() {
        return line_no;
    }

    public void setLine_no(int line_no) {
        this.line_no = line_no;
    }

    @Override
    public String toString() {
        return "TradeData{" +
                "trade_id='" + trade_id + '\'' +
                ", ticker_symbol='" + ticker_symbol + '\'' +
                ", trade_identifier='" + trade_identifier + '\'' +
                ", trade_date='" + trade_date + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
