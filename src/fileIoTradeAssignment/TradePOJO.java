package fileIoTradeAssignment;

import java.sql.Date;

public class TradePOJO {

    int trade_id;
    String trade_identifier;
    String ticker_symbol;
    int quantity ;
    Double price ;
    Date date;

    public TradePOJO(int trade_id,String trade_identifier, String ticker_symbol, int quantity, Double price, Date date) {
        this.trade_id= trade_id;
        this.trade_identifier = trade_identifier;
        this.ticker_symbol = ticker_symbol;
        this.quantity = quantity;
        this.price = price;
        this.date = date;
    }

    public int getTrade_id() {
        return trade_id;
    }

    public String getTrade_identifier() {
        return trade_identifier;
    }


    public String getTicker_symbol() {
        return ticker_symbol;
    }



    public int getQuantity() {
        return quantity;
    }


    public Double getPrice() {
        return price;
    }


    public Date getDate() {
        return date;
    }




}
