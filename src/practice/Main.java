package practice;

// Base class for sellers
abstract class Seller {
    private String name;

    public Seller(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // Method to list products
    public abstract void listProducts();
}

// Interface for processing orders
interface OrderProcessor {
    void processOrder(String product);
}

// Interface for receiving payments
interface PaymentReceiver {
    void receivePayment(double amount);
}

// Generic Marketplace class that requires sellers to meet all constraints
class Marketplace<T extends Seller & OrderProcessor & PaymentReceiver> {
    private T seller;

    public Marketplace(T seller) {
        this.seller = seller;
    }

    public void operate() {
        System.out.println("Welcome to the store of " + seller.getName());
        seller.listProducts();
        seller.processOrder("Product1");
        seller.receivePayment(100.0);
    }
}

// Concrete Seller class that meets all constraints
class OnlineSeller extends Seller implements OrderProcessor, PaymentReceiver {
    public OnlineSeller(String name) {
        super(name);
    }

    @Override
    public void listProducts() {
        System.out.println("Listing products for sale...");
    }

    @Override
    public void processOrder(String product) {
        System.out.println("Processing order for " + product);
    }

    @Override
    public void receivePayment(double amount) {
        System.out.println("Received payment of $" + amount);
    }
}

// Main class to run the example
public class Main {
    public static void main(String[] args) {
        OnlineSeller seller = new OnlineSeller("John's Store");
        Marketplace<OnlineSeller> marketplace = new Marketplace<>(seller);
        marketplace.operate();
    }
}
