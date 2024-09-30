package thread.tradeprocess.model;

public class TradeProcessData {
    String tradeId;
    String accountNo;
    String cusip;
    String activity;
    int quantity;
    double price;

    public TradeProcessData(String tradeId, String accountNo, String cusip, String activity, int quantity, double price) {
        this.tradeId = tradeId;
        this.accountNo = accountNo;
        this.cusip = cusip;
        this.activity = activity;
        this.quantity = quantity;
        this.price = price;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getCusip() {
        return cusip;
    }

    public void setCusip(String cusip) {
        this.cusip = cusip;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
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

}
