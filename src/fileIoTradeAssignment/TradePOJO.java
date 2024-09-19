package fileIoTradeAssignment;

import java.sql.Date;

public class TradePOJO {

    String trade_identifier;
    String trade_symbol;
    int quantity ;
    Double price ;
    Date date;

    public TradePOJO(String trade_identifier, String trade_symbol, int quantity, Double price, Date date) {
        this.trade_identifier = trade_identifier;
        this.trade_symbol = trade_symbol;
        this.quantity = quantity;
        this.price = price;
        this.date = date;
    }


    public String getTrade_identifier() {
        return trade_identifier;
    }

    public void setTrade_identifier(String trade_identifier) {
        this.trade_identifier = trade_identifier;
    }

    public String getTrade_symbol() {
        return trade_symbol;
    }

    public void setTrade_symbol(String trade_symbol) {
        this.trade_symbol = trade_symbol;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


}
