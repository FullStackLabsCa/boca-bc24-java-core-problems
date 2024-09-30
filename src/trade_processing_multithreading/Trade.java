package trade_processing_multithreading;


import java.time.LocalDateTime;

public class Trade {
//        private int line_no;
        private String trade_id;
        private LocalDateTime transaction_time;
        private String account_number;
        private String cusip;
        private String activity;
        private int quantity;
        private  double price;

//    public Trade(String trade_id, LocalDateTime transaction_time, String account_number, String cusip, String activity, int quantity, double price) {
////        this.line_no = line_no;
//        this.trade_id = trade_id;
//        this.transaction_time = transaction_time;
//        this.account_number = account_number;
//        this.cusip = cusip;
//        this.activity = activity;
//        this.quantity = quantity;
//        this.price = price;
//    }

//    public int getLine_no() {
//        return line_no;
//    }
//
//    public void setLine_no(int line_no) {
//        this.line_no = line_no;
//    }

    public String getTrade_id() {
        return trade_id;
    }

    public void setTrade_id(String trade_id) {
        this.trade_id = trade_id;
    }

    public LocalDateTime getTransaction_time() {
        return transaction_time;
    }

    public void setTransaction_time(LocalDateTime transaction_time) {
        this.transaction_time = transaction_time;
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
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
