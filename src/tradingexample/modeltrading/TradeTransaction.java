package tradingexample.modeltrading;

import java.time.LocalDate;
public class TradeTransaction {
    private final String tradeId;
    private final String tradeIdentifier;
    private final String tickerSymbol;
    private final Integer quantity;
    private final Double price;
    private final LocalDate tradeDate;

    public TradeTransaction(String tradeId, String tradeIdentifier, String tickerSymbol, Integer quantity, Double price, LocalDate tradeDate) {
        this.tradeId = tradeId;
        this.tradeIdentifier = tradeIdentifier;
        this.tickerSymbol = tickerSymbol;
        this.quantity = quantity;
        this.price = price;
        this.tradeDate = tradeDate;
    }

    public static TradeTransaction fromCsv(String csvLine) {
        String[] data = csvLine.split(",");
        try {
            return new TradeTransaction(
                    data[0],
                    data[1],
                    data[2],
                    Integer.parseInt(data[3]),
                    Double.parseDouble(data[4]),
                    LocalDate.parse(data[5])
            );
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public String getTickerSymbol() {
        return tickerSymbol;
    }

    public String getTradeId() {
        return tradeId;
    }

    public String getTradeIdentifier() {
        return tradeIdentifier;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getPrice() {
        return price;
    }

    public LocalDate getTradeDate() {
        return tradeDate;
    }

    @Override
    public String toString() {
        return "TradeTransaction{" +
                "tradeId='" + tradeId + '\'' +
                ", tickerSymbol='" + tickerSymbol + '\'' +
                ", tradeIdentifier='" + tradeIdentifier + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", tradeDate=" + tradeDate +
                '}';
    }


}
