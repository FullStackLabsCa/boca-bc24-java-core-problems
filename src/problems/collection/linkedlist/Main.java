package collection.linkedlist;

public class Main {
    public static void main(String[] args) {
        MyLinkedList myList = new MyLinkedList();

        myList.printList();

        myList.addFirst(30);
        myList.addFirst(20);
        myList.addFirst(10);

        myList.addLast(40);
        myList.addLast(50);
        myList.addLast(60);

        myList.printList();
    }
}
