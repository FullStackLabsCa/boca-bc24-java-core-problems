package io.reactivestax.model;

public class JournalEntrySandbox {
    private String journalAccountID;
    private String journalCusip;
    private String  journalDirection;
    private  int journalQuantity;

    public String getJournalAccountID() {
        return journalAccountID;
    }

    public void setJournalAccountID(String journalAccountID) {
        this.journalAccountID = journalAccountID;
    }

    public String getJournalCusip() {
        return journalCusip;
    }

    public void setJournalCusip(String journalCusip) {
        this.journalCusip = journalCusip;
    }

    public String getJournalDirection() {
        return journalDirection;
    }

    public void setJournalDirection(String journalDirection) {
        this.journalDirection = journalDirection;
    }

    public int getJournalQuantity() {
        return journalQuantity;
    }

    public void setJournalQuantity(int journalQuantity) {
        this.journalQuantity = journalQuantity;
    }
}
