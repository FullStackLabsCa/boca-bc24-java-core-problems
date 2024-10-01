package trading.model;

public class TradingModel {
    String tradeId;
    String tradeIdentifier;
    String tickerSymbol;
    int quantity;
    Double price;
    String date;
    int version;



    public TradingModel(String tradeId, String tradeIdentifier, String tickerSymbol, int quantity, Double price, String date) {
        this.tradeId = tradeId;
        this.tradeIdentifier = tradeIdentifier;
        this.tickerSymbol = tickerSymbol;
        this.quantity = quantity;
        this.price = price;
        this.date = date;
        this.version = 1;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public String getTradeIdentifier() {
        return tradeIdentifier;
    }

    public void setTradeIdentifier(String tradeIdentifier) {
        this.tradeIdentifier = tradeIdentifier;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "TradingModel{" +
                "trade_id=" + tradeId +
                " |  ticker_symbol='" + tickerSymbol + '\'' +
                " |  quantity=" + quantity +
                " |  price=" + price +
                " |  date=" + date +
                ", version=" + version +
                '}';
    }
}
