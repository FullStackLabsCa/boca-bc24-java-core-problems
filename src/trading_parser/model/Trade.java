package trading_parser.model;

import java.time.LocalDate;

public class Trade {

    private String tradeId;
    private String tradeIdentifier;
    private String tickerSymbol;
    private int quantity;
    private double price;

    public String getTradeIdentifier() {
        return tradeIdentifier;
    }

    public void setTradeIdentifier(String tradeIdentifier) {
        this.tradeIdentifier = tradeIdentifier;
    }

    private LocalDate tradeDate;

    public Trade(String tradeId, String tradeIdentifier, String tickerSymbol, int quantity, double price, LocalDate tradeDate) {
        this.tradeId = tradeId;
        this.tradeIdentifier = tradeIdentifier;
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

    public LocalDate getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(LocalDate tradeDate) {
        this.tradeDate = tradeDate;
    }

    @Override
    public String toString() {
        return "Trade{" +
                "trade_id='" + tradeId + '\'' +
                ", trade_identifier='" + tradeIdentifier + '\'' +
                ", ticker_symbol='" + tickerSymbol + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", trade_date=" + tradeDate +
                '}';
    }
}