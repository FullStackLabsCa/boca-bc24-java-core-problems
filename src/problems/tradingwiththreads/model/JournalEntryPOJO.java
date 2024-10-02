package problems.tradingwiththreads.model;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

public class JournalEntryPOJO {

    private String tradeId;
    private String cusip;
    private Timestamp transactionTime;
    private String accountNumber;
    private String securityId;
    private String direction;
    private String quantity;

    public String getTradeId() {
        return tradeId;
    }

    public String getCusip() {
        return cusip;
    }

    public void setCusip(String cusip) {
        this.cusip = cusip;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public Timestamp getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(Timestamp transactionTime) {
        this.transactionTime = transactionTime;
    }

    private String postedStatus;



    public String getAccountNumber(){
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber){
        this.accountNumber = accountNumber;
    }

    public String getSecurityId(){
        return securityId;
    }

    public void setSecurityId(String securityId){
        this.securityId = securityId;
    }

    public String getDirection(){
        return direction;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getPostedStatus() {
        return postedStatus;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setPostedStatus(String postedStatus) {
        this.postedStatus = postedStatus;
    }

    public void setDirection(String direction){
        this.direction= direction;
    }

}
