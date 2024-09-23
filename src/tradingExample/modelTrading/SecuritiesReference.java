package tradingExample.modelTrading;

public class SecuritiesReference {

    private final String symbol;
    private  final String desc;

    public SecuritiesReference(String symbol, String desc) {
        this.symbol = symbol;
        this.desc = desc;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getDesc() {
        return desc;
    }
}
