package multithreadingtrade.trademodel;

public class Positions {
    private String accountNumber;
    private String Cusip;
    private int position_id;
    private String position;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCusip() {
        return Cusip;
    }

    public void setCusip(String cusip) {
        Cusip = cusip;
    }

    public int getPosition_id() {
        return position_id;
    }

    public void setPosition_id(int position_id) {
        this.position_id = position_id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
