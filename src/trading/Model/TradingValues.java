package trading.Model;
import java.time.LocalDate;

public class TradingValues {
    private  String tradeId;
    private  String tickerSymbol;
    private int quantity;
    private  double price;
    LocalDate tradeDate;

    public TradingValues(String tradeId, String tickerSymbol, int quantity, double price, LocalDate tradeDate) {
        this.tradeId = tradeId;
        this.tickerSymbol = tickerSymbol;
        this.quantity = quantity;
        this.price = price;
        this.tradeDate = tradeDate;
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

    public double getPrice() {
        return price;
    }

    public LocalDate gettradeDate() {
        return tradeDate;
    }

    @Override
    public String toString() {
        return "TradingValues :-" +
                "tradeId = '" + tradeId + '\'' +
                ", tickerSymbol = '" + tickerSymbol + '\'' +
                ", quantity = " + quantity +
                ", price = "  + price +
                ", tradeDate = " + tradeDate
                ;
    }
}
