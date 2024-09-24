package practice.multi_threading;

class Inventory {
    private int stock = 10;

    public synchronized void purchaseItem(String buyer) {

        if (stock > 0) {
            System.out.println(buyer + " is purchasing an item...");
            stock--;
            System.out.println(buyer + " completed purchase. Remaining stock: " + stock);
        } else {
            System.out.println("No stock left for " + buyer);
        }
    }

    public static void main(String[] args) {
        Inventory inventory = new Inventory();

        Thread buyer1 = new Thread(() -> inventory.purchaseItem("Buyer 1"));
        Thread buyer2 = new Thread(() -> inventory.purchaseItem("Buyer 2"));
        Thread buyer3 = new Thread(() -> inventory.purchaseItem("Buyer 3"));

        buyer1.start();
        buyer2.start();
        buyer3.start();
    }

}