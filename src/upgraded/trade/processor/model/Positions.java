package upgraded.trade.processor.model;

public class Positions {
    private final String accountNumber;
    private final String securityCusp;
    private int quantity;
    private int version;

    public Positions(int quantity, String accountNumber, String securityCusp, int version) {
        this.quantity = quantity;
        this.accountNumber = accountNumber;
        this.securityCusp = securityCusp;
        this.version = version;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getSecurityCusp() {
        return securityCusp;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
