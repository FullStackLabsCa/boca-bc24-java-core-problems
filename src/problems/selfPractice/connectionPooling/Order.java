package problems.selfPractice.connectionPooling;

public class Order {

    private int orderId;
    private int userId;
    private String orderDate;
    private double amount;

    public Order(int orderId, int userId, String orderDate, double amount) {
        this.orderId = orderId;
        this.userId = userId;
        this.orderDate = orderDate;
        this.amount = amount;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getUserId() {
        return userId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
//        return "Order{" +
//                "orderId=" + orderId +
//                ", userId=" + userId +
//                ", orderDate='" + orderDate + '\'' +
//                ", amount=" + amount +
//                '}';
        return String.format("%-10d %-5d %-20s %-10.2f", orderId, userId, orderDate, amount);
    }
}
