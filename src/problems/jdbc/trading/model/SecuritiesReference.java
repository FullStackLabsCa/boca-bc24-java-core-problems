package problems.jdbc.trading.model;

public class SecuritiesReference {
    private final String symbol;
    private final String description;

    public SecuritiesReference(String symbol, String description) {
        this.symbol = symbol;
        this.description = description;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getDescription() {
        return description;
    }
}
