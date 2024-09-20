package problems.jdbc.trade;
import java.util.Date;

public class TradeTransaction {
    private String tradeIdentifier;
    private String tickerSymbol;
    private int quantity;
    private double price;
    private Date tradeDate;
    private int lineNumber;

    public TradeTransaction(String tradeIdentifier, String tickerSymbol, int quantity, double price, Date tradeDate, int lineNumber) {
        this.tradeIdentifier = tradeIdentifier;
        this.tickerSymbol = tickerSymbol;
        this.quantity = quantity;
        this.price = price;
        this.tradeDate = tradeDate;
        this.lineNumber = lineNumber;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    @Override
    public String toString() {
        return "TradeTransaction{" +
                "tradeIdentifier='" + tradeIdentifier + '\'' +
                ", tickerSymbol='" + tickerSymbol + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", tradeDate=" + tradeDate +
                ", lineNumber=" + lineNumber +
                '}';
    }
    public static final class TradeTransactionBuilder {
        private String tradeIdentifier;
        private String tickerSymbol;
        private int quantity;
        private double price;
        private Date tradeDate;
        private int lineNumber;

        private TradeTransactionBuilder() {
        }

        public static TradeTransactionBuilder aTradeTransaction() {
            return new TradeTransactionBuilder();
        }

        public TradeTransactionBuilder withTradeIdentifier(String tradeIdentifier) {
            this.tradeIdentifier = tradeIdentifier;
            return this;
        }

        public TradeTransactionBuilder withTickerSymbol(String tickerSymbol) {
            this.tickerSymbol = tickerSymbol;
            return this;
        }

        public TradeTransactionBuilder withQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public TradeTransactionBuilder withPrice(double price) {
            this.price = price;
            return this;
        }

        public TradeTransactionBuilder withTradeDate(Date tradeDate) {
            this.tradeDate = tradeDate;
            return this;
        }

        public TradeTransactionBuilder withLineNumber(int lineNumber) {
            this.lineNumber = lineNumber;
            return this;
        }

        public TradeTransaction build() {
            return new TradeTransaction(tradeIdentifier, tickerSymbol, quantity, price, tradeDate, lineNumber);
        }
    }
}

