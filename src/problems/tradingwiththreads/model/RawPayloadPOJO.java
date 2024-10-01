package problems.tradingwiththreads.model;

public class RawPayloadPOJO {

    private String tradeId;
    private String status = "Valid";
    private String statusReason;
    private String payload;
    private String accountID;


    public String getAccountID(String accountID){
        return accountID;
    }

    public void setAccountID(String accountID){
        this.accountID = accountID;
    }


    public String getTradeId(){
        return tradeId;
    }

    public void setTradeId(String tradeId){
        this.tradeId = tradeId;
    }

    public String getStatus(){
        return status;
    }

   public void setStatus(String status){
        this.status = status;
   }

   public String getStatusReason(){
        return statusReason;
   }

   public void setStatusReason(String statusReason){
        this.statusReason = statusReason;
   }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }
}
