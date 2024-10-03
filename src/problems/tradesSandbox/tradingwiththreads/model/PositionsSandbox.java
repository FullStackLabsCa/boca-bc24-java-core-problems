package problems.tradesSandbox.tradingwiththreads.model;

public class PositionsSandbox {
    String positionAccountId;
    String positionCusip;
    String positionPosition;
    int positionVersion;

    public String getPositionAccountId() {
        return positionAccountId;
    }

    public void setPositionAccountId(String positionAccountId) {
        this.positionAccountId = positionAccountId;
    }

    public String getPositionCusip() {
        return positionCusip;
    }

    public void setPositionCusip(String positionCusip) {
        this.positionCusip = positionCusip;
    }

    public String getPositionPosition() {
        return positionPosition;
    }

    public void setPositionPosition(String positionPosition) {
        this.positionPosition = positionPosition;
    }

    public int getPositionVersion() {
        return positionVersion;
    }

    public void setPositionVersion(int positionVersion) {
        this.positionVersion = positionVersion;
    }
}
