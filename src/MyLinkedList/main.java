package MyLinkedList;

public class main {
    public static void main(String[] args) {
        MyLinkedList test = new MyLinkedList();
        test.addLast(10);
        test.addLast(20);
        test.addFirst(5);
        test.addLast(30);
        test.removeFirst();
        test.removeLast();

        test.printList();

//        MyLinkedList test2 = new MyLinkedList();
//        test2.printList();
    }
}
