package problems.trading.tradingmodel;

import java.time.LocalDate;

public class TradingValues {
    private String trade_id;
    private String trade_identifier;
    private String ticker_symbol;
    private int quantity;
    private Double price;
    private LocalDate trade_date;

    public TradingValues(String trade_id,String trade_identifier,String ticker_symbol, int quantity, double price, LocalDate trade_date) {
        this.trade_id = trade_id;
        this.trade_identifier = trade_identifier;
        this.ticker_symbol = ticker_symbol;
        this.quantity = quantity;
        this.price = price;
        this.trade_date = trade_date;
    }

//     Getters
    public String getTradeId() {
        return trade_id;
    }

    public String getTradeIdentifier(){
        return trade_identifier;
    }

    public String getTickerSymbol() {
        return ticker_symbol;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public LocalDate getTradeDate() {
        return trade_date;
    }

    @Override
    public String toString() {
        return "TradingValues{" +
                "trade_id" + trade_id + '\'' +
                "trade_identifier" + trade_identifier + '\'' +
                ", ticker_symbol='" + ticker_symbol + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", trade_date='" + trade_date + '\'' +
                '}';
    }
}


