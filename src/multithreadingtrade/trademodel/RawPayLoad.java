package multithreadingtrade.trademodel;


public class RawPayLoad {
    private String tradeID;
    private String status = "Valid";
    private String statusReason;
    private String payLoads;

    public String getStatusReason() {
        return statusReason;
    }

    public void setStatusReason(String statusReason) {
        this.statusReason = statusReason;
    }

    public String getTradeID() {
        return tradeID;
    }

    public String getStatus() {
        return status;
    }

    public String getPayLoads() {
        return payLoads;
    }


    public void setTradeID(String tradeID) {
        this.tradeID = tradeID;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPayLoads(String payLoads) {
        this.payLoads = payLoads;
    }
}