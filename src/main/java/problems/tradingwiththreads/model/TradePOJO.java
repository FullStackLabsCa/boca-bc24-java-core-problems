package problems.tradingwiththreads.model;

import java.sql.Time;

public class TradePOJO {

    private String trade_id;
    private Time transaction_time;
    private String account_number;
    private String activity;
    private String cusip;
    private int quantity;
    private double price;


    public TradePOJO(String trade_id, Time transaction_time, String account_number, String activity, String cusip, int quantity, double price) {

        this.trade_id = trade_id;
        this.account_number = account_number;
        this.activity = activity;
        this.cusip = cusip;
        this.quantity = quantity;
        this.price = price;
        this.transaction_time = transaction_time;


    }

    public String getTrade_id() {
        return trade_id;
    }


    public String getAccount_number() {
        return account_number;
    }

    public String getActivity() {
        return activity;
    }

    public String getCusip() {
        return cusip;
    }


    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public Time getTransaction_time() {
        return transaction_time;
    }

}

